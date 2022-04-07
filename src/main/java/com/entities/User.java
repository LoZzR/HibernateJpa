package com.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
@SequenceGenerator(
        name = "ID_GENERATOR", sequenceName="S_USER",allocationSize=5,initialValue=1
)
public class User implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ID_GENERATOR"
    )
    protected Long id;


    protected Address homeAddress;

    public Long getId() {
        return id;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }
    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}
