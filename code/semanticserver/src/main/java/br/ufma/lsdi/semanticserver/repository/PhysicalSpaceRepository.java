package br.ufma.lsdi.semanticserver.repository;

import br.ufma.lsdi.semanticserver.domain.Holder;
import br.ufma.lsdi.semanticserver.domain.PhysicalSpace;
import org.springframework.data.repository.CrudRepository;

public interface PhysicalSpaceRepository extends CrudRepository<PhysicalSpace, Long> {
	public Iterable<PhysicalSpace> findPhysicalSpacesByParent(PhysicalSpace parent);
}
