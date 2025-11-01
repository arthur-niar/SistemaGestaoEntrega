package com.example.pedidoservice.strategy;

import java.time.LocalDateTime;

import com.example.pedidoservice.interfaces.PedidoStrategy;
import com.example.pedidoservice.model.Pedido;

public class EntregaAgendadaStrategy implements PedidoStrategy {
    @Override
    public void aplicar(Pedido pedido) {
        pedido.setTaxaEntrega(10.0);
        pedido.setDataHora(LocalDateTime.now().plusHours(2));
    }

    @Override
    public double calcularCustoEntrega(Pedido pedido) {
        return pedido.getTaxaEntrega();
    }
}
