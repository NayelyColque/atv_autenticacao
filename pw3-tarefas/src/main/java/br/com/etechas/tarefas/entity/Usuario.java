package br.com.etechas.tarefas.entity;

import br.com.etechas.tarefas.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;

import javax.naming.Name;

@Entity
@Table(name = "tb_usuario")
@Data
public class Usuario {
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String username;

    @Column(name = "senha")
    private String password;

    @Column(name = "role")
    private RoleEnum role;


}
