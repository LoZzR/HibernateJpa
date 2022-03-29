package com.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.Immutable
@org.hibernate.annotations.Subselect(
        value = "select ID , NAME " +
                "from ITEM " +
                "group by ID, NAME"
)
@org.hibernate.annotations.Synchronize("Item")
public class ItemSummary {

    @Id
    protected Long id;
    protected String name;

    public ItemSummary() {
    }
}
