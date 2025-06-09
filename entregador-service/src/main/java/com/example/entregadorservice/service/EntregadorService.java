package com.example.entregadorservice.service;

import com.example.entregadorservice.repository.EntregadorRepository;
import com.example.entregadorservice.model.Entregador;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EntregadorService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    public boolean isEntregador(String usuarioId) {
        return entregadorRepository.findByUsuarioId(usuarioId).isPresent();
    }

    public Entregador salvar(Entregador entregador) {
        return entregadorRepository.save(entregador);
    }
    public Optional<Entregador> atualizar(String id, Entregador atualizado) {
        return entregadorRepository.findById(id).map(entregador -> {
            entregador.setUsuarioId(atualizado.getUsuarioId());
            entregador.setPedidoId(atualizado.getPedidoId());
            entregador.setNome(atualizado.getNome());
            entregador.setEmail(atualizado.getEmail());
            entregador.setTelefone(atualizado.getTelefone());
            entregador.setCNH(atualizado.getCNH());
            entregador.setVeiculo(atualizado.getVeiculo());
            return entregadorRepository.save(entregador);
        });
    }

    public boolean excluir(String id) {
        if (entregadorRepository.existsById(id)) {
            entregadorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Iterable<Entregador> buscarTodos() {
        return entregadorRepository.findAll();
    }
    
    public Optional<Entregador> buscarPorId(String id) {
        return entregadorRepository.findById(id);
    }

    public Entregador buscarPorUsuarioId(String usuarioId) {
        return entregadorRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Entregador não encontrado para o usuário: " 
                + usuarioId));
    }

    public Entregador buscarPorPedidoId(String pedidoId) {
        return entregadorRepository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Entregador não encontrado para o pedido: " 
                + pedidoId));
    }
}
