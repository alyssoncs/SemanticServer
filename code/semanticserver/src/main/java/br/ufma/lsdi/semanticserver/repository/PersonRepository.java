package br.ufma.lsdi.semanticserver.repository;

import br.ufma.lsdi.semanticserver.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, String> {
	public Optional<Person> findByHolder_Id(Long id);
}
