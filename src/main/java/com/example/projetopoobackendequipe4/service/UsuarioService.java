package com.example.projetopoobackendequipe4.service;

import com.example.projetopoobackendequipe4.exception.NotificacaoNaoEncontrada;
import com.example.projetopoobackendequipe4.exception.TipoDeUsuarioInexistenteException;
import com.example.projetopoobackendequipe4.exception.UsuarioNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.Cliente;
import com.example.projetopoobackendequipe4.model.Produtor;
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

        // Verifica se o usuário é um Cliente ou Produtor
        if (usuario instanceof Cliente) {
            return usuarioRepository.save((Cliente) usuario);
        } else if (usuario instanceof Produtor) {
            return usuarioRepository.save((Produtor) usuario);
        } else {
            throw new TipoDeUsuarioInexistenteException("Tipo de usuário não suportado");
        }
    }

    public Usuario buscarUsuarioPorId(Long id) throws UsuarioNaoEncontradoException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado"));
    }

    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario atualizarUsuario(Long id, Usuario detalhesDoUsuario) throws UsuarioNaoEncontradoException {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado"));

        usuarioExistente.setNomeUsuario(detalhesDoUsuario.getNomeUsuario());
        return usuarioRepository.save(usuarioExistente);
    }

    public void deletarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado por Id");
        }
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
