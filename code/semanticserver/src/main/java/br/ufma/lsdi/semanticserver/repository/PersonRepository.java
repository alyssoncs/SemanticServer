package br.ufma.lsdi.semanticserver.repository;

import br.ufma.lsdi.semanticserver.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, String> {

}
