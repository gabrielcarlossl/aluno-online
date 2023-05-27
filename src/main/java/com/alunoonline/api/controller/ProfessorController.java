package com.alunoonline.api.controller;

import com.alunoonline.api.model.Aluno;
import com.alunoonline.api.model.Professor;
import com.alunoonline.api.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService service;
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Professor> create(@RequestBody Professor professor){
        Professor professorCreated = service.create(professor);
        return ResponseEntity.status(201).body(professorCreated);
    }

    @PatchMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Professor> update(@PathVariable Long id, @RequestBody Professor professorUpdated){
        Optional<Professor> optionalProfessor = service.findById(id);
        if (optionalProfessor.isPresent()){
            Professor professor = optionalProfessor.get();

            String nomeAtualizado = professorUpdated.getNome();
            String emailAtualizado = professorUpdated.getEmail();

            // Verificar se o nome foi fornecido na requisição
            if (nomeAtualizado != null && !nomeAtualizado.isEmpty()) {
                professor.setNome(nomeAtualizado);
            }

            // Verificar se o email foi fornecido na requisição
            if (emailAtualizado != null && !emailAtualizado.isEmpty()) {
                professor.setEmail(emailAtualizado);
            }

            service.save(professor);
            return ResponseEntity.ok(professor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Professor> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.OK)
    public List<Professor> findAll(){
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
