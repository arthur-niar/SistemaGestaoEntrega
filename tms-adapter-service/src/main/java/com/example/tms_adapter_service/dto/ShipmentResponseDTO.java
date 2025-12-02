package com.example.tms_adapter_service.dto;

public class ShipmentResponseDTO {

    private String trackingCode;
    private String status;

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
}
