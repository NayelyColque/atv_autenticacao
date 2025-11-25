package br.com.etechas.tarefas.service;

import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.enums.StatusEnum;
import br.com.etechas.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public List<Tarefa> listar(){
        return repository.findAll();
    }

    public boolean deletarPorId(Long id){
        var tarefa = repository.findById(id);
        if(tarefa.isEmpty()){
            return false;
        }
        if(tarefa.get().isPending()){
            repository.deleteById(id);
            return true;
        }
        throw new RuntimeException("Tarefa em progresso ou concluída");
    }
    public Tarefa save(Tarefa tarefa) {

        if(tarefa.getDataLimite().isBefore(LocalDate.now()))
            throw new RuntimeException("Data limite deve ser superior a data atual.");
        tarefa.setStatus(StatusEnum.PENDENTE);
        return repository.save(tarefa);

    }

    public Tarefa alteracao(Long id, Tarefa tarefa) {
        var tarefaConsultada =  repository.findById(id);
        if(tarefaConsultada.isPresent()){
            var novaTarefa = tarefaConsultada.get();
            novaTarefa.setId(tarefa.getId());
            novaTarefa.setStatus(tarefa.getStatus());
            novaTarefa.setDescricao(tarefa.getDescricao());
            novaTarefa.setTitulo(tarefa.getTitulo());
            novaTarefa.setDataLimite(tarefa.getDataLimite());
            novaTarefa.setResponsavel(tarefa.getResponsavel());
            return repository.save(novaTarefa);
        }
        throw new RuntimeException("Tarefa não existe");
    }

}
