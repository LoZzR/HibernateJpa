package com.entities;

import com.util.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AttributeOverride(
        name = "owner",
        column = @Column(name = "CC_OWNER", nullable = false))
/*@SequenceGenerator(
        name = "ID_GENERATOR", sequenceName="S_CREDIT_CARD",allocationSize=5,initialValue=1
)*/
@DiscriminatorValue("CC")
//@PrimaryKeyJoinColumn(name = "CREDITCARD_ID")
public class CreditCard extends BillingDetails {

    @NotNull
    protected String cardNumber;

    @NotNull
    protected String expMonth;

    @NotNull
    protected String expYear;

    // ...

    public CreditCard() {
        super();
    }

    public CreditCard(String owner, String cardNumber, String expMonth, String expYear) {
        super(owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

}
