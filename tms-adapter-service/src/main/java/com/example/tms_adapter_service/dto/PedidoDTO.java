package com.example.tms_adapter_service.dto;

import java.time.LocalDateTime;

public class PedidoDTO {
    private String id;
    private String descricao;
    private String status;
    private LocalDateTime dataHora;
    private double taxaEntrega;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    public double getTaxaEntrega() {
        return taxaEntrega;
    }
    public void setTaxaEntrega(double taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }
}
