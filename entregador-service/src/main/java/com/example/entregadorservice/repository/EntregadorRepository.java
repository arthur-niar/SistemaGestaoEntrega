package com.example.entregadorservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.entregadorservice.model.Entregador;

public interface EntregadorRepository extends MongoRepository<Entregador, String> {

    Optional<Entregador> findByUsuarioId(String usuarioId);
    Optional<Entregador> findByPedidoId(String pedidoId);
    
}
