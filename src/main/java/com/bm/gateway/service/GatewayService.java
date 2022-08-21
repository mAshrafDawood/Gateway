package com.bm.gateway.service;

import com.bm.gateway.entity.Gateway;
import com.bm.gateway.repository.GatewayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GatewayService {

    @Autowired
    private GatewayRepository gatewayRepository;

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
}