package com.henrysgrocery.shop;

import org.joda.money.Money;

import static org.joda.money.CurrencyUnit.GBP;
import static org.joda.money.Money.of;

public enum ProductsPrice {
    soup(of(GBP, 0.65)),
    bread(of(GBP, 0.80)),
    milk(of(GBP, 1.30)),
    apple(of(GBP, 0.10));

    private final Money price;

    ProductsPrice(final Money price) {
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }
}
