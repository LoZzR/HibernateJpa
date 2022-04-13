package com.entities;

import com.entities.converters.MonetaryAmountConverter;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//Using native Hiberante
/*@org.hibernate.annotations.GenericGenerator(
        name = "ID_GENERATOR",
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = "sequence_name",
                        value = "JPWH_SEQUENCE"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "initial_value",
                        value = "1000"
                )
        })*/
//Using JPA
/*The allocationSize is used to instruct the JPA provider the number
of values that can be allocated by the application using a single
database sequence call
Use a pooled optimizer, The first sequence call gives the value of 1,
so the first Post entity gets that value. Now, when persisting the second
Item entity, Hibernate needs to call the sequence again, and it will get
the value of 6, so it can generate the identifier values of 2, 3, 4, 5, and 6
without needing any other database sequence call
 */
@SequenceGenerator(
        name = "ID_GENERATOR", sequenceName="S_ITEM",allocationSize=5,initialValue=1
)
public class Item {

    @Id
    /*Hibernate picks an appropriate strategy, asking the
    SQL dialect of your configured database what is best, default value*/
    //@GeneratedValue(strategy = GenerationType.AUTO)
    /*Hibernate expects (and creates, if you use the tools) a
    sequence named HIBERNATE_SEQUENCE in your database*/
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    /*Hibernate expects (and creates in table DDL) a
    special auto-incremented primary key column*/
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    /*Hibernate will use an extra table in your database
    schema that holds the next numeric primary key value,
    one row for each entity class*/
    //@GeneratedValue(strategy = GenerationType.TABLE)
    //Using native Hibernate
    //@GeneratedValue(generator = "ID_GENERATOR")
    //Using JPA
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ID_GENERATOR"
    )
    protected Long id;


    public Long getId() { // Optional but useful
        return id;
    }

    protected long version;

    @NotNull
    @Size(
            min = 2,
            max = 255,
            message = "Name is required, maximum 255 characters."
    )
    protected String name;

    @Future
    @Temporal(TemporalType.TIMESTAMP)
    protected Date auctionEnd;

    @org.hibernate.annotations.Formula(
            "SELECT CONCAT('ITEM*',I.NAME) FROM ITEM I WHERE I.ID = ID"
    )
    protected String shortDescription;

    @org.hibernate.annotations.Type(type = "yes_no")
    protected boolean verified = false;

    @NotNull
    @Convert(
            converter = MonetaryAmountConverter.class,
            disableConversion = false)
    @Column(name = "PRICE", length = 63)
    protected MonetaryAmount buyNowPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    public MonetaryAmount getBuyNowPrice() {
        return buyNowPrice;
    }

    public void setBuyNowPrice(MonetaryAmount buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }

    //protected Set<Bid> bids = new HashSet<Bid>();

    /*public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public void addBid(Bid bid) {
        // Be defensive
        if (bid == null)
            throw new NullPointerException("Can't add null Bid");
        if (bid.getItem() != null)
            throw new IllegalStateException("Bid is already assigned to an Item");

        getBids().add(bid);
        bid.setItem(this);
    }

    public Bid placeBid(Bid currentHighestBid, BigDecimal bidAmount) {
        if (currentHighestBid == null ||
                bidAmount.compareTo(currentHighestBid.getAmount()) > 0) {
            return new Bid(bidAmount, this);
        }
        return null;
    }*/
}
