package com.example.entregadorservice.controller;

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

import com.example.entregadorservice.model.Entregador;
import com.example.entregadorservice.service.EntregadorService;

@RestController
@RequestMapping("/api/entregadores")
public class EntregadorController {

    @Autowired
    private EntregadorService entregadorService;

    @RequestMapping("/teste")
    public String ping() {
        return "Entregador Service está online!";
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<?> adicionarEntregador(@RequestBody Entregador entregador) {
        if (entregador == null || entregador.getUsuarioId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
             "Entregador ou ID de usuário inválido");
        }
        try {
            return ResponseEntity.ok(entregadorService.salvar(entregador));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> atualizarEntregador(@PathVariable String id, @RequestBody Entregador entregador) {
        if (entregador == null || entregador.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
             "Parâmetros inválidos para atualização do entregador");
        }
        try {
            return ResponseEntity.ok(entregadorService.atualizar(id, entregador));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}") // excluir
    public ResponseEntity<?> excluir(@PathVariable String id) {
        boolean removido = entregadorService.excluir(id);
        if (removido) {
            return ResponseEntity.ok("Entregador removido com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/isentregador/{id}")
    public ResponseEntity<?> isEntregador(@PathVariable String id) { // exceção
        if (!ObjectId.isValid(id)) {
            return ResponseEntity.badRequest().body("ID inválido");
        }
        try {
            boolean isCliente = entregadorService.isEntregador(id);
            return ResponseEntity.ok(isCliente);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    
    @RequestMapping("/todos")
    public ResponseEntity<Iterable<?>> buscarTodosEntregadores() {
        return ResponseEntity.ok(entregadorService.buscarTodos());
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        if (!ObjectId.isValid(id)) {
            return ResponseEntity.badRequest().body("ID inválido");
        }
        return entregadorService.buscarPorId(id)
                .map(entregador -> ResponseEntity.ok(entregador))
                .orElse(ResponseEntity.notFound().build());
    } 
    
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuarioId(@PathVariable String usuarioId) { // exceção
        try {
            return ResponseEntity.ok(entregadorService.buscarPorUsuarioId(usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<?> buscarPorPedidoId(@PathVariable String pedidoId) { // exceção
        try {
            return ResponseEntity.ok(entregadorService.buscarPorPedidoId(pedidoId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
