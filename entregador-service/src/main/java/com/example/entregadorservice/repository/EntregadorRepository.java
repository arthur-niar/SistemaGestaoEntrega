package com.example.entregadorservice.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.entregadorservice.model.Entregador;

public interface EntregadorRepository extends MongoRepository<Entregador, ObjectId> {

    Optional<Entregador> findByUsuarioId(ObjectId usuarioId);
    Optional<Entregador> findByPedidoId(ObjectId pedidoId);
    
}
