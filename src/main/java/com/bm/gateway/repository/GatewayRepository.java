package com.bm.gateway.repository;

import com.bm.gateway.entity.Gateway;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GatewayRepository extends CrudRepository<Gateway, UUID> {
}