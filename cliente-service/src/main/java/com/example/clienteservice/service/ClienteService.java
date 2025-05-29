package com.example.clienteservice.service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clienteservice.model.Cliente;
import com.example.clienteservice.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public boolean isCliente(ObjectId usuarioId) {
        return clienteRepository.findByUsuarioId(usuarioId).isPresent();
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public Cliente atualizar(Cliente cliente) { // exceção
        if (!clienteRepository.existsById(cliente.getId())) {
            throw new RuntimeException("Cliente não encontrado para atualização: "
            + cliente.getId());
        }
        return clienteRepository.save(cliente);
    }
    
    public void excluir(ObjectId id) {
        clienteRepository.deleteById(id);
    }
    
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(ObjectId id) {
        return clienteRepository.findById(id);
    }

    public Cliente buscarPorUsuarioId(ObjectId usuarioId) { // exceção
        return clienteRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para o usuário: "
                 + usuarioId));
    }
}
