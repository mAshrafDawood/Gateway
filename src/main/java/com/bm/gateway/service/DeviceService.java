package com.bm.gateway.service;

import com.bm.gateway.entity.Device;
import com.bm.gateway.entity.Gateway;
import com.bm.gateway.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Iterable<Device> getAllDevice(){
        return deviceRepository.findAll();
    }

    public Device addDevice(Device device){
        return deviceRepository.save(device);
    }

    public boolean deleteDevice(Long id){
        deviceRepository.deleteById(id);
        return true;
    }

    public Optional<Device> getGatewayById(Long id){
        return deviceRepository.findById(id);
    }

}