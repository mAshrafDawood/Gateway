package com.bm.gateway.controller;

import com.bm.gateway.entity.Device;
import com.bm.gateway.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/device/v1")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/all")
    public Iterable<Device> getAllDevice(){
        return deviceService.getAllDevice();
    }

    @GetMapping("/get/{id}")
    public Optional<Device> getDeviceById(@PathVariable Long id){
        return deviceService.getGatewayById(id);
    }

    @PostMapping("/add")
    public Device addDevice(@RequestBody Device device){
        return deviceService.addDevice(device);
    }

    @DeleteMapping("/del/{id}")
    public boolean deleteDevice(@PathVariable Long id){
        return deviceService.deleteDevice(id);
    }

}