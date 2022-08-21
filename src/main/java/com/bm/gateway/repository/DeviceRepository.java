package com.bm.gateway.repository;

import com.bm.gateway.entity.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Long> {
}