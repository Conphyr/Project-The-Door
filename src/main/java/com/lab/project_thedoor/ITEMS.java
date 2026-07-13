package com.lab.project_thedoor;
import java.util.*;

public class ITEMS {

    private String itemName;
    private String itemDescription;
    private String category;
    private int stackAmount;
    private int maximumStackAmount;
    private static ArrayList<ITEMS> itemsList = new ArrayList<>();

    public ITEMS(String itemName, String itemDescription, String category, int stackAmount, int maximumStackAmount) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.category = category;
        this.stackAmount = stackAmount;
        this.maximumStackAmount = maximumStackAmount;
    }

    public String getItemName(ITEMS item) {
        return item.itemName;
    }

    public String getItemDescription(ITEMS item) {
        return item.itemDescription;
    }

    public String getItemCategory(ITEMS item) {
        return item.category;
    }

    public int getItemStackAmount(ITEMS item) {
        return item.stackAmount;
    }

    public int getItemMaximumStackAmount(ITEMS item) {
        return item.maximumStackAmount;
    }

    public void addStackAmount(ITEMS item, int amountToAdd) {
        item.stackAmount += amountToAdd;
    }

    public void reduceStackAmount(ITEMS item, int amountToReduce) {
        item.stackAmount -= amountToReduce;
    }

    // //stack when conditions meet, otherwise return false (refer to the "addInventory_Item" method in the PLAYER class)
    // public boolean canStackMore(ITEMS item_toBeStack, ITEMS item_toBeAdded) {
    //     if ((item_toBeStack.maximumStackAmount - item_toBeStack.stackAmount) >= item_toBeAdded.stackAmount) {
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

    public void getItemDescription(ITEMS item, int itemCount) {
        System.out.printf("%d) [%s] * (%d/%d): %s\n", itemCount, item.itemName, item.stackAmount, item.maximumStackAmount, item.itemDescription);
    }

    public void addItem(ITEMS item) {
        itemsList.add(item);
    }
    
}