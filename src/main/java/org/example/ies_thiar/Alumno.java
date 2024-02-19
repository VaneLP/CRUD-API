package org.example.ies_thiar;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Alumno {
        private String nombre;
        private String DNI;
        private int tlf;
        private int edad;
        private  int id;
        private static int contador = 1;
        private Curso curso;
        private List<Double> listaNotas;
}
