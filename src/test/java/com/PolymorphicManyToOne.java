package com;

import com.entities.BillingDetails;
import com.entities.CreditCard;
import com.entities.User;
import com.util.EntityManagerHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        em.getTransaction().commit();

        List<User> users = em.createQuery("SELECT u FROM User u").getResultList();
        List<BillingDetails> billingDetails = em.createQuery("SELECT b FROM BillingDetails b").getResultList();

        assertNotNull(users);
        assertEquals(1, users.size());

        assertAll(
                ()->assertNotNull(users),
                ()->assertEquals(1, users.size()),
                ()->assertNotNull(billingDetails),
                ()->assertEquals(1, billingDetails.size())

        );
    }

    @AfterEach
    public void tearDown(){
        em.close();
        EntityManagerHelper.emFactoryObj.close();
    }
}
