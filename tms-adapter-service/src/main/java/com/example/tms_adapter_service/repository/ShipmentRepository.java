package com.example.tms_adapter_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.tms_adapter_service.model.Shipment;

public interface ShipmentRepository extends MongoRepository<Shipment, String> {

    Shipment findByPedidoId(String pedidoId);
}
