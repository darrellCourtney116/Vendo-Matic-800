package com.techelevator.view;

public class Items {

    private String slotLocation;
    private String name;
    private double price;
    private String type;
    private int stock = 5;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public String getType() {
        return type;
    }

    public int getStock() {
        return stock;
    }

    public Items(String slotLocation, String name, double price, String type, int stock) {
        this.slotLocation = slotLocation;
        this.name = name;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public Items(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public int decrement(){
        stock--;
        return stock;
    }

    public Items() {
    }
}




