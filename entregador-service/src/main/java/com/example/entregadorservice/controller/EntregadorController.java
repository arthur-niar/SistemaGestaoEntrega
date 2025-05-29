package com.example.entregadorservice.controller;

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
    
    @PostMapping("/salvar")
    public ResponseEntity<?> adicionarEntregador(Entregador entregador) {
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

    @PostMapping("/atualizar")
    public ResponseEntity<?> atualizarEntregador(Entregador entregador) {
        if (entregador == null || entregador.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
             "Parâmetros inválidos para atualização do entregador");
        }
        try {
            return ResponseEntity.ok(entregadorService.atualizar(entregador));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirEntregador(ObjectId id) {
        if (!ObjectId.isValid(id.toHexString())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
            "ID inválido");
        }
        try {
            entregadorService.excluir(id);
            return ResponseEntity.ok("Entregador excluído com sucesso.");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/isentregador/{id}")
    public ResponseEntity<?> isEntregador(ObjectId id) { // exceção
        if (!ObjectId.isValid(id.toHexString())) {
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
    public ResponseEntity<?> buscarPorId(ObjectId id) {
        if (!ObjectId.isValid(id.toHexString())) {
            return ResponseEntity.badRequest().body("ID inválido");
        }
        return entregadorService.buscarPorId(id)
                .map(entregador -> ResponseEntity.ok(entregador))
                .orElse(ResponseEntity.notFound().build());
    } 
    
    @RequestMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuarioId(ObjectId usuarioId) { // exceção
        try {
            return ResponseEntity.ok(entregadorService.buscarPorUsuarioId(usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @RequestMapping("/pedido/{pedidoId}")
    public ResponseEntity<?> buscarPorPedidoId(ObjectId pedidoId) { // exceção
        try {
            return ResponseEntity.ok(entregadorService.buscarPorPedidoId(pedidoId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
