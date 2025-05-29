package com.example.clienteservice.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.clienteservice.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, ObjectId> {

    Optional<Cliente> findByUsuarioId(ObjectId usuarioId);

}