package com.example.pedidoservice.enums;

public enum PedidoEventoTipo {
    PEDIDO_CRIADO("Pedido foi criado."),
    PEDIDO_ATUALIZADO("Pedido foi atualizado."),
    PEDIDO_EXCLUIDO("Pedido foi excluído."),
    PEDIDO_ENTREGUE("Pedido foi entregue."),
    PEDIDO_PAGO("Pedido foi pago.");

    private final String descricao;

    PedidoEventoTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
