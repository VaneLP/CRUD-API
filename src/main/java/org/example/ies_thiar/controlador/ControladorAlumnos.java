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
import org.example.ies_thiar.controlador.dao.AlumnoDAO;
import org.example.ies_thiar.controlador.dao.jpa.AlumnoDAOJPAImpl;
import org.example.ies_thiar.modelo.Alumno;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path
public class ControladorAlumnos  {
	private List<Double> listNota = new ArrayList<Double>();
	private AlumnoDAO alumJpa = new AlumnoDAOJPAImpl();
	private double allNotas = 0, media;
	private int cont = 0;

	// ---- METODOS ----
	/**
	 * Agrega un alumno utilizando la informacion proporcionada en el cuerpo de la solicitud
	 * @param body Cuerpo de la solicitud en formato JSON que contiene la informacion del alumno a agregar
	 * @param response Respuesta HTTP que indica el resultado de la operacion
	 */
	@POST("/api/alumno")
	public void agregar(@Body String body, Response response) {
		try{
			Alumno a = new Gson().fromJson(body, new TypeToken<Alumno>() {}.getType());
			alumJpa.insert(a);
			response.status(200);
		}catch (Exception e){
			e.printStackTrace();
			response.status(400);
		}

	}

	/**
	 * Busca un alumno por su id
	 * @param response Respuesta HTTP que contiene la informacion del alumno si se encuentra
	 * @param id Identificador del alumno a buscar
	 * @return El alumno encontrado o un mensaje indicando que no se encontro el alumno
	 */
	@GET("/api/alumno/:id")
	public Alumno buscar(Response response, @PathParam long id) {
		Optional<Alumno> a = Optional.ofNullable(alumJpa.readUno(id));

		if(a.isPresent())
			response.json(a.get());
		else
			response.json("Alumno no encontrado");

		return a.get();
	}

	/**
	 * Actualiza la informacion de un alumno existente
	 * @param body Cuerpo de la solicitud en formato JSON que contiene la información actualizada del alumno
	 * @param response Respuesta HTTP que indica el resultado de la operacion
	 * @param id Identificador del alumno a actualizar
	 */
	@PUT("/api/alumno/:id")
	public void update(@Body String body, Response response, @PathParam long id){
		try{
			Alumno a = new Gson().fromJson(body, new TypeToken<Alumno>() {}.getType());
			alumJpa.update(a);
			response.status(200);
		}catch (Exception e){
			e.printStackTrace();
			response.status(400);
		}
	}

	/**
	 * Elimina un alumno por su id
	 * @param response Respuesta HTTP que indica el resultado de la operacion
	 * @param request Solicitud HTTP que contiene el id del alumno a eliminar
	 */
	@DELETE("/api/alumno/:id")
	public void eliminar(Response response, Request request) {
		alumJpa.delete(request.pathLong("id"));
	}

	/**
	 * Lista todos los alumnos registrados
	 * @param response Respuesta HTTP que contiene la lista de alumnos
	 * @return La lista de alumnos registrados
	 */
	@GET("/api/alumnos")
	public List<Alumno> listar(Response response) {
		response.json(alumJpa.listaAlumDAO());

		return alumJpa.listaAlumDAO();
	}

}
