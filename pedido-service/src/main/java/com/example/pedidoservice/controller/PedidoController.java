package com.example.pedidoservice.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pedidoservice.dto.PedidoDTO;
import com.example.pedidoservice.interfaces.PedidoStrategy;
import com.example.pedidoservice.model.Pedido;
import com.example.pedidoservice.service.PedidoService;
import com.example.pedidoservice.strategy.EntregaAgendadaStrategy;
import com.example.pedidoservice.strategy.EntregaRapidaStrategy;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/teste")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pedido Service está online!");
    }

    @PostMapping("/adicionar")
    public ResponseEntity<PedidoDTO> adicionarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.salvar(pedido);
        return ResponseEntity.ok(new PedidoDTO(novoPedido));
    }

    @PostMapping("/adicionar/{tipo}")
    public ResponseEntity<PedidoDTO> adicionarPedidoPorTipo(@PathVariable String tipo) {
        Pedido pedido;
        if ("rapida".equalsIgnoreCase(tipo)) {
            pedido = pedidoService.criarPedidoComEstrategia(new EntregaRapidaStrategy());
        } else if ("agendada".equalsIgnoreCase(tipo)) {
            pedido = pedidoService.criarPedidoComEstrategia(new EntregaAgendadaStrategy());
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new PedidoDTO(pedido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable String id, @RequestBody Pedido pedidoAtualizado) {
        Optional<Pedido> atualizado = pedidoService.atualizar(id, pedidoAtualizado);
        return atualizado.map(p -> ResponseEntity.ok(new PedidoDTO(p)))
                         .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPedido(@PathVariable String id) {
        boolean removido = pedidoService.excluir(id);
        if (removido) {
            return ResponseEntity.ok("Pedido removido com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPedidoPorId(@PathVariable String id) {
        return pedidoService.buscarPorId(id)
                .map(p -> ResponseEntity.ok(new PedidoDTO(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PedidoDTO>> buscarTodosPedidos() {
        List<PedidoDTO> pedidos = ((List<Pedido>) pedidoService.buscarTodos())
                .stream()
                .map(PedidoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PedidoDTO>> buscarPedidosPorStatus(@PathVariable String status) {
        List<PedidoDTO> pedidos = ((List<Pedido>) pedidoService.buscarPorStatus(status))
                .stream()
                .map(PedidoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/data")
    public ResponseEntity<List<PedidoDTO>> buscarPorIntervaloDeData(
            @RequestParam("inicio") LocalDateTime inicio,
            @RequestParam("fim") LocalDateTime fim) {
        List<PedidoDTO> pedidos = ((List<Pedido>) pedidoService.buscarPorDataHora(inicio, fim))
                .stream()
                .map(PedidoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/taxa/{taxa}")
    public ResponseEntity<List<PedidoDTO>> buscarPedidosPorTaxa(@PathVariable Double taxa) {
        List<PedidoDTO> pedidos = ((List<Pedido>) pedidoService.buscarPorTaxaEntrega(taxa))
                .stream()
                .map(PedidoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pedidos);
    }
    @GetMapping("/custo-entrega/{tipo}")
    public ResponseEntity<Double> calcularCustoEntrega(@PathVariable String tipo) {
        Pedido pedido = new Pedido();
        PedidoStrategy strategy;

        if ("rapida".equalsIgnoreCase(tipo)) {
            strategy = new EntregaRapidaStrategy();
        } else if ("agendada".equalsIgnoreCase(tipo)) {
            strategy = new EntregaAgendadaStrategy();
        } else {
            return ResponseEntity.badRequest().build();
        }

        strategy.aplicar(pedido);
        double custo = strategy.calcularCustoEntrega(pedido);
        return ResponseEntity.ok(custo);
    }
}
