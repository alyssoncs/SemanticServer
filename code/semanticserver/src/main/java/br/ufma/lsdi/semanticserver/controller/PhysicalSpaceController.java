package br.ufma.lsdi.semanticserver.controller;

import br.ufma.lsdi.semanticserver.domain.Holder;
import br.ufma.lsdi.semanticserver.domain.PhysicalSpace;
import br.ufma.lsdi.semanticserver.repository.HolderRepository;
import br.ufma.lsdi.semanticserver.repository.PhysicalSpaceRepository;
import br.ufma.lsdi.semanticserver.service.PhysicalSpaceService;
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
	PhysicalSpaceService physicalSpaceService;

	@GetMapping
	public ResponseEntity<Iterable<PhysicalSpace>> getAllPhysicalSpaces() {
		Iterable<PhysicalSpace> physicalSpacesList = physicalSpaceRepository.findAll();
		return ResponseEntity.ok(physicalSpacesList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PhysicalSpace> getPhysicalSpaceById(@PathVariable("id") Long HolderId) {
		PhysicalSpace physicalSpace = physicalSpaceService.getPhysicalSpaceByHolderId(HolderId);

		if (physicalSpace != null)
			return ResponseEntity.ok(physicalSpace);

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
