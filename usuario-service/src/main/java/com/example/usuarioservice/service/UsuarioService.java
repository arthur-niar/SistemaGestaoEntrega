package com.example.usuarioservice.service;

import com.example.usuarioservice.model.Usuario;
import com.example.usuarioservice.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> autenticar(String nome, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNome(nome);
        return usuarioOpt.filter(usuario -> usuario.getSenha().equals(senha));
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> atualizar(String id, Usuario atualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(atualizado.getNome());
            usuario.setEmail(atualizado.getEmail());
            usuario.setSenha(atualizado.getSenha());
            usuario.setTipo(atualizado.getTipo());
            return usuarioRepository.save(usuario);
        });
    }

    public boolean excluir(String id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}