package com.bm.gateway.service;

import com.bm.gateway.entity.Device;
import com.bm.gateway.entity.Gateway;
import com.bm.gateway.repository.DeviceRepository;
import com.bm.gateway.repository.GatewayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GatewayService {

    @Autowired
    private GatewayRepository gatewayRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public Iterable<Gateway> getAllGateway(){
        return gatewayRepository.findAll();
    }

    public Gateway addGateway(Gateway gateway){
        //TODO: validate IP before adding
        return gatewayRepository.save(gateway);
    }

    public Optional<Gateway> getGatewayById(String id){
        return gatewayRepository.findById(UUID.fromString(id));
    }

    public Optional<Gateway> addDeviceToGateway(String gatewayId, Long deviceId){
        Optional<Gateway> gatewayOpt = gatewayRepository.findById(UUID.fromString(gatewayId));
        Gateway gateway;
        if (gatewayOpt.isPresent()) gateway = gatewayOpt.get();
        else return gatewayOpt;

        Optional<Device> deviceOpt = deviceRepository.findById(deviceId);
        Device device;
        if (deviceOpt.isPresent()) device = deviceOpt.get();
        else return Optional.empty();
        gateway.getDevices().add(device);
        gatewayRepository.save(gateway);
        return gatewayOpt;
    }

    public Optional<Gateway> removeDeviceFromGateway(String gatewayId, Long deviceId){
        Optional<Gateway> gatewayOpt = gatewayRepository.findById(UUID.fromString(gatewayId));
        Gateway gateway;
        if (gatewayOpt.isPresent()) gateway = gatewayOpt.get();
        else return gatewayOpt;

        Optional<Device> deviceOpt = deviceRepository.findById(deviceId);
        Device device;
        if (deviceOpt.isPresent()) device = deviceOpt.get();
        else return Optional.empty();

        gateway.getDevices().remove(device);
        gatewayRepository.save(gateway);
        return gatewayOpt;
    }


}