package br.com.etechas.tarefas.entity;

import br.com.etechas.tarefas.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "tb_tarefa")
public class Tarefa {

    @Id
    @Column(name = "id_tarefa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tx_titulo")
    private String titulo;

    @Column(name = "tx_descricao")
    private String descricao;

    @Column(name = "tx_responsavel")
    private String responsavel;

    @Column(name = "dt_data_limite")
    private LocalDate dataLimite;

    @Enumerated(EnumType.STRING)
    @Column(name = "tx_status")
    private StatusEnum status;

    public boolean isPending(){
        return getStatus().equals(StatusEnum.PENDENTE);

    }
}
