package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.entity.Usuario;
import br.com.etechas.tarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public  Usuario registrar(Usuario cadastro){
        var existe = repository.findByUsername(cadastro.getUsername());
        if (existe.isPresent()){
            throw  new RuntimeException("Nome de usuario ja existe");
        }
        var salvo = repository.save(cadastro);
        return salvo;
    }

}
