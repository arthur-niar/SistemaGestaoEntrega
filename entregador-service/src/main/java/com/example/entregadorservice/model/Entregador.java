package com.example.entregadorservice.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "entregadores")
public class Entregador {
    
    @Id
    private ObjectId id;
    private ObjectId usuarioId;
    private ObjectId pedidoId;
    private String nome;
    private String email;
    private String telefone;
    private String CNH; // Carteira Nacional de Habilitação
    private String veiculo; // deve incluir a placa
    
    
    public Entregador(org.bson.types.ObjectId id, org.bson.types.ObjectId usuarioId, org.bson.types.ObjectId pedidoId,
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

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public ObjectId getUsuarioId() { return usuarioId; }
    public void setUsuarioId(ObjectId usuarioId) { this.usuarioId = usuarioId; }

    public ObjectId getPedidoId() { return pedidoId; }
    public void setPedidoId(ObjectId pedidoId) { this.pedidoId = pedidoId; }

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
