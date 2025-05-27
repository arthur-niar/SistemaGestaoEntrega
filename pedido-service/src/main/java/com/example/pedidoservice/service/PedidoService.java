package com.example.pedidoservice.service;

import com.example.pedidoservice.enums.PedidoEventoTipo;
import com.example.pedidoservice.interfaces.PedidoObserver;
import com.example.pedidoservice.model.Pedido;
import com.example.pedidoservice.repository.PedidoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvar(Pedido pedido) {
        notificarEvento(pedido, PedidoEventoTipo.PEDIDO_CRIADO);
        notificarObservers(pedido);
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> atualizar(String id, Pedido atualizado) {
        return pedidoRepository.findById(id).map(pedido -> {
            //pedido.setClienteId(atualizado.getClienteId());
            pedido.setStatus(atualizado.isStatus());
            pedido.setDataHora(atualizado.getDataHora());
            pedido.setTaxaEntrega(atualizado.getTaxaEntrega());
            notificarEvento(pedido, PedidoEventoTipo.PEDIDO_ATUALIZADO);
            notificarObservers(pedido);
            return pedidoRepository.save(pedido);
        });
    }

    public boolean excluir(String id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            notificarEvento(null, PedidoEventoTipo.PEDIDO_EXCLUIDO);
            return true;
        }
        return false;
    }

    public Optional<Pedido> buscarPorId(String id) {
        return pedidoRepository.findById(id);
    }

    public Iterable<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    public Iterable<Pedido> buscarPorClienteId(String clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public Iterable<Pedido> buscarPorStatus(String status) {
        return pedidoRepository.findByStatus(status);
    }

    public Iterable<Pedido> buscarPorDataHora(LocalDateTime start, LocalDateTime end) {
        return pedidoRepository.findByDataHoraBetween(start, end);
    }

    public Iterable<Pedido> buscarPorTaxaEntrega(double taxaEntrega) {
        return pedidoRepository.findByTaxaEntrega(taxaEntrega);
    }

    // LÓGICA DE OBSERVERS ABAIXO

    private List<PedidoObserver> observer;

    private void notificarEvento(Pedido pedido, PedidoEventoTipo tipoevento) {
        System.out.println("[LOG] Evento: " + tipoevento + " | Pedido: " + pedido);
        for (PedidoObserver obs : observer) {
            obs.notificarEvento(pedido, tipoevento);
        }
    }
    
    private void notificarObservers(Pedido pedido) {
        System.out.println("[LOG] Notificando observers sobre alteração no pedido: " 
        + pedido.getId());
        for (PedidoObserver obs : observer) {
            obs.notificarPedido(pedido);
        }
    }
}
