package com.example.projetopoobackendequipe4.service;

import com.example.projetopoobackendequipe4.exception.UsuarioNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.Usuario;
import com.example.projetopoobackendequipe4.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario criarUsuario(Usuario usuario) {
        // Antes de salvar, codifica a senha do usuário
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario atualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario autenticarUsuario(String email, String senha) throws UsuarioNaoEncontradoException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        // Verifica se a senha fornecida corresponde à senha armazenada no banco de dados
        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new UsuarioNaoEncontradoException("Senha incorreta");
        }

        // Se chegou até aqui, a autenticação foi bem-sucedida
        return usuario;
    }
}
