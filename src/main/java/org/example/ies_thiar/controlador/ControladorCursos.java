package org.example.ies_thiar.controlador;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hellokaton.blade.annotation.Path;
import com.hellokaton.blade.annotation.request.Body;
import com.hellokaton.blade.annotation.request.PathParam;
import com.hellokaton.blade.annotation.route.DELETE;
import com.hellokaton.blade.annotation.route.GET;
import com.hellokaton.blade.annotation.route.POST;
import com.hellokaton.blade.annotation.route.PUT;
import com.hellokaton.blade.mvc.http.Request;
import com.hellokaton.blade.mvc.http.Response;
import org.example.ies_thiar.controlador.dao.CursoDAO;
import org.example.ies_thiar.controlador.dao.jpa.CursoDAOJPAImpl;
import org.example.ies_thiar.modelo.Curso;
import java.util.List;
import java.util.Optional;

@Path
public class ControladorCursos{
    //private CursoDAO curDao = new CursoDAOJDBCImpl();
    private CursoDAO curJpa = new CursoDAOJPAImpl();

    // ---- METODOS ----

    
//    public void crearTablas() {
//        curJpa.crearTablasCur();
//    }

    @POST("/api/agregarCurso")
    public void agregar(@Body String body, Response response) {
        try{
            Curso c = new Gson().fromJson(body, new TypeToken<Curso>() {}.getType());
            curJpa.insert(c);
            response.status(200);
        }catch (Exception e){
            e.printStackTrace();
            response.status(400);
        }
    }


//    public void agregarCsv(long id, String nombre) {
//        //BBDD
//        curJpa.insert(new Curso(id, nombre));
//    }

    /**
     * Metodo buscar de la interfaz ILista
     * @param id pasamos una cadena de texto, que haria referencia al codigo
     * @return nos duvuelve un curso si corresponde con el codigo
     */

    @GET("/api/curso/:id")
    public Curso buscar(Response response, @PathParam long id) {
        Optional<Curso> c = Optional.ofNullable(curJpa.readUno(id));

        if(c.isPresent())
            response.json(c.get());
        else
            response.json("Curso no encontrado");

        return c.get();
    }

    @PUT("/api/updateCurso/:id")
    public void update(@Body String body, Response response, @PathParam long id){
        try{
            Curso c = new Gson().fromJson(body, new TypeToken<Curso>() {}.getType());
            curJpa.update(c);
            response.status(200);
        }catch (Exception e){
            e.printStackTrace();
            response.status(400);
        }
    }

    @DELETE("/api/eliminarCurso/:id")
    public void eliminar(Response response, Request request) {
        curJpa.delete(request.pathLong("id"));
    }

    /**
     * Metodo listar de la interfaz ILista
     *
     * @return nos duvuelve todos los cursos que tenemos en la lista
     */
    @GET("/api/cursos")
    public List<Curso> listar(Response response) {
        response.json(curJpa.listaCurDAO());

        return curJpa.listaCurDAO();
    }


//
//    public List<Curso> ordenarALfabeticamente() {
//        //BBDD
//        return curJpa.ordenarCurAlfDAO();
//    }
//
//    public List<Curso> coincidenciaExactaId(int idd){
//        return curJpa.coincidenciaExactaId(idd);
//    }
//    public List<Curso> contienePalabraClaveId(int idd){
//        return curJpa.contienePalabraClaveId(idd);
//    }
//    public List<Curso> empiezaPorId(int idd){
//        return curJpa.empiezaPorId(idd);
//    }
//    public List<Curso> terminaEnId(int idd){
//        return curJpa.terminaEnId(idd);
//    }
//    public List<Curso> coincidenciaExactaNombre(String name){
//        return curJpa.coincidenciaExactaNombre(name);
//    }
//    public List<Curso> contienePalabraClaveNombre(String name){
//        return curJpa.contienePalabraClaveNombre(name);
//    }
//    public List<Curso> empiezaPorNombre(String name){
//        return curJpa.empiezaPorNombre(name);
//    }
//    public List<Curso> terminaEnNombre(String name){
//        return curJpa.terminaEnNombre(name);
//    }

}
