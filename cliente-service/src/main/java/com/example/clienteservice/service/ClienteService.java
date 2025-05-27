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

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void excluir(String id) {
        clienteRepository.deleteById(id);
    }

    public Cliente atualizar(Cliente cliente) {
        if (!clienteRepository.existsById(String.valueOf(cliente.getId()))) {
            throw new RuntimeException("Cliente não encontrado para atualização: "
             + cliente.getId());
        }
        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorUsuarioId(String usuarioId) {
        return clienteRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para o usuário: "
                 + usuarioId));
    }
    
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(String id) {
        return clienteRepository.findById(id);
    }
}
