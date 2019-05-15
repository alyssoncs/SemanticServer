package br.ufma.lsdi.semanticserver.controller;

import br.ufma.lsdi.semanticserver.domain.Device;
import br.ufma.lsdi.semanticserver.domain.Holder;
import br.ufma.lsdi.semanticserver.domain.Person;
import br.ufma.lsdi.semanticserver.domain.Thing;
import br.ufma.lsdi.semanticserver.repository.HolderRepository;
import br.ufma.lsdi.semanticserver.repository.PersonRepository;
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
@RequestMapping("persons")
public class PersonController {
	@Autowired
	PersonRepository personRepository;

	@Autowired
	HolderRepository holderRepository;

	@GetMapping
	public ResponseEntity<Iterable<Person>> getAllPersons() {
		Iterable<Person> personList = personRepository.findAll();
		return ResponseEntity.ok(personList);
	}

	@GetMapping("/{email}")
	public ResponseEntity<Person> getPersonByEmail(@PathVariable String email) {
		Optional<Person> person = personRepository.findById(email);
		if (person.isPresent())
			return ResponseEntity.ok(person.get());
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Person> createThing(@Valid @RequestBody Person person, HttpServletResponse response) {
		Holder savedHolder = holderRepository.save(new Holder());

		person.setHolderId(savedHolder);
		Person savedPerson = personRepository.save(person);

		buildResponseHeader(response, savedPerson.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
	}

	@DeleteMapping("/{email}")
	public ResponseEntity removePerson(@PathVariable String email) {
		Optional<Person> person = personRepository.findById(email);
		if (person.isPresent()) {
			personRepository.delete(person.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();

	}

	private void buildResponseHeader(HttpServletResponse response, String email) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{email}")
				.buildAndExpand(email).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}
