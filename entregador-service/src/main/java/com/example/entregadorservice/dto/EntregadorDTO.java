package com.example.entregadorservice.dto;

public class EntregadorDTO {
    
    private String id;
    private String usuarioId;
    private String pedidoId;
    private String nome;
    private String email;
    private String telefone;
    private String CNH; // Carteira Nacional de Habilitação
    private String veiculo; // deve incluir a placa
    
    
    public EntregadorDTO(String id, String usuarioId, String pedidoId,
            String nome, String email, String telefone, String CNH, String veiculo) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.pedidoId = pedidoId;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.CNH = CNH;
        this.veiculo = veiculo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public String getPedidoId() { return pedidoId; }
    public void setPedidoId(String pedidoId) { this.pedidoId = pedidoId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCNH() { return CNH; }
    public void setCNH(String cNH) { this.CNH = cNH; }

    public String getVeiculo() { return veiculo; }
    public void setVeiculo(String veiculo) { this.veiculo = veiculo; }
    
}