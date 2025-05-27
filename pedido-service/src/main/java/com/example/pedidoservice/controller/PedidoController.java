package com.example.pedidoservice.controller;
import com.example.pedidoservice.service.PedidoService;
import com.example.pedidoservice.model.Pedido;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {  // Aplicar tratamento de exceções e validações nessa classe
    
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/teste")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pedido Service está online!");
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Pedido> adicionarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.salvar(pedido);
        return ResponseEntity.ok(novoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPedido(String id, @RequestBody Pedido pedidoAtualizado) {
        return pedidoService.atualizar(id, pedidoAtualizado)
                .map(pedido -> ResponseEntity.ok(pedido))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPedido(String id) {
        boolean removido = pedidoService.excluir(id);
        if (removido) {
            return ResponseEntity.ok("Pedido removido com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(String id) {
        return pedidoService.buscarPorId(id)
                .map(pedido -> ResponseEntity.ok(pedido))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/todos")
    public ResponseEntity<Iterable<Pedido>> buscarTodosPedidos() {
        Iterable<Pedido> pedidos = pedidoService.buscarTodos();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Iterable<Pedido>> buscarPedidosPorStatus(@org.springframework.web.bind.annotation.PathVariable String status) {
        Iterable<Pedido> pedidos = pedidoService.buscarPorStatus(status);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/data") // esse método não faz muito sentido sem front
    public ResponseEntity<Iterable<Pedido>> buscarPorIntervaloDeData(
            @org.springframework.web.bind.annotation.RequestParam("inicio") LocalDateTime inicio,
            @org.springframework.web.bind.annotation.RequestParam("fim") LocalDateTime fim) {
        // parâmetros precisam estar no formato ISO_LOCAL_DATE_TIME (ex: 2024-06-10T15:30:00)
        LocalDateTime dataInicio = inicio;
        LocalDateTime dataFim = fim;
        Iterable<Pedido> pedidos = pedidoService.buscarPorDataHora(dataInicio, dataFim);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/taxa/{taxa}")
    public ResponseEntity<Iterable<Pedido>> buscarPedidosPorTaxa(@org.springframework.web.bind.annotation.PathVariable Double taxa) {
        Iterable<Pedido> pedidos = pedidoService.buscarPorTaxaEntrega(taxa);
        return ResponseEntity.ok(pedidos);
    }
}
