package br.ufma.lsdi.semanticserver.service;

import br.ufma.lsdi.semanticserver.domain.Holder;
import br.ufma.lsdi.semanticserver.domain.Person;
import br.ufma.lsdi.semanticserver.repository.HolderRepository;
import br.ufma.lsdi.semanticserver.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
	@Autowired
	HolderRepository holderRepository;

	@Autowired
	PersonRepository personRepository;

	public Optional<Person> getPersonByEmailOrHolderId(String emailOrId) {
		Optional<Person> person;

		try {
			Long id = Long.parseLong(emailOrId);
			person = personRepository.findByHolder_Id(id);

		} catch (Exception e) {
			String email = emailOrId;
			person = personRepository.findById(email);
		}

		return person;
	}
}
