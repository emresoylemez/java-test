package com.henrysgrocery.shop.product;

import org.joda.money.Money;

public class Product {
    private final String unit;
    private final Money cost;

    public Product(final String unit, final Money cost) {
        this.unit = unit;
        this.cost = cost;
    }

    public String getUnit() {
        return unit;
    }

    public Money getCost() {
        return cost;
    }
}
