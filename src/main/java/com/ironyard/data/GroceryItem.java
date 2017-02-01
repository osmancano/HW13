package com.ironyard.data;

/**
 * Created by jasonskipper on 1/26/17.
 */
public class GroceryItem {
    private long id;
    private String name;
    private double price;
    private int quantity;
    private int isle;
    private int category;

    public GroceryItem(){

    }
    public GroceryItem(long id, String name, double price, int quantity, int isle, int category){
        this.id = id;
        this.name = name;
        this.isle = isle;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public GroceryItem(String name, double price, int quantity, int isle, int category){
        this.name = name;
        this.isle = isle;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIsle() {
        return isle;
    }

    public void setIsle(int isle) {
        this.isle = isle;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
