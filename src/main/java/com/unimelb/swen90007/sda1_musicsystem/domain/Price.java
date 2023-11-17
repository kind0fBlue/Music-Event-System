package com.unimelb.swen90007.sda1_musicsystem.domain;

/***********Embedded Value************/
public class Price {
    private Integer amount;
    private String currency;

    public Price(Integer amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}

/***********Embedded value************/

