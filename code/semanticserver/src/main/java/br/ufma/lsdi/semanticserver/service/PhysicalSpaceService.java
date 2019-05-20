package br.ufma.lsdi.semanticserver.service;

import br.ufma.lsdi.semanticserver.domain.Holder;
import br.ufma.lsdi.semanticserver.domain.PhysicalSpace;
import br.ufma.lsdi.semanticserver.repository.HolderRepository;
import br.ufma.lsdi.semanticserver.repository.PhysicalSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhysicalSpaceService {
	@Autowired
	HolderRepository holderRepository;

	@Autowired
	PhysicalSpaceRepository physicalSpaceRepository;

	public PhysicalSpace getPhysicalSpaceByHolderId(Long id) {
		Optional<Holder> holder = holderRepository.findById(id);
		if (holder.isPresent()) {
			Optional<PhysicalSpace> physicalSpace = physicalSpaceRepository.findById(id);
			if (physicalSpace.isPresent()) {
				return physicalSpace.get();
			}
		}

		return null;
	}

	public void setParent(PhysicalSpace physicalSpace) {
		System.out.println("<<-not->>");

		if (physicalSpace.getParent() != null) {
			System.out.println("<<-->> " + physicalSpace.getParent().toString());
			Optional<PhysicalSpace> parent = physicalSpaceRepository.findById(physicalSpace.getParent().getHolder().getId());
			if (parent.isPresent()) {
				physicalSpace.setParent(parent.get());
			} else {
				physicalSpace.setParent(null);
			}
		}
	}
}
