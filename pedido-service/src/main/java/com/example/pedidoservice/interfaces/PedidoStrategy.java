package com.example.pedidoservice.interfaces;

import com.example.pedidoservice.model.Pedido;

public interface PedidoStrategy {
    void aplicar(Pedido pedido);
    double calcularCustoEntrega(Pedido pedido);
}
