package com;

import com.entities.CreditCard;
import com.entities.User;
import com.util.EntityManagerHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

public class PolymorphicManyToOne {

     EntityManager em;

    @BeforeEach
    public void setUp(){
        em = EntityManagerHelper.getEntityManager();
        em.getTransaction().begin();
    }

    @Test
    public void insertUserPositive(){
        CreditCard cc = new CreditCard(
                "John Doe", "1234123412341234", "06", "2015"
        );
        User johndoe = new User("johndoe");
        johndoe.setBillingDetails(cc);
        em.persist(cc);
        em.persist(johndoe);
    }

    @AfterEach
    public void tearDown(){
        em.getTransaction().commit();
        em.close();
        EntityManagerHelper.emFactoryObj.close();
    }
}
