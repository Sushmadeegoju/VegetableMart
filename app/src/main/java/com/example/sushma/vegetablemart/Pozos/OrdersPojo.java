package com.example.sushma.vegetablemart.Pozos;

public class OrdersPojo {
    String date,amount;

    public OrdersPojo() {
    }

    public OrdersPojo(String date, String amount) {
        this.date = date;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
