package com.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/*@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)*/
@SequenceGenerator(
        name = "ID_GENERATOR", sequenceName="S_BILLING_DETAILS",allocationSize=5,initialValue=1
)
//@MappedSuperclass
/*@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "BD_TYPE")*/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetails {


    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ID_GENERATOR"
    )
    protected Long id;

    //@NotNull
    @Column(nullable = false)
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
