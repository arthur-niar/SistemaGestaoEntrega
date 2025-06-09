package com.example.clienteservice.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/cadastrar")
    public ResponseEntity<?> adicionarCliente(@RequestBody Cliente cliente) { // exceção
        try {
            Cliente novo = clienteService.salvar(cliente);
            return ResponseEntity.ok(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400)
            .body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable String id, @RequestBody Cliente clienteAtualizado) {
        if (clienteAtualizado == null || clienteAtualizado.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
            "Parâmetros inválidos para atualização do cliente");
        }
        try {
            return clienteService.atualizar(id, clienteAtualizado)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirCliente(@PathVariable String id) {
        try {
            return clienteService.excluir(id)
                ? ResponseEntity.ok("Cliente removido com sucesso.")
                : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/iscliente/{usuarioId}")
    public ResponseEntity<Boolean> isCliente(@PathVariable String usuarioId) {
        if (usuarioId == null || !ObjectId.isValid(usuarioId)) {
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
    public ResponseEntity<?> buscarPorId(@PathVariable String id) { // adicionar exceção para id inválido
        return clienteService.buscarPorId(id)
        .map(cliente -> ResponseEntity.ok(cliente))
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuarioId(@PathVariable String usuarioId) { // exceção 
        try {
            return ResponseEntity.ok(clienteService.buscarPorUsuarioId(usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
