package com.Taskifyapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import com.Taskifyapi.dto.ProjetoDTO;
import com.Taskifyapi.model.Projeto;
import com.Taskifyapi.repository.ProjetoRepository;
import com.Taskifyapi.service.ProjetoService;

@RestController
@RequestMapping(value = "/projetos")
public class ProjetoController {
    
    @PostMapping
    public ResponseEntity<Projeto> cadastrarNovoProjeto(@RequestBody Projeto projeto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoRepository.save(projeto));
    }

    @GetMapping
    public ResponseEntity<Page<Projeto>> listarProjetos(@PageableDefault(size=10, page = 0, sort = "nome", direction = Direction.DESC) Pageable paginacao) {
        return ResponseEntity.status(HttpStatus.OK).body( projetoRepository.findAll(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> listarProjetoPeloId(@PathVariable Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);

        if (projeto.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(projeto.get()); 
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Projeto> oberProjetosDoUsuario(@PathVariable("idUsuario") Long idUsuario) {
        return projetoRepository.findByUsuario(idUsuario);
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<String> deletarProjetoPeloId(@PathVariable Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);

        if (projeto.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        projetoRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Projeto deletado com sucesso!");

    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProejto(@PathVariable Long id, @RequestBody Projeto projeto) {
        Optional<Projeto> projetoExistente = projetoRepository.findById(id);

        if (projetoExistente.isPresent()) {
            projetoExistente.get().setNome(projeto.getNome());
            projetoExistente.get().setDescricao(projeto.getDescricao());
            projetoExistente.get().setUsuario(projeto.getUsuario());

            return ResponseEntity.status(HttpStatus.OK).body(projetoRepository.save(projetoExistente.get()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
    }

    //Buscar projetoDTO pelo id
    @GetMapping("dto/{id}")
    public ResponseEntity<ProjetoDTO> findById(@PathVariable("id") long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);

        if(projeto.isPresent()) {
             return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProjetoService service;
}
