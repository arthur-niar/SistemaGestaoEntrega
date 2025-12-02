package com.example.tms_fake_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entregas_fake")
public class Shipment {

    @Id
    private String id;

    private String pedidoId;
    private String trackingCode;
    private String status;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }
    public String getTrackingCode() {
        return trackingCode;
    }
    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    } 

    @Override
    public String toString() {
    return "Shipment{" +
           "id='" + id + '\'' +
           ", pedidoId='" + pedidoId + '\'' +
           ", trackingCode='" + trackingCode + '\'' +
           ", status='" + status + '\'' +
           '}';
    }
}
