package com.alunoonline.api.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class DisciplinaAlunoDto {
    private String nomeDisciplina;
    private String professorDisciplina;
    private Double nota1;
    private Double nota2;
    private Double media;
    private String status;
}