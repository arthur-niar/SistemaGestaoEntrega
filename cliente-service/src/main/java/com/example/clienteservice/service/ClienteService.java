package com.example.clienteservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clienteservice.model.Cliente;
import com.example.clienteservice.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public boolean isCliente(String usuarioId) {
        return clienteRepository.findByUsuarioId(usuarioId).isPresent();
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public Optional<Cliente> atualizar(String id, Cliente atualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setUsuarioId(atualizado.getUsuarioId());
            cliente.setNome(atualizado.getNome());
            cliente.setEmail(atualizado.getEmail());
            cliente.setTelefone(atualizado.getTelefone());
            cliente.setEndereco(atualizado.getEndereco());
            return clienteRepository.save(cliente);
        });
    }
    
    public boolean excluir(String id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(String id) {
        return clienteRepository.findById(id);
    }

    public Cliente buscarPorUsuarioId(String usuarioId) { // exceção
        return clienteRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para o usuário: "
                + usuarioId));
    }
}
