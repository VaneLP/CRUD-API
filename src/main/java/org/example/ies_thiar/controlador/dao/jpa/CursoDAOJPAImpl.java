package org.example.ies_thiar.controlador.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.ies_thiar.controlador.dao.CursoDAO;
import org.example.ies_thiar.modelo.Alumno;
import org.example.ies_thiar.modelo.Curso;
import org.hibernate.Hibernate;

import java.util.List;

public class CursoDAOJPAImpl implements CursoDAO {
    private EntityManager entityManager;

    @Override
    public void insert(Curso cur) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            //empieza la trnsaccion
            entityManager.getTransaction().begin();
            entityManager.persist(cur);
            //termina la transaccion
            entityManager.getTransaction().commit();

            System.out.println("Inserción cur");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al agregar Curso" + e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Curso cur) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            //empieza la trnsaccion
            entityManager.getTransaction().begin();
            entityManager.merge(cur);
            //termina la transaccion
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar Curso" + e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Long idCur) {
        entityManager = ControladorJPA.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            Curso curso = entityManager.find(Curso.class, idCur);

            if (curso != null) {
                entityManager.remove(curso);
            }

            entityManager.getTransaction().commit();

            System.out.println("Curso borrado");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eleiminar Curso" + e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Curso readUno(Long idCur) {
        entityManager = ControladorJPA.getEntityManager();

        try {

            return entityManager.find(Curso.class, idCur);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

    //HQL
    @Override
    public List<Curso> listaCurDAO() {
        entityManager = ControladorJPA.getEntityManager();

        try {
            TypedQuery<Curso> query =
                    entityManager.createQuery(
                            "SELECT c " +
                                    "FROM Curso c", Curso.class);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

}
