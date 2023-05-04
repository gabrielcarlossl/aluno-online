package com.alunoonline.api.controller;

import com.alunoonline.api.model.MatriculaAluno;
import com.alunoonline.api.service.MatriculaAlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/matricula-aluno")
public class MatriculaAlunoController {

    @Autowired
    MatriculaAlunoService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MatriculaAluno> create(@RequestBody MatriculaAluno matriculaAluno) {
        MatriculaAluno matriculaAlunoCreated = service.create(matriculaAluno);

        return ResponseEntity.status(201).body(matriculaAlunoCreated);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MatriculaAluno> update(@PathVariable Long id, @RequestBody MatriculaAluno matriculaAlunoUpdate){

        Optional<MatriculaAluno> optionalMatriculaAluno = service.findById(id);

        if (optionalMatriculaAluno.isPresent()) {
            MatriculaAluno matriculaAluno = optionalMatriculaAluno.get();

            matriculaAluno.setAluno(matriculaAlunoUpdate.getAluno());
            matriculaAluno.setDisciplina(matriculaAlunoUpdate.getDisciplina());
            matriculaAluno.setNota1(matriculaAlunoUpdate.getNota1());
            matriculaAluno.setNota2(matriculaAlunoUpdate.getNota2());
            matriculaAluno.setStatus(matriculaAlunoUpdate.getStatus());
            service.save(matriculaAluno);
            return ResponseEntity.ok(matriculaAluno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
