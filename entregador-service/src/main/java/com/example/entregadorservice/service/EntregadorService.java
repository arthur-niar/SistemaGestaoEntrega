package com.example.entregadorservice.service;

import com.example.entregadorservice.repository.EntregadorRepository;
import com.example.entregadorservice.model.Entregador;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EntregadorService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    public boolean isEntregador(ObjectId usuarioId) {
        return entregadorRepository.findByUsuarioId(usuarioId).isPresent();
    }

    public Entregador salvar(Entregador entregador) {
        return entregadorRepository.save(entregador);
    }

    public Entregador atualizar(Entregador entregador) {
        if (!entregadorRepository.existsById(entregador.getId())) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Entregador não encontrado para atualização: " 
            + entregador.getId());
        }
        return entregadorRepository.save(entregador);
    }

    public void excluir(ObjectId id) {
        entregadorRepository.deleteById(id);
    }

    public Iterable<Entregador> buscarTodos() {
        return entregadorRepository.findAll();
    }
    
    public Optional<Entregador> buscarPorId(ObjectId id) {
        return entregadorRepository.findById(id);
    }

    public Entregador buscarPorUsuarioId(ObjectId usuarioId) {
        return entregadorRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Entregador não encontrado para o usuário: " 
                + usuarioId));
    }

    public Entregador buscarPorPedidoId(ObjectId pedidoId) {
        return entregadorRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Entregador não encontrado para o pedido: " 
                + pedidoId));
    }
}
