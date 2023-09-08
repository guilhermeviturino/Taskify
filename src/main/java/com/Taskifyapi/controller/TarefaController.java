package com.Taskifyapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Taskifyapi.dto.TarefaDTO;
import com.Taskifyapi.model.Tarefa;
import com.Taskifyapi.repository.TarefaRepository;
import com.Taskifyapi.service.TarefaService;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {
    
    @PostMapping
    public ResponseEntity<Tarefa> cadastrarNovaTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.save(tarefa));
    }

    @GetMapping
    public ResponseEntity<Page<Tarefa>> listarTarefas(Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> listarTarefaPeloId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        
        if (tarefa.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(tarefa.get());
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Tarefa> obterTarefasDoUsuario(@PathVariable("idUsuario") Long idUsuario) {
        return tarefaRepository.findByUsuario(idUsuario);
    }

    @GetMapping("/projeto/{idProjeto}")
    public List<Tarefa> obterTarefaDoProjeto(@PathVariable("idProjeto") Long idProjeto) {
        return tarefaRepository.findByProjeto(idProjeto); 
    }

    @GetMapping("/projeto/{idProjeto}/usuario/{idUsuario}")
    public List<Tarefa> filtrarTarefasPorUsuarioEProjeto(@PathVariable("idProjeto") Long idProjeto, @PathVariable("idUsuario") Long Idusuario) {
        return tarefaRepository.findByProjetoAndUsuario(idProjeto, Idusuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarTarefaPeloId(@PathVariable Long id) {
       Optional<Tarefa> tarefa = tarefaRepository.findById(id);

       if (tarefa.isEmpty()) {
        ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }

       tarefaRepository.deleteById(id);

       return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);

        if (tarefaExistente.isPresent()) {
            tarefaExistente.get().setNome(tarefa.getNome());
            tarefaExistente.get().setDescricao(tarefa.getDescricao());
            tarefaExistente.get().setDataDeCriacao(tarefa.getDataDeCriacao());
            tarefaExistente.get().setDataDeConclusao(tarefa.getDataDeConclusao());
            tarefaExistente.get().setPrioridade(tarefa.getPrioridade());
            tarefaExistente.get().setStatus(tarefa.getStatus());
            tarefaExistente.get().setUsuario(tarefa.getUsuario());
            tarefaExistente.get().setProjeto(tarefa.getProjeto());

            return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.save(tarefaExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Buscar tarefaDTO pelo id
    @GetMapping("dto/{id}")
    public ResponseEntity<TarefaDTO> findById(@PathVariable("id") Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        if (tarefa.isPresent()) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private TarefaService service;
}
