package br.ufma.lsdi.semanticserver.service;

import br.ufma.lsdi.semanticserver.domain.Holder;
import br.ufma.lsdi.semanticserver.domain.Mhub;
import br.ufma.lsdi.semanticserver.domain.PhysicalSpace;
import br.ufma.lsdi.semanticserver.domain.Thing;
import br.ufma.lsdi.semanticserver.repository.HolderRepository;
import br.ufma.lsdi.semanticserver.repository.MhubRepository;
import br.ufma.lsdi.semanticserver.repository.PhysicalSpaceRepository;
import br.ufma.lsdi.semanticserver.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PhysicalSpaceService {
	@Autowired
	HolderRepository holderRepository;

	@Autowired
	ThingRepository thingRepository;

	@Autowired
	MhubRepository mhubRepository;

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

	public List<Thing> getPhysicalSpaceDescendantThings(PhysicalSpace physicalSpace) {
		if (physicalSpace == null) {
			return new ArrayList<>();
		}

		Iterable<Thing> things = thingRepository.findAllByDevice_Holder(physicalSpace.getHolder());
		List<Thing> thingList = iterableToList(things.iterator());

		for (PhysicalSpace child : physicalSpace.getChildren()) {
			thingList.addAll(getPhysicalSpaceDescendantThings(child));
		}

		return thingList;
	}

	public List<Mhub> getPhysicalSpaceDescendantMhubs(PhysicalSpace physicalSpace) {
		if (physicalSpace == null) {
			return new ArrayList<>();
		}

		Iterable<Mhub> mhubs = mhubRepository.findAllByDevice_Holder(physicalSpace.getHolder());
		List<Mhub> mhubList = iterableToList(mhubs.iterator());

		for (PhysicalSpace child : physicalSpace.getChildren()) {
			mhubList.addAll(getPhysicalSpaceDescendantMhubs(child));
		}

		return mhubList;
	}

	private static <T> List<T> iterableToList(Iterator<T> iter) {
		List<T> copy = new ArrayList<T>();
		while (iter.hasNext())
			copy.add(iter.next());

		return copy;
	}


	public void setParent(PhysicalSpace physicalSpace) {
		if (physicalSpace.getParent() != null) {
			Optional<PhysicalSpace> parent = physicalSpaceRepository.findById(physicalSpace.getParent().getHolder().getId());
			if (parent.isPresent()) {
				physicalSpace.setParent(parent.get());
			} else {
				physicalSpace.setParent(null);
			}
		}
	}
}
