package org.example.ies_thiar.controlador.dao.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ControladorJPA {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    public static EntityManager getEntityManager() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        return entityManager;
    }

}
