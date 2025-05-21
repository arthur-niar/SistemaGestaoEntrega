package com.example.sistemaDeEntregas.controller;

import com.example.sistemaDeEntregas.dto.LoginDTO;
import com.example.sistemaDeEntregas.model.Usuario;
import com.example.sistemaDeEntregas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        return usuarioService.autenticar(login.getEmail(), login.getSenha())
                .map(usuario -> ResponseEntity.ok("Login bem-sucedido!"))
                .orElse(ResponseEntity.status(401).body("Email ou senha inválidos"));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario novo = usuarioService.salvar(usuario);
        return ResponseEntity.ok(novo);
    }
}
