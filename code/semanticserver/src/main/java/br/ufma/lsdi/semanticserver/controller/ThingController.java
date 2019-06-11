package br.ufma.lsdi.semanticserver.controller;

import br.ufma.lsdi.semanticserver.domain.Device;
import br.ufma.lsdi.semanticserver.domain.Thing;
import br.ufma.lsdi.semanticserver.repository.DeviceRepository;
import br.ufma.lsdi.semanticserver.repository.ThingRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/things")
public class ThingController {
	@Autowired
	ThingRepository thingRepository;

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	ThingService thingService;

	@GetMapping
	public ResponseEntity<Iterable<Device>> getAllThings() {
		Iterable<Thing> thingList = thingRepository.findAll();
		return ResponseEntity.ok(thingService.toDeviceList(thingList));
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<Device> getThingByUUID(@PathVariable String uuid) {
		Optional<Thing> thing = thingRepository.findById(uuid);
		if (thing.isPresent())
			return ResponseEntity.ok(thingService.toDevice(thing.get()));
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Device> createThing(@Valid @RequestBody Device thing, HttpServletResponse response) {
		if (thing.getUuid() == null)
			thing.setUuid(UUID.randomUUID().toString());

		Thing savedThing = thingService.saveDeviceAndThing(thing);

		buildResponseHeader(response, savedThing.getId());

		return ResponseEntity.status(HttpStatus.CREATED).body(savedThing.getDevice());
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<Device> updateThing(@PathVariable String uuid, @Valid @RequestBody Device mhub) {
		Optional<Thing> savedThing = thingRepository.findById(uuid);

		if (savedThing.isPresent()) {
			BeanUtils.copyProperties(mhub, savedThing.get().getDevice(), "uuid");
			deviceRepository.save(savedThing.get().getDevice());

			return ResponseEntity.status(HttpStatus.CREATED).body(savedThing.get().getDevice());
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity removeThing(@PathVariable String uuid) {
		Optional<Thing> thing = thingRepository.findById(uuid);
		if (thing.isPresent()) {
			thingService.deleteDeviceAndThing(uuid);
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
