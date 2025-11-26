package com.example.listadelacompra;
public class Item {
    private String name;
    private int quantity;
    private int imageResId; // imagen asociada

    public Item(String name, int quantity, int imageResId) {
        this.name = name;
        this.quantity = quantity;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getImageResId() { return imageResId; }
    public void setImageResId(int imageResId) { this.imageResId = imageResId; }
}
