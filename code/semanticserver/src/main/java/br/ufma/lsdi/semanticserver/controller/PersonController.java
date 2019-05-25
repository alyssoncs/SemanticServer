package br.ufma.lsdi.semanticserver.controller;

import br.ufma.lsdi.semanticserver.domain.*;
import br.ufma.lsdi.semanticserver.repository.HolderRepository;
import br.ufma.lsdi.semanticserver.repository.MhubRepository;
import br.ufma.lsdi.semanticserver.repository.PersonRepository;
import br.ufma.lsdi.semanticserver.repository.ThingRepository;
import br.ufma.lsdi.semanticserver.service.MhubService;
import br.ufma.lsdi.semanticserver.service.PersonService;
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
@RequestMapping("persons")
public class PersonController {
	@Autowired
	PersonRepository personRepository;

	@Autowired
	HolderRepository holderRepository;

	@Autowired
	ThingRepository thingRepository;

	@Autowired
	MhubRepository mhubRepository;

	@Autowired
	PersonService personService;

	@Autowired
	ThingService thingService;

	@Autowired
	MhubService mhubService;

	@GetMapping
	public ResponseEntity<Iterable<Person>> getAllPersons() {
		Iterable<Person> personList = personRepository.findAll();
		return ResponseEntity.ok(personList);
	}

	@GetMapping("/{emailOrId}")
	public ResponseEntity<Person> getPersonByEmailOrHolderId(@PathVariable String emailOrId) {
		Optional<Person> person = personService.getPersonByEmailOrHolderId(emailOrId);

		if (person.isPresent()) {
			return ResponseEntity.ok(person.get());
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{emailOrId}/things")
	public ResponseEntity<Iterable<Device>> getPersonThings(@PathVariable String emailOrId) {
		Optional<Person> person = personService.getPersonByEmailOrHolderId(emailOrId);

		if (person.isPresent()) {
			Iterable<Thing> things = thingRepository.findAllByDevice_Holder(person.get().getHolder());
			return ResponseEntity.ok(thingService.toDeviceList(things));
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{emailOrId}/mhubs")
	public ResponseEntity<Iterable<Device>> getPersonMhubs(@PathVariable String emailOrId) {
		Optional<Person> person = personService.getPersonByEmailOrHolderId(emailOrId);

		if (person.isPresent()) {
			Iterable<Mhub> things = mhubRepository.findAllByDevice_Holder(person.get().getHolder());
			return ResponseEntity.ok(mhubService.toDeviceList(things));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Person> createThing(@Valid @RequestBody Person person, HttpServletResponse response) {
		Holder savedHolder = holderRepository.save(new Holder());

		person.setHolder(savedHolder);
		Person savedPerson = personRepository.save(person);

		buildResponseHeader(response, savedPerson.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
	}

	@DeleteMapping("/{emailOrId}")
	public ResponseEntity removePerson(@PathVariable String emailOrId) {
		Optional<Person> person = personService.getPersonByEmailOrHolderId(emailOrId);

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
