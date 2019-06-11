package br.ufma.lsdi.semanticserver.controller;

import br.ufma.lsdi.semanticserver.domain.Device;
import br.ufma.lsdi.semanticserver.domain.Mhub;
import br.ufma.lsdi.semanticserver.domain.Thing;
import br.ufma.lsdi.semanticserver.repository.DeviceRepository;
import br.ufma.lsdi.semanticserver.repository.MhubRepository;
import br.ufma.lsdi.semanticserver.repository.ThingRepository;
import br.ufma.lsdi.semanticserver.service.MhubService;
import br.ufma.lsdi.semanticserver.service.ThingService;
import org.springframework.beans.BeanUtils;
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
	DeviceRepository deviceRepository;

	@Autowired
	MhubService mhubService;

	@GetMapping
	public ResponseEntity<Iterable<Device>> getAllMhubs() {
		Iterable<Mhub> mhubList = mhubRepository.findAll();
		return ResponseEntity.ok(mhubService.toDeviceList(mhubList));
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<Device> getMhubByUUID(@PathVariable String uuid) {
		Optional<Mhub> mhub = mhubRepository.findById(uuid);
		if (mhub.isPresent())
			return ResponseEntity.ok(mhubService.toDevice(mhub.get()));
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Device> createMhub(@Valid @RequestBody Device mhub, HttpServletResponse response) {
		if (mhub.getUuid() == null)
			mhub.setUuid(UUID.randomUUID().toString());

		Mhub savedMhub = mhubService.saveDeviceAndMhub(mhub);

		buildResponseHeader(response, savedMhub.getId());

		return ResponseEntity.status(HttpStatus.CREATED).body(savedMhub.getDevice());
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<Device> updateMhub(@PathVariable String uuid, @Valid @RequestBody Device mhub) {
		Optional<Mhub> savedMhub = mhubRepository.findById(uuid);
		//Optional<Device> savedMhub = deviceRepository.findById(uuid);
		if (savedMhub.isPresent()) {
			BeanUtils.copyProperties(mhub, savedMhub.get().getDevice(), "uuid");
			deviceRepository.save(savedMhub.get().getDevice());

			return ResponseEntity.status(HttpStatus.CREATED).body(savedMhub.get().getDevice());
		}

		return ResponseEntity.notFound().build();
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
