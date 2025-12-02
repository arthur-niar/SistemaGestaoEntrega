package com.example.tms_adapter_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.example.tms_adapter_service.dto.PedidoDTO;
import com.example.tms_adapter_service.dto.ShipmentRequestDTO;
import com.example.tms_adapter_service.dto.ShipmentResponseDTO;
import com.example.tms_adapter_service.model.Shipment;
import com.example.tms_adapter_service.repository.ShipmentRepository;

import org.springframework.beans.factory.annotation.Qualifier;

import reactor.core.publisher.Mono;

@Service
public class TmsAdapterService {

    @Autowired
    @Qualifier("pedidoClient")
    private WebClient pedidoServiceClient;

    @Autowired
    @Qualifier("tmsClient")
    private WebClient tmsClient;

    @Autowired
    private ShipmentRepository shipmentRepository;

    /**
     * Busca um pedido no pedido-service pelo ID
     */
    public Mono<PedidoDTO> buscarPedidoPorId(String pedidoId) {
        return pedidoServiceClient.get()
                .uri("/api/pedidos/{id}", pedidoId)  // <<< ROTA CORRETA
                .retrieve()
                .bodyToMono(PedidoDTO.class)
                .onErrorResume(WebClientResponseException.NotFound.class, e -> {
                    System.out.println("Pedido não encontrado no pedido-service");
                    return Mono.empty();
                });
    }

    /**
     * Converte PedidoDTO → ShipmentRequestDTO
     */
    public ShipmentRequestDTO converterPedidoParaShipment(PedidoDTO pedido) {
        ShipmentRequestDTO dto = new ShipmentRequestDTO();
        dto.setPedidoId(pedido.getId());
        dto.setDescricao(pedido.getDescricao());
        dto.setValorEntrega(pedido.getTaxaEntrega());
        return dto;
    }

    /**
     * Chama a TMS externa (placeholder por enquanto)
     */
    public Mono<ShipmentResponseDTO> enviarAoTms(ShipmentRequestDTO request) {
        return tmsClient.post()
                .uri("/shipments")
                .body(Mono.just(request), ShipmentRequestDTO.class)
                .retrieve()
                .bodyToMono(ShipmentResponseDTO.class);
    }

    /**
     * Processo completo:
     * Pedido → DTO → TMS → Salva no Mongo
     */
    public Mono<Shipment> processarEnvio(String pedidoId) {

        return buscarPedidoPorId(pedidoId)   // <<< CORRIGIDO
                .flatMap(pedido -> {
                    if (pedido == null) {
                        return Mono.error(new RuntimeException("Pedido não encontrado"));
                    }
                    ShipmentRequestDTO dto = converterPedidoParaShipment(pedido);
                    return enviarAoTms(dto);
                })
                .map(response -> {
                    Shipment shipment = new Shipment(
                            pedidoId,
                            response.getTrackingCode(),
                            response.getStatus()
                    );
                    shipmentRepository.save(shipment);
                    return shipment;
                });
    }

    /**
     * Consulta de status no MongoDB
     */
    public Shipment consultarShipment(String pedidoId) {
        return shipmentRepository.findByPedidoId(pedidoId);
    }
}
