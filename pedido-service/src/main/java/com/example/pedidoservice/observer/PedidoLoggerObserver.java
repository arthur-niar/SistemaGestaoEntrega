package com.example.pedidoservice.observer;

import com.example.pedidoservice.enums.PedidoEventoTipo;
import com.example.pedidoservice.interfaces.PedidoObserver;
import com.example.pedidoservice.model.Pedido;

public class PedidoLoggerObserver implements PedidoObserver {

    @Override
    public void notificarPedido(Pedido pedido) {
        if (pedido != null) {
            System.out.println("[OBSERVER] Pedido atualizado:");
            System.out.println("   ID: " + pedido.getId());
            System.out.println("   Status: " + pedido.getStatus());
            System.out.println("   Taxa de Entrega: " + pedido.getTaxaEntrega());
            System.out.println("   Data/Hora: " + pedido.getDataHora());
        } else {
            System.out.println("[OBSERVER] Pedido nulo ao tentar notificar.");
        }
    }

    @Override
    public void notificarEvento(Pedido pedido, PedidoEventoTipo tipoEvento) {
        System.out.println("[EVENTO] Tipo: " + tipoEvento.name());
        System.out.println("   Descrição: " + tipoEvento.getDescricao());

        if (pedido != null) {
            System.out.println("   Pedido ID: " + pedido.getId());
            System.out.println("   Status Atual: " + (pedido.getStatus() != null ? pedido.getStatus() : "Indefinido"));
        } else {
            System.out.println("   Pedido ID: (pedido deletado)");
            System.out.println("   Status Atual: N/A");
        }
    }
}
