package com;

import com.entities.Item;
import com.util.EntityManagerHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import java.util.List;

public class MappingCollectionsTests {

    EntityManager em;

    @BeforeEach
    public void setUp(){
        em = EntityManagerHelper.getEntityManager();
        em.getTransaction().begin();
    }

    @Test
    public void testPreserveOrderPositive(){
        List<String> images = List.of("test1", "test2", "test3");
        Item item = new Item();
        item.setName("Ball");
        item.setImages(images);
        em.persist(item);
        em.getTransaction().commit();
    }


    @AfterEach
    public void tearDown(){
        em.close();
        EntityManagerHelper.emFactoryObj.close();
    }
}
