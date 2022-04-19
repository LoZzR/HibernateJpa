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

    @Test
    public void retrieveUserByIdPositive(){
        User user = em.find(User.class, Long.valueOf(22));
        assertNotNull(user);
        BillingDetails bd = user.getBillingDetails();
        assertFalse(bd instanceof CreditCard);

        // if previous is not called, select generated only for CreditCard field
        CreditCard creditCard =
                em.getReference(CreditCard.class, Long.valueOf(22));

        //assertTrue(bd != creditCard);

        User userEager = (User) em.createQuery(
                "select u from User u " +
                        "left join fetch u.billingDetails " +
                        "where u.id = :id")
                .setParameter("id", Long.valueOf(22))
                .getSingleResult();
        assertNotNull(userEager);
        //If there is no lazy loading before
        CreditCard creditCardEager = (CreditCard)userEager.getBillingDetails();
        assertTrue(creditCardEager instanceof CreditCard);
    }

    @AfterEach
    public void tearDown(){
        em.close();
        EntityManagerHelper.emFactoryObj.close();
    }
}
