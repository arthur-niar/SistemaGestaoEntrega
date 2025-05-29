package com.example.clienteservice.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.clienteservice.model.Cliente;
import com.example.clienteservice.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/teste")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pedido Service está online!");
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> adicionarCliente(Cliente cliente) { // exceção
        try {
            return ResponseEntity.ok(clienteService.salvar(cliente));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/atualizar")
    public ResponseEntity<?> atualizarCliente(Cliente cliente) {
        if (cliente == null || cliente.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
            "Parâmetros inválidos para atualização do cliente");
        }
        try {
            return ResponseEntity.ok(clienteService.atualizar(cliente));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirCliente(ObjectId id) { // exceção
        try {
            clienteService.excluir(id);
            return ResponseEntity.ok("Cliente excluído com sucesso.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/iscliente/{usuarioId}")
    public ResponseEntity<Boolean> isCliente(ObjectId usuarioId) {
        if (usuarioId == null || !ObjectId.isValid(usuarioId.toHexString())) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
            "ID inválido");
        }
        try {
            boolean isCliente = clienteService.isCliente(usuarioId);
            return ResponseEntity.ok(isCliente);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<Iterable<?>> buscarTodosClientes() {
        return ResponseEntity.ok(clienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(ObjectId id) { // adicionar exceção para id inválido
        return clienteService.buscarPorId(id)
        .map(cliente -> ResponseEntity.ok(cliente))
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuarioId(ObjectId usuarioId) { // exceção 
        try {
            return ResponseEntity.ok(clienteService.buscarPorUsuarioId(usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
