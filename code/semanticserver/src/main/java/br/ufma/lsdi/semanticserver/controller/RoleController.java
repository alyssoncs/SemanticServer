package br.ufma.lsdi.semanticserver.controller;

import br.ufma.lsdi.semanticserver.domain.Role;
import br.ufma.lsdi.semanticserver.repository.RoleRepository;
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
@RequestMapping("roles")
public class RoleController {
	@Autowired
	RoleRepository roleRepository;

	@GetMapping
	public ResponseEntity<Iterable<Role>> getAllRoles() {
		Iterable<Role> roleList = roleRepository.findAll();
		return ResponseEntity.ok(roleList);
	}
	@GetMapping("/{name}")
	public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
		Optional<Role> role = roleRepository.findById(name);
		if (role.isPresent()) {
			return ResponseEntity.ok(role.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Role> createRole(@RequestBody @Valid Role role, HttpServletResponse response) {
		Role savedRole = roleRepository.save(role);

		buildResponseHeader(response, savedRole.getName());

		return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
	}

	@DeleteMapping("/{name}")
	public ResponseEntity removeRole(@PathVariable String name) {
		Optional<Role> role = roleRepository.findById(name);
		if (role.isPresent()) {
			roleRepository.delete(role.get());
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	private void buildResponseHeader(HttpServletResponse response, String name) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{name}")
				.buildAndExpand(name).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}
