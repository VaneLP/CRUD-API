package org.example.ies_thiar.controlador.dao;

import org.example.ies_thiar.modelo.Curso;
import java.util.List;

//clase para incluir logica de BDD
public interface CursoDAO {

    void insert(Curso cur);
    void update(Curso cur);
    void delete(Long idCur);
    Curso readUno(Long idCur);
    List<Curso> listaCurDAO();

}