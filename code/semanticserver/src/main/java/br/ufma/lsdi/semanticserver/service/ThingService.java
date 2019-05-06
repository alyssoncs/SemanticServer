package br.ufma.lsdi.semanticserver.service;

import br.ufma.lsdi.semanticserver.domain.Device;
import br.ufma.lsdi.semanticserver.domain.Thing;
import br.ufma.lsdi.semanticserver.repository.DeviceRepository;
import br.ufma.lsdi.semanticserver.repository.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThingService {
	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	ThingRepository thingRepository;

	public Device toDevice(Thing thing) {
		return thing.getDevice();
	}

	public List<Device> toDeviceList(Iterable<Thing> thingList) {
		if (thingList != null) {
			List<Device> deviceList = new ArrayList<>();

			for (Thing thing : thingList) {
				deviceList.add(thing.getDevice());
			}
			return deviceList;
		}
		return null;
	}

	public Thing saveDeviceAndThing(Device device) {
		Device savedDevice = deviceRepository.save(device);

		Thing thing = new Thing();
		thing.setDevice(savedDevice);
		return thingRepository.save(thing);
	}

	public void deleteDeviceAndThing(String uuid) {
		thingRepository.deleteById(uuid);
		deviceRepository.deleteById(uuid);
	}
}