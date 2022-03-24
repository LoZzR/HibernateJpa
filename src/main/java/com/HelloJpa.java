package com;

import com.entities.Message;
import com.util.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import java.util.List;

public class HelloJpa {

    public static void main(String args[]){
        EntityManager em = EntityManagerHelper.getEntityManager();
        em.getTransaction().begin();
        /*Message message = new Message();
        message.setId(Long.valueOf(1));
        message.setText("Hello World!");
        em.persist(message);*/

        List<Message> messages = em.createQuery("select m from Message m").getResultList();

        System.out.println(messages);

        messages.get(0).setText("Take me to your leader!");

        em.getTransaction().commit();
        // INSERT into MESSAGE (ID, TEXT) values (1, 'Hello World!')
        em.close();
    }

}
