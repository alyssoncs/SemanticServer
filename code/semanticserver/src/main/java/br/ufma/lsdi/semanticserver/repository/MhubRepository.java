package br.ufma.lsdi.semanticserver.repository;

import br.ufma.lsdi.semanticserver.domain.Holder;
import br.ufma.lsdi.semanticserver.domain.Mhub;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MhubRepository extends CrudRepository<Mhub, String> {
	public Iterable<Mhub> findAllByDevice_Holder(Holder holder);
}
