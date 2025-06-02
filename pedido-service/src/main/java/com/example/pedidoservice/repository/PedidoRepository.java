package com.example.pedidoservice.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.pedidoservice.model.Pedido;

public interface PedidoRepository extends MongoRepository<Pedido, String> {
    List<Pedido> findByStatus(String status);
    List<Pedido> findByDataHoraBetween(LocalDateTime start, LocalDateTime end);
    List<Pedido> findByTaxaEntrega(double taxaEntrega);
}