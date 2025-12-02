package com.example.tms_fake_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tms_fake_service.model.Shipment;
import com.example.tms_fake_service.service.ShipmentService;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService service;

    @RequestMapping("/teste")
    public String ping() {
        return "TMS Fake Service está online!";
    }

    @PostMapping
    public Shipment criar(@RequestBody Shipment request) {
        return service.criarShipment(
                request.getPedidoId(),
                request.getTrackingCode(),
                request.getStatus()
        );
    }

    @GetMapping("/{id}")
    public Shipment consultar(@PathVariable String id) {
        id = id.trim(); // remove espaços
        return service.consultar(id);
    }
}
