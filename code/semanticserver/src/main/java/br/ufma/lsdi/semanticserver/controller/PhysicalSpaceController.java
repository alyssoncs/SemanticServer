package br.ufma.lsdi.semanticserver.controller;

import br.ufma.lsdi.semanticserver.domain.*;
import br.ufma.lsdi.semanticserver.repository.HolderRepository;
import br.ufma.lsdi.semanticserver.repository.MhubRepository;
import br.ufma.lsdi.semanticserver.repository.PhysicalSpaceRepository;
import br.ufma.lsdi.semanticserver.repository.ThingRepository;
import br.ufma.lsdi.semanticserver.service.MhubService;
import br.ufma.lsdi.semanticserver.service.PhysicalSpaceService;
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

@RestController
@RequestMapping("physical_spaces")
public class PhysicalSpaceController {
	@Autowired
	PhysicalSpaceRepository physicalSpaceRepository;

	@Autowired
	HolderRepository holderRepository;

	@Autowired
	ThingRepository thingRepository;

	@Autowired
	MhubRepository mhubRepository;

	@Autowired
	PhysicalSpaceService physicalSpaceService;

	@Autowired
	ThingService thingService;

	@Autowired
	MhubService mhubService;

	@GetMapping
	public ResponseEntity<Iterable<PhysicalSpace>> getAllPhysicalSpaces() {
		Iterable<PhysicalSpace> physicalSpacesList = physicalSpaceRepository.findAll();
		return ResponseEntity.ok(physicalSpacesList);
	}

	@GetMapping("/roots")
	public ResponseEntity<Iterable<PhysicalSpace>> getRootPhysicalSpaces() {
		Iterable<PhysicalSpace> rootPhysicalSpacesList = physicalSpaceRepository.findPhysicalSpacesByParent(null);
		return ResponseEntity.ok(rootPhysicalSpacesList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PhysicalSpace> getPhysicalSpaceById(@PathVariable("id") Long HolderId) {
		PhysicalSpace physicalSpace = physicalSpaceService.getPhysicalSpaceByHolderId(HolderId);

		if (physicalSpace != null)
			return ResponseEntity.ok(physicalSpace);

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/things")
	public ResponseEntity<Iterable<Device>> getPhysicalSpaceThings(@PathVariable("id") Long HolderId) {
		PhysicalSpace physicalSpace = physicalSpaceService.getPhysicalSpaceByHolderId(HolderId);

		if (physicalSpace != null) {
			Iterable<Thing> things = thingRepository.findAllByDevice_Holder(physicalSpace.getHolder());
			return ResponseEntity.ok(thingService.toDeviceList(things));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/descendant/things")
	public ResponseEntity<Iterable<Device>> getPhysicalSpaceDescendantThings(@PathVariable("id") Long HolderId) {
		PhysicalSpace physicalSpace = physicalSpaceService.getPhysicalSpaceByHolderId(HolderId);

		if (physicalSpace != null) {
			Iterable<Thing> things = physicalSpaceService.getPhysicalSpaceDescendantThings(physicalSpace);
			return ResponseEntity.ok(thingService.toDeviceList(things));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/mhubs")
	public ResponseEntity<Iterable<Device>> getPhysicalSpaceMhubs(@PathVariable("id") Long HolderId) {
		PhysicalSpace physicalSpace = physicalSpaceService.getPhysicalSpaceByHolderId(HolderId);

		if (physicalSpace != null) {
			Iterable<Mhub> mhubs = mhubRepository.findAllByDevice_Holder(physicalSpace.getHolder());
			return ResponseEntity.ok(mhubService.toDeviceList(mhubs));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/descendant/mhubs")
	public ResponseEntity<Iterable<Device>> getPhysicalSpaceDescendantMhubs(@PathVariable("id") Long HolderId) {
		PhysicalSpace physicalSpace = physicalSpaceService.getPhysicalSpaceByHolderId(HolderId);

		if (physicalSpace != null) {
			Iterable<Mhub> mhubs = physicalSpaceService.getPhysicalSpaceDescendantMhubs(physicalSpace);
			return ResponseEntity.ok(mhubService.toDeviceList(mhubs));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}/parent")
	public ResponseEntity<PhysicalSpace> getPhysicalSpaceParent(@PathVariable("id") Long HolderId) {
		PhysicalSpace physicalSpace = physicalSpaceService.getPhysicalSpaceByHolderId(HolderId);

		if (physicalSpace != null)
			return ResponseEntity.ok(physicalSpace.getParent());

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<PhysicalSpace> createPhysicalSpace(@Valid @RequestBody PhysicalSpace physicalSpace, HttpServletResponse response) {
		Holder savedHolder = holderRepository.save(new Holder());
		physicalSpace.setHolder(savedHolder);

		physicalSpaceService.setParent(physicalSpace);
		PhysicalSpace savedPhysicalSpace = physicalSpaceRepository.save(physicalSpace);

		buildResponseHeader(response, savedPhysicalSpace.getHolderId());
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPhysicalSpace);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PhysicalSpace> deletePhysicalSpace(@PathVariable("id") Long holderId) {
		PhysicalSpace physicalSpace = physicalSpaceService.getPhysicalSpaceByHolderId(holderId);
		if (physicalSpace != null) {
			physicalSpaceRepository.delete(physicalSpace);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	private void buildResponseHeader(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}
