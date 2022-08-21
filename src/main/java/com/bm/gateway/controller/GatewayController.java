package com.bm.gateway.controller;

import com.bm.gateway.entity.Gateway;
import com.bm.gateway.service.GatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gateway/v1")
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @GetMapping("/all")
    public Iterable<Gateway> getAllGateway(){
        return gatewayService.getAllGateway();
    }

    @PostMapping("/add")
    public Gateway addGateway(@RequestBody Gateway gateway){
        return gatewayService.addGateway(gateway);
    }

    @GetMapping("/get/{id}")
    public Optional<Gateway> getGatewayById(@PathVariable String id){
        return gatewayService.getGatewayById(id);
    }

    @PutMapping("/add/device/{gatewayId}/{deviceId}")
    public Optional<Gateway> addDeviceToGateway(@PathVariable String gatewayId, @PathVariable Long deviceId){
        return gatewayService.addDeviceToGateway(gatewayId, deviceId);
    }

    @DeleteMapping("/remove/device/{gatewayId}/{deviceId}")
    public Optional<Gateway> removeDeviceFromGateway(@PathVariable String gatewayId, @PathVariable Long deviceId){
        return gatewayService.removeDeviceFromGateway(gatewayId, deviceId);
    }
}