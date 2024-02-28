package org.example.ies_thiar.controlador.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import org.example.ies_thiar.controlador.dao.AlumnoDAO;
import org.example.ies_thiar.modelo.Alumno;
import org.hibernate.Hibernate;
import javax.swing.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOJPAImpl implements AlumnoDAO {
    private EntityManager entityManager;

    @Override
    public void insert(Alumno alumno) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            //empieza la trnsaccion
            entityManager.getTransaction().begin();
            entityManager.persist(alumno);
            //termina la transaccion
            entityManager.getTransaction().commit();

            System.out.println("Inserción Alumno");
        }  catch (PersistenceException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");

                throw new RuntimeException("Error de violación de restricción de integridad en la base de datos", e);
            } else {
                JOptionPane.showMessageDialog(null, "Ups... algo salió mal, intentalo de nuevo.");

                throw new RuntimeException("Error de persistencia al obtener la lista de Alumno", e);
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al insertar Alumno", e);
        } finally {
            entityManager.close();
        }
    }


    @Override
    public void update(Alumno alum) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            //empieza la trnsaccion
            entityManager.getTransaction().begin();
            entityManager.merge(alum);
            //termina la transaccion
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar Alumno" + e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(String dni) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "WHERE a.DNI = :DNI", Alumno.class);

            query.setParameter("DNI", dni);

            for (Alumno a : query.getResultList()) {
                //if(a.getDNI().equalsIgnoreCase(dni))
                    entityManager.remove(a);
            }

            entityManager.getTransaction().commit();

            System.out.println("Alumno borrado");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar Alumno", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Long id) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso " +
                                    "WHERE a.id = :id", Alumno.class);

            query.setParameter("id", id);

            for (Alumno a : query.getResultList()) {
                //if(a.getDNI().equalsIgnoreCase(dni))
                entityManager.remove(a);
            }

            entityManager.getTransaction().commit();

            System.out.println("Alumno borrado");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar Alumno", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Alumno readUno(String dniAlum) {
        entityManager = ControladorJPA.getEntityManager();

        try {

            return entityManager.find(Alumno.class, dniAlum);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Alumno readUno(long idAlum) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            Alumno a = entityManager.find(Alumno.class, idAlum);

            Hibernate.initialize(a.getListaNotas());
            
            return a;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Alumno> listaAlumDAO() {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Alumno> query =
                    entityManager.createQuery(
                            "SELECT a " +
                                    "FROM Alumno a " +
                                    "LEFT JOIN FETCH a.curso", Alumno.class);

            for (Alumno alumno : query.getResultList()) {
                Hibernate.initialize(alumno.getListaNotas());
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

}
