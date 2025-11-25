package br.com.etechas.tarefas.controller;

import br.com.etechas.tarefas.entity.Tarefa;
import br.com.etechas.tarefas.service.TarefaService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listar(){
        return tarefaService.listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(tarefaService.deletarPorId(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Tarefa> create(@RequestBody Tarefa tarefa) {
        Tarefa createdTask = tarefaService.save(tarefa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTask.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdTask);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> update(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        Tarefa updatedTask = tarefaService.alteracao(id, tarefa);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
