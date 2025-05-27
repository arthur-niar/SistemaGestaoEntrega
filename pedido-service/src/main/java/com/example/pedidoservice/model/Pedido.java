package com.example.pedidoservice.model;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pedidos")
public class Pedido {

    @Id
    private ObjectId id; //usar objectid pro mongodb
    private boolean status;
    private LocalDateTime dataHora; //usar localdatetime pro mongodb
    private double taxaEntrega;
    
    public Pedido(ObjectId id, boolean status, LocalDateTime dataHora, double taxaEntrega) {
        this.id = id;
        this.status = status;
        this.dataHora = dataHora;
        this.taxaEntrega = taxaEntrega;
    }

    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
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