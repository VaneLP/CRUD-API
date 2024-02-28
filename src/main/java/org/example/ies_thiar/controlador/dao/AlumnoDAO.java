package org.example.ies_thiar.controlador.dao;

import org.example.ies_thiar.modelo.Alumno;
import java.util.List;

//clase para incluir logica de BDD
public interface AlumnoDAO {
    void insert(Alumno alum);
    void update(Alumno alum);
    void delete(String dni);
    void delete(Long id);
    Alumno readUno(String dniAlum);
    Alumno readUno(long idAlum);
    List<Alumno> listaAlumDAO();

}
