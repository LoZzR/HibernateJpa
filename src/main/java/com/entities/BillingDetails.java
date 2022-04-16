package com.entities;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.persistence.Enity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/*@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)*/
@MappedSuperclass
public abstract class BillingDetails {

    @NotNull
    protected String owner;

    // ...

    protected BillingDetails() {
    }

    protected BillingDetails(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
