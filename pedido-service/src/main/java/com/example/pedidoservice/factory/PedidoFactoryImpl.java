package com.example.pedidoservice.factory;

import com.example.pedidoservice.enums.PedidoEventoTipo;
import com.example.pedidoservice.interfaces.PedidoFactory;
import com.example.pedidoservice.interfaces.PedidoStrategy;
import com.example.pedidoservice.model.Pedido;

public class PedidoFactoryImpl implements PedidoFactory {
    private PedidoStrategy strategy;

    public PedidoFactoryImpl(PedidoStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Pedido criarPedido() {
        Pedido pedido = new Pedido();
        pedido.setStatus(PedidoEventoTipo.PEDIDO_CRIADO);
        strategy.aplicar(pedido);
        return pedido;
    }

    public PedidoStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(PedidoStrategy strategy) {
        this.strategy = strategy;
    }
}