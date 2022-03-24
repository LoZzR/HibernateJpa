package com.util;

import com.entities.Item;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "findItemsOrderByName",
                query = "select i from Item i order by i.name asc"
        )
        ,
        @NamedQuery(
                name = "findItemBuyNowPriceGreaterThan",
                query = "select i from Item i where i.buyNowPrice > :price"
        )
})
public class Queries {

    public List<Item> getDynamicItems() {
        EntityManager em = EntityManagerHelper.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Item> query =
                cb.createQuery(Item.class);
        Root<Item> fromItem = query.from(Item.class);
        query.select(fromItem);
        Path<String> namePath = fromItem.get("name");
        query.where(
                cb.like(
                        namePath,
                        cb.parameter(String.class, "pattern")
                )
        );

        //List<Item> items = em.createQuery(query).getResultList();
        List<Item> items =em.createQuery(query)
                        .setParameter("pattern", "%some item%")
                        .getResultList();

        return items;
    }

}
