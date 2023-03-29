package com.example.sushma.vegetablemart.Pozos;

public class CartPozo  {
    String name,cost,quantity,Mobile;

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    Double amount;
    int image;
    int id;

    public CartPozo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public CartPozo(String name, String cost, String quantity, int image, double v) {
        this.name = name;
        this.amount = v;
        this.quantity = quantity;
        this.image = image;
        this.cost=cost;
    }

    public Double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


}
