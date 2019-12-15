package com.henrysgrocery.shop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<String> items = new ArrayList<>();

    public void addItem(String name, int count) {
        for (int i = 0; i < count; i++) {
            items.add(name);
        }
    }

    public List<String> getItems() {
        return items;
    }
}
