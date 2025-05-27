package com.example.pedidoservice.repository;

import com.example.pedidoservice.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
public interface PedidoRepository extends MongoRepository <Pedido, String> {

    List<Pedido> findByClienteId(String clienteId); 
    List<Pedido> findByStatus(String status);   
    List<Pedido> findByDataHoraBetween(LocalDateTime start, LocalDateTime end);
    List<Pedido> findByTaxaEntrega(double taxaEntrega);
    
}
