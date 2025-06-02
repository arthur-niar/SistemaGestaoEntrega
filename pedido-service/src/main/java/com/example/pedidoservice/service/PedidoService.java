package com.example.pedidoservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pedidoservice.enums.PedidoEventoTipo;
import com.example.pedidoservice.factory.PedidoFactoryImpl;
import com.example.pedidoservice.interfaces.PedidoObserver;
import com.example.pedidoservice.interfaces.PedidoStrategy;
import com.example.pedidoservice.model.Pedido;
import com.example.pedidoservice.observer.PedidoLoggerObserver;
import com.example.pedidoservice.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    private List<PedidoObserver> observers = List.of(new PedidoLoggerObserver());
public Pedido salvar(Pedido pedido) {
    pedido.setStatus(PedidoEventoTipo.PEDIDO_CRIADO); 

   
    Pedido salvo = pedidoRepository.save(pedido);

  
    notificarEvento(salvo, PedidoEventoTipo.PEDIDO_CRIADO);
    notificarObservers(salvo);

    return salvo;
}


    public Pedido criarPedidoComEstrategia(PedidoStrategy strategy) {
        Pedido pedido = new PedidoFactoryImpl(strategy).criarPedido();
        notificarEvento(pedido, PedidoEventoTipo.PEDIDO_CRIADO);
        notificarObservers(pedido);
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> atualizar(String id, Pedido atualizado) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setStatus(PedidoEventoTipo.PEDIDO_ATUALIZADO);
            pedido.setDataHora(atualizado.getDataHora());
            pedido.setTaxaEntrega(atualizado.getTaxaEntrega());
            pedido.setDescricao(atualizado.getDescricao());
            notificarEvento(pedido, PedidoEventoTipo.PEDIDO_ATUALIZADO);
            notificarObservers(pedido);
            return pedidoRepository.save(pedido);
        });
    }

   public boolean excluir(String id) {
    Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);

    if (pedidoOptional.isPresent()) {
        Pedido pedido = pedidoOptional.get();

       
        pedido.setStatus(PedidoEventoTipo.PEDIDO_EXCLUIDO);

        
        notificarEvento(pedido, PedidoEventoTipo.PEDIDO_EXCLUIDO);
        notificarObservers(pedido);

        
        pedidoRepository.deleteById(id);
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

    public Iterable<Pedido> buscarPorStatus(String status) {
        return pedidoRepository.findByStatus(status);
    }

    public Iterable<Pedido> buscarPorDataHora(LocalDateTime start, LocalDateTime end) {
        return pedidoRepository.findByDataHoraBetween(start, end);
    }

    public Iterable<Pedido> buscarPorTaxaEntrega(double taxaEntrega) {
        return pedidoRepository.findByTaxaEntrega(taxaEntrega);
    }

    private void notificarEvento(Pedido pedido, PedidoEventoTipo tipoEvento) {
        for (PedidoObserver obs : observers) {
            obs.notificarEvento(pedido, tipoEvento);
        }
    }

    private void notificarObservers(Pedido pedido) {
        for (PedidoObserver obs : observers) {
            obs.notificarPedido(pedido);
        }
    }

    public PedidoRepository getPedidoRepository() {
        return pedidoRepository;
    }

    public void setPedidoRepository(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<PedidoObserver> getObservers() {
        return observers;
    }

    public void setObservers(List<PedidoObserver> observers) {
        this.observers = observers;
    }
    
}
