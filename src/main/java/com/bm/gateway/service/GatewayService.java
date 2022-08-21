package com.bm.gateway.service;

import com.bm.gateway.entity.Device;
import com.bm.gateway.entity.Gateway;
import com.bm.gateway.repository.DeviceRepository;
import com.bm.gateway.repository.GatewayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GatewayService {

    private static final String IPV4_REGEX = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";
    private static final Pattern pattern = Pattern.compile(IPV4_REGEX);

    protected static final int MAX_DEVICE = 10;

    @Autowired
    private GatewayRepository gatewayRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public Iterable<Gateway> getAllGateway(){
        return gatewayRepository.findAll();
    }

    public boolean isValid(final String gatewayIpv4){
        return pattern.matcher(gatewayIpv4).matches();
    }

    public Gateway addGateway(Gateway gateway){
        gateway.setValid(isValid(gateway.getIpv4()));
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

        if (gateway.getDevices().size() >= MAX_DEVICE){
            return Optional.empty();
        }

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