package br.com.etechas.tarefas.controller;

import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping

public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.registrar(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    //@GetMapping
   // public List<Usuario> findAll(){
     //   return
   // }
}
