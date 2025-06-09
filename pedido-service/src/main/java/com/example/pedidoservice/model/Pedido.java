package com.example.pedidoservice.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.pedidoservice.enums.PedidoEventoTipo;

@Document(collection = "pedidos")
public class Pedido {

    @Id
    private String id;
    private String descricao; 
    private PedidoEventoTipo status;
    private LocalDateTime dataHora;
    private double taxaEntrega;
   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PedidoEventoTipo getStatus() {
        return status;
    }

    public void setStatus(PedidoEventoTipo status) {
        this.status = status;
    }
}
