package br.ufma.lsdi.semanticserver.service;

import br.ufma.lsdi.semanticserver.domain.Device;
import br.ufma.lsdi.semanticserver.domain.Mhub;
import br.ufma.lsdi.semanticserver.repository.DeviceRepository;
import br.ufma.lsdi.semanticserver.repository.MhubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MhubService {
	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	MhubRepository mhubRepository;

	public Device toDevice(Mhub mhub) {
		return mhub.getDevice();
	}

	public List<Device> toDeviceList(Iterable<Mhub> mhubList) {
		if (mhubList != null) {
			List<Device> deviceList = new ArrayList<>();

			for (Mhub mhub : mhubList) {
				deviceList.add(mhub.getDevice());
			}
			return deviceList;
		}
		return null;
	}

	public Mhub saveDeviceAndMhub(Device device) {
		Device savedDevice = deviceRepository.save(device);

		Mhub mhub = new Mhub();
		mhub.setDevice(savedDevice);
		return mhubRepository.save(mhub);
	}

	public void deleteDeviceAndMhub(String uuid) {
		mhubRepository.deleteById(uuid);
		deviceRepository.deleteById(uuid);
	}
}
