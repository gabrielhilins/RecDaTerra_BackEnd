package com.example.projetopoobackendequipe4.controller;

import com.example.projetopoobackendequipe4.exception.TipoDeUsuarioInexistenteException;
import com.example.projetopoobackendequipe4.exception.UsuarioNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.Cliente;
import com.example.projetopoobackendequipe4.model.Produtor;
import com.example.projetopoobackendequipe4.model.Usuario;
import com.example.projetopoobackendequipe4.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para cadastrar um novo usuário (Cliente ou Produtor)
    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@RequestBody Usuario usuario) {
        try {
            // Chama o serviço para criar um novo usuário
            Usuario novoUsuario = usuarioService.criarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (TipoDeUsuarioInexistenteException e) {
            // Retorna erro 400 (Bad Request) se o tipo de usuário não for suportado
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para buscar um usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Long id) {
        // Chama o serviço para buscar um usuário pelo ID
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);
        // Retorna 200 (OK) com o usuário se encontrado, ou 404 (Not Found) se não encontrado
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para buscar um usuário pelo email
    @GetMapping("/buscaremail")
    public ResponseEntity<?> buscarUsuarioPorEmail(@RequestParam String email) {
        // Chama o serviço para buscar um usuário pelo email
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorEmail(email);
        // Retorna 200 (OK) com o usuário se encontrado, ou 404 (Not Found) se não encontrado
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint para atualizar um usuário
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        try {
            // Chama o serviço para atualizar um usuário pelo ID
            Usuario usuario = usuarioService.atualizarUsuario(usuarioAtualizado);
            // Retorna 200 (OK) com o usuário atualizado
            return ResponseEntity.ok(usuario);
        } catch (UsuarioNaoEncontradoException e) {
            // Retorna 404 (Not Found) se o usuário não foi encontrado para atualização
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para excluir um usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Long id) {
        // Chama o serviço para excluir um usuário pelo ID
        usuarioService.excluirUsuario(id);
        // Retorna 204 (No Content) indicando que a operação foi realizada com sucesso
        return ResponseEntity.noContent().build();
    }

    // Endpoint para listar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        // Chama o serviço para listar todos os usuários
        List<Usuario> usuarios = usuarioService.listarTodosUsuarios();
        // Retorna 200 (OK) com a lista de usuários
        return ResponseEntity.ok(usuarios);
    }

    // Endpoint para autenticar um usuário
    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticarUsuario(@RequestParam String email, @RequestParam String senha) {
        try {
            // Chama o serviço para autenticar um usuário pelo email e senha
            Usuario usuarioAutenticado = usuarioService.autenticarUsuario(email, senha);
            // Retorna 200 (OK) com o usuário autenticado
            return ResponseEntity.ok(usuarioAutenticado);
        } catch (UsuarioNaoEncontradoException e) {
            // Retorna erro 400 (Bad Request) se o usuário não foi encontrado ou a senha está incorreta
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
