package com.entities;


import com.util.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
/*@SequenceGenerator(
        name = "ID_GENERATOR", sequenceName="S_BANK_ACCOUNT",allocationSize=5,initialValue=1
)*/
@DiscriminatorValue("BA")
public class BankAccount extends BillingDetails {
/*
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ID_GENERATOR"
    )
    protected Long id;*/

    @NotNull
    protected String account;

    @NotNull
    protected String bankname;

    @NotNull
    protected String swift;

    public BankAccount() {
        super();
    }

    public BankAccount(String owner, String account, String bankname, String swift) {
        super(owner);
        this.account = account;
        this.bankname = bankname;
        this.swift = swift;
    }

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }
}