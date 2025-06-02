package com.example.pedidoservice.dto;

import java.time.LocalDateTime;

import com.example.pedidoservice.enums.PedidoEventoTipo;
import com.example.pedidoservice.model.Pedido;

public class PedidoDTO {
    private String id;
    private String descricao; 
    private PedidoEventoTipo status;
    private LocalDateTime dataHora;
    private double taxaEntrega;

    public PedidoDTO(Pedido pedido) {
        this.id = pedido.getId();
        this.descricao = pedido.getDescricao(); 
        this.status = pedido.getStatus();
        this.dataHora = pedido.getDataHora();
        this.taxaEntrega = pedido.getTaxaEntrega();
    }

    public String getId() {
        return id;
    }

    public String getDescricao() {
        return descricao; 
    }

    public PedidoEventoTipo getStatus() {
        return status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public double getTaxaEntrega() {
        return taxaEntrega;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao; 
    }

    public void setStatus(PedidoEventoTipo status) {
        this.status = status;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public void setTaxaEntrega(double taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }
}
