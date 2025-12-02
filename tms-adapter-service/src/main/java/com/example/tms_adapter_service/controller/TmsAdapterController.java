package com.example.tms_adapter_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.tms_adapter_service.model.Shipment;
import com.example.tms_adapter_service.service.TmsAdapterService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tms")
public class TmsAdapterController {

    @Autowired
    private TmsAdapterService tmsAdapterService;

    /**
     * Dispara o envio de um pedido para o TMS
     * Fluxo:
     * 1. Busca o pedido no pedido-service
     * 2. Converte para Shipment DTO
     * 3. Envia para o TMS externo
     * 4. Salva o shipment gerado no MongoDB
     */
    @PostMapping("/enviar/{pedidoId}")
    public Mono<Shipment> enviarPedidoParaTms(@PathVariable String pedidoId) {
        return tmsAdapterService.processarEnvio(pedidoId);
    }

    /**
     * Consulta o status salvo no MongoDB
     */
    @GetMapping("/status/{pedidoId}")
    public Shipment consultarStatus(@PathVariable String pedidoId) {
        return tmsAdapterService.consultarShipment(pedidoId);
    }
}
