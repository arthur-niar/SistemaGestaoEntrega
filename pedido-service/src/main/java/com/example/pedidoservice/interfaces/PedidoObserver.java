package com.example.pedidoservice.interfaces;

import com.example.pedidoservice.enums.PedidoEventoTipo;
import com.example.pedidoservice.model.Pedido;

public interface PedidoObserver {

    void notificarPedido(Pedido pedido);
    void notificarEvento(Pedido pedido, PedidoEventoTipo tipoEvento);
}