package org.example.ies_thiar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Curso {
    private final int id;
    private static int contador = 1;
    private String nombre;
}
