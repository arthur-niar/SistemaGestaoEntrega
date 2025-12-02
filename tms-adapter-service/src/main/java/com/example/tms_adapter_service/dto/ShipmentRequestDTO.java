package com.example.tms_adapter_service.dto;

public class ShipmentRequestDTO {

    private String pedidoId;
    private String descricao;
    private double valorEntrega;

    public String getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getValorEntrega() {
        return valorEntrega;
    }
    public void setValorEntrega(double valorEntrega) {
        this.valorEntrega = valorEntrega;
    }
}
