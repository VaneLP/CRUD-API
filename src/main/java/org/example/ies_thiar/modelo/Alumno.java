package org.example.ies_thiar.modelo;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
//JPA
@Entity
public class Alumno extends Persona {
    //JPA
    //Many to one hace referencia al que muchos ALUMNO pueden tener un CURSO
    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(nullable = false)
    private Curso curso;

    @ElementCollection
    @CollectionTable(name = "alumno_notas")
    @Column(name = "Notas")
    private List<Double> listaNotas;

    //JPA
    public Alumno() {
    }

    /*
     * constructor al cual le pasamos un super con los atributos de la clase Persona
     * porque hemos heredado de ella y tambien le pasamos los atributos propios de
     * la clase Alumno
     */
    public Alumno(long id, String nombre, String DNI, String tlf, String edad, Curso curso) {
        super(id, nombre, DNI, tlf, edad);

        //this.id = id;
        this.curso = curso;
        this.listaNotas = new ArrayList<Double>();
    }

    public Alumno(long id, String nombre, String DNI, String tlf, String edad, Curso curso, List<Double> listaNotas) {
        super(id, nombre, DNI, tlf, edad);

        this.curso = curso;
        this.listaNotas = listaNotas;
    }

    public Alumno(Curso curso, List<Double> listaNotas) {
        this.curso = curso;
        this.listaNotas = listaNotas;
    }

    public Alumno(String nombre, String DNI, String tlf, String edad, Curso curso) {
        super(nombre, DNI, tlf, edad);

        //this.id = id;
        this.curso = curso;
        this.listaNotas = new ArrayList<Double>();
    }

    // getters y setters
    // curso
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        if (curso != null)
            this.curso = curso;
    }

    // lista notas
    public List<Double> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(List<Double> listaNotas) {
        if (listaNotas != null)
            this.listaNotas = listaNotas;
    }

    //id
//    public int getId() {
//        return id;
//    }

    // ---- METODOS ----

    /**
     * Metodo para agregar una nota a el ArrayList listaNotas
     *
     * @param nota
     */
    public void agregarNota(double nota) {
        listaNotas.add(nota);
    }

    /**
     * Metodo para eliminar todas las nota del ArrayList listaNotas al cual no le
     * <p>
     * pasamos nada como parametro porque queremos eliminarlas todas
     */
    public void eliminarNotas() {
        // Si la listaNotas no esta vacia devuelve TRUE
        if (!listaNotas.isEmpty()) {
            // Eliminar de con el .clear() todas las notas de la lista
            listaNotas.clear();
            // Mostramos un mensaje por pantalla
            System.out.println("Las notas han sido eliminadas con exito");
        }
        // Si devuelve FALSE
        else
            // mostramos un mensaje por pantalla
            System.out.println("No hay notas que eliminar");
    }

    /**
     * Metodo toString
     *
     * @return una cadena de texto formateada
     */
    @Override
    public String toString() {
        return super.toString() + curso + "\nLista de notas: " + listaNotas + "\n";
    }

}
