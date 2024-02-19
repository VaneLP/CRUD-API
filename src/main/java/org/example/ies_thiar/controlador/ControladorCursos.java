package org.example.ies_thiar.controlador;

import com.hellokaton.blade.annotation.Path;
import com.hellokaton.blade.annotation.route.GET;
import org.example.ies_thiar.controlador.dao.CursoDAO;
import org.example.ies_thiar.controlador.dao.jpa.CursoDAOJPAImpl;
import org.example.ies_thiar.modelo.Curso;
import java.util.List;

@Path
public class ControladorCursos implements ILista<Curso> {
    //private CursoDAO curDao = new CursoDAOJDBCImpl();
    private CursoDAO curJpa = new CursoDAOJPAImpl();

    // ---- METODOS ----

    @Override
    public void crearTablas() {
        curJpa.crearTablasCur();
    }

    @Override
    public void agregar(Curso object) {

    }

    public void agregar(String nombre) {
        //BBDD
        curJpa.insert(new Curso(nombre));
    }


    public void agregarCsv(long id, String nombre) {
        //BBDD
        curJpa.insert(new Curso(id, nombre));
    }

    /**
     * Metodo buscar de la interfaz ILista
     * @param id pasamos una cadena de texto, que haria referencia al codigo
     * @return nos duvuelve un curso si corresponde con el codigo
     */
    @Override
    public Curso buscar(String id) {
        //BBDD
        return curJpa.readUno(Integer.valueOf(id));
    }

    /**
     * Metodo eliminar de la interfaz ILista
     *
     * @param codigo le pasamos una cadena de texto, que haria referencia al codigo
     * @return nos duvuelve TRUE si se ha eliminado el curso y FALSE sino
     */
    @Override
    public void eliminar(String codigo) {
        //BBDD
        curJpa.delete(Integer.valueOf(codigo));
    }

    /**
     * Metodo listar de la interfaz ILista
     *
     * @return nos duvuelve todos los cursos que tenemos en la lista
     */
    @GET("/api/cursos")
    @Override
    public List<Curso> listar() {
        //BBDD
        return curJpa.listaCurDAO();
    }

    public List<Curso> ordenarALfabeticamente() {
        //BBDD
        return curJpa.ordenarCurAlfDAO();
    }

    public List<Curso> coincidenciaExactaId(int idd){
        return curJpa.coincidenciaExactaId(idd);
    }
    public List<Curso> contienePalabraClaveId(int idd){
        return curJpa.contienePalabraClaveId(idd);
    }
    public List<Curso> empiezaPorId(int idd){
        return curJpa.empiezaPorId(idd);
    }
    public List<Curso> terminaEnId(int idd){
        return curJpa.terminaEnId(idd);
    }
    public List<Curso> coincidenciaExactaNombre(String name){
        return curJpa.coincidenciaExactaNombre(name);
    }
    public List<Curso> contienePalabraClaveNombre(String name){
        return curJpa.contienePalabraClaveNombre(name);
    }
    public List<Curso> empiezaPorNombre(String name){
        return curJpa.empiezaPorNombre(name);
    }
    public List<Curso> terminaEnNombre(String name){
        return curJpa.terminaEnNombre(name);
    }

}
