package com.example.tms_fake_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.tms_fake_service.model.Shipment;

public interface ShipmentRepository extends MongoRepository<Shipment, String> {
}
