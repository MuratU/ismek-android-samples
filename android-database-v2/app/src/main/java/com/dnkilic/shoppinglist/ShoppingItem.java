package com.dnkilic.shoppinglist;


public class ShoppingItem {

    private long id;
    private String itemName;
    private String itemQuantity;

    public ShoppingItem()
    {

    }

    public ShoppingItem(long id, String itemName, String itemQuantity) {
        this.id = id;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    public ShoppingItem(String itemName, String itemQuantity) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
