package com.alunoonline.api.controller;


import com.alunoonline.api.model.Disciplina;
import com.alunoonline.api.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    @Autowired
    DisciplinaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Disciplina> create(@RequestBody Disciplina disciplina){
        Disciplina disciplinaCreated = service.create(disciplina);
        return ResponseEntity.status(201).body(disciplinaCreated);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Disciplina> update(@PathVariable Long id, @RequestBody Disciplina disciplinaUpdate){
        Optional<Disciplina> optionalDisciplina = service.findById(id);
        if (optionalDisciplina.isPresent()){
            Disciplina disciplina = optionalDisciplina.get();
            disciplina.setNome(disciplinaUpdate.getNome());
            disciplina.setProfessor(disciplinaUpdate.getProfessor());
            service.save(disciplina);
            return ResponseEntity.ok(disciplina);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
