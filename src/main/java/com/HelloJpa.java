package com;

import com.entities.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

public class HelloJpa {
    private static final EntityManagerFactory emFactoryObj;
    private static final String PERSISTENCE_UNIT_NAME = "TestPersistence";

    static {
        emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public static void main(String args[]){
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();
        Message message = new Message();
        message.setId(Long.valueOf(1));
        message.setText("Hello World!");
        em.persist(message);
        em.getTransaction().commit();
        // INSERT into MESSAGE (ID, TEXT) values (1, 'Hello World!')
        em.close();
    }

}