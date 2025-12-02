package com.example.tms_adapter_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shipments")
public class Shipment {

    @Id
    private String id;

    private String pedidoId;
    private String trackingCode;
    private String status;

    public Shipment(String pedidoId, String trackingCode, String status) {
        this.pedidoId = pedidoId;
        this.trackingCode = trackingCode;
        this.status = status;
    }

    public String getId() { return id; }
    public String getPedidoId() { return pedidoId; }
    public String getTrackingCode() { return trackingCode; }
    public String getStatus() { return status; }

    public void setId(String id) { this.id = id; }
    public void setPedidoId(String pedidoId) { this.pedidoId = pedidoId; }
    public void setTrackingCode(String trackingCode) { this.trackingCode = trackingCode; }
    public void setStatus(String status) { this.status = status; }
}
