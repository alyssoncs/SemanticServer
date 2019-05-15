package br.ufma.lsdi.semanticserver.controller;

import br.ufma.lsdi.semanticserver.domain.Device;
import br.ufma.lsdi.semanticserver.domain.Mhub;
import br.ufma.lsdi.semanticserver.domain.Thing;
import br.ufma.lsdi.semanticserver.repository.MhubRepository;
import br.ufma.lsdi.semanticserver.repository.ThingRepository;
import br.ufma.lsdi.semanticserver.service.MhubService;
import br.ufma.lsdi.semanticserver.service.ThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/mhubs")
public class MhubController {
	@Autowired
	MhubRepository mhubRepository;

	@Autowired
	MhubService mhubService;

	@GetMapping
	public ResponseEntity<Iterable<Device>> getAllThings() {
		Iterable<Mhub> mhubList = mhubRepository.findAll();
		return ResponseEntity.ok(mhubService.toDeviceList(mhubList));
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<Device> getThingByUUID(@PathVariable String uuid) {
		Optional<Mhub> mhub = mhubRepository.findById(uuid);
		if (mhub.isPresent())
			return ResponseEntity.ok(mhubService.toDevice(mhub.get()));
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Device> createThing(@Valid @RequestBody Device mhub, HttpServletResponse response) {
		if (mhub.getUuid() == null)
			mhub.setUuid(UUID.randomUUID().toString());

		Mhub savedMhub = mhubService.saveDeviceAndMhub(mhub);

		buildResponseHeader(response, savedMhub.getId());

		return ResponseEntity.status(HttpStatus.CREATED).body(savedMhub.getDevice());
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity removeThing(@PathVariable String uuid) {
		Optional<Mhub> mhub = mhubRepository.findById(uuid);
		if (mhub.isPresent()) {
			mhubService.deleteDeviceAndMhub(uuid);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();

	}

	private void buildResponseHeader(HttpServletResponse response, String uuid) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{uuid}")
				.buildAndExpand(uuid).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}
