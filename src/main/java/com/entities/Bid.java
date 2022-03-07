package com.entities;

import javax.persistence.*;
import java.math.BigDecimal;

public class Bid {

    protected Long id;

    public Long getId() { // Optional but useful
        return id;
    }

    public Bid() {
    }
    protected BigDecimal amount;

    public Bid(BigDecimal amount, Item item) {
        this.amount = amount;
        this.item = item;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    protected Item item;

    public Bid(Item item) {
        this.item = item;
        item.getBids().add(this); // Bidirectional
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
