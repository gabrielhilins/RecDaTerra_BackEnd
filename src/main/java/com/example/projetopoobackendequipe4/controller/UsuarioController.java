package com.example.projetopoobackendequipe4.controller;

import com.example.projetopoobackendequipe4.exception.TipoDeUsuarioInexistenteException;
import com.example.projetopoobackendequipe4.exception.UsuarioNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.Usuario;
import com.example.projetopoobackendequipe4.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/criar")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.criarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (TipoDeUsuarioInexistenteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable("id") Long id) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/atualizar")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario detalhesDoUsuario) {
        try {
            Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, detalhesDoUsuario);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/deletar")
    public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Long id) {
        try {
            usuarioService.deletarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/autenticar")
    public ResponseEntity<String> autenticarUsuario(@RequestParam String email, @RequestParam String senha) {
        try {
            Usuario usuarioAutenticado = usuarioService.autenticarUsuario(email, senha);
            return ResponseEntity.ok("Usuário autenticado com sucesso!");
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação: " + e.getMessage());
        }
    }
}
