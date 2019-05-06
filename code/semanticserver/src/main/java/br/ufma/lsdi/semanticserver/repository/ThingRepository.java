package br.ufma.lsdi.semanticserver.repository;

import br.ufma.lsdi.semanticserver.domain.Thing;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ThingRepository extends CrudRepository<Thing, String> {
}
