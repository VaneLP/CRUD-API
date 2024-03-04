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
    private CursoDAO curJpa = new CursoDAOJPAImpl();

    // ---- METODOS ----
    /**
     * Agrega un curso utilizando la informacion proporcionada en el cuerpo de la solicitud
     * @param body Cuerpo de la solicitud en formato JSON que contiene la información del curso a agregar
     * @param response Respuesta HTTP que indica el resultado de la operacion
     */
    @POST("/api/curso")
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

    /**
     * Busca un curso por su id
     * @param response Respuesta HTTP que contiene la informacion del curso si se encuentra
     * @param id Identificador del curso a buscar
     * @return El curso encontrado o un mensaje indicando que no se encontró el curso
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

    /**
     * Actualiza la información de un curso existente
     * @param body Cuerpo de la solicitud en formato JSON que contiene la informacion actualizada del curso
     * @param response Respuesta HTTP que indica el resultado de la operacion
     * @param id Identificador del curso a actualizar
     */
    @PUT("/api/curso/:id")
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

    /**
     * Elimina un curso por su id
     * @param response Respuesta HTTP que indica el resultado de la operacion
     * @param request Solicitud HTTP que contiene el identificador del curso a eliminar
     */
    @DELETE("/api/curso/:id")
    public void eliminar(Response response, Request request) {
        curJpa.delete(request.pathLong("id"));
    }

    /**
     * Lista todos los cursos registrados
     * @param response Respuesta HTTP que contiene la lista de cursos
     * @return La lista de cursos registrados
     */
    @GET("/api/cursos")
    public List<Curso> listar(Response response) {
        response.json(curJpa.listaCurDAO());

        return curJpa.listaCurDAO();
    }
}
