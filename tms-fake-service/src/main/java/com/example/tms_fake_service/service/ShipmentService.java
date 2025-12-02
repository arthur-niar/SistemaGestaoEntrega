package com.example.tms_fake_service.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.tms_fake_service.model.Shipment;
import com.example.tms_fake_service.repository.ShipmentRepository;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository repository;

    public Shipment criarShipment(String pedidoId, String descricao, String valor) {

        String tracking = "TRK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        String status = "CREATED";

        Shipment s = new Shipment();
        s.setPedidoId(pedidoId);
        s.setTrackingCode(tracking);
        s.setStatus(status);

        return repository.save(s);
    }

    public Shipment consultar(String shipmentId) {
        return repository.findById(shipmentId)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, 
                    "Shipment não encontrado com o ID: " + shipmentId
                ));
    }
}
