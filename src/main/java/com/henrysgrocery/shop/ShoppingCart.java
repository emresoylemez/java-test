package com.henrysgrocery.shop;

import org.joda.money.Money;

import java.util.ArrayList;
import java.util.List;

import static org.joda.money.CurrencyUnit.GBP;

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

    public Money calculateTotal() {
        return items.stream()
                .map(item -> ProductsPrice.valueOf(item).getPrice())
                .reduce(Money.zero(GBP), Money::plus);
    }
}
