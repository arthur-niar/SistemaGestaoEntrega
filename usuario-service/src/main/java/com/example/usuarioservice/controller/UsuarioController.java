package com.example.usuarioservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuarioservice.dto.LoginDTO;
import com.example.usuarioservice.model.Usuario;
import com.example.usuarioservice.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/teste")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Usuário Service está online!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        return usuarioService.autenticar(login.getNome(), login.getSenha())
                .map(usuario -> ResponseEntity.ok("Login bem-sucedido!"))
                .orElse(ResponseEntity.status(401).body("Nome de usuário ou senha inválidos"));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario novo = usuarioService.salvar(usuario);
        return ResponseEntity.ok(novo);
    }

    @PutMapping("/{id}") // atualizar
    public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioService.atualizar(id, usuarioAtualizado)
                .map(usuario -> ResponseEntity.ok(usuario))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // excluir
    public ResponseEntity<?> excluir(@PathVariable String id) {
        boolean removido = usuarioService.excluir(id);
        if (removido) {
            return ResponseEntity.ok("Usuário removido com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

