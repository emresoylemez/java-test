package com.henrysgrocery.shop;

import org.joda.money.Money;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.joda.money.CurrencyUnit.GBP;

public class ShoppingCart {

    private final List<String> items = new ArrayList<>();

    public void addItem(final String name, final int count) {
        for (int i = 0; i < count; i++) {
            items.add(name);
        }
    }

    public List<String> getItems() {
        return items;
    }

    public Money calculateTotal() {
        final Money discount = appleDiscountOffer();

        return items.stream()
                .map(item -> ProductsPrice.valueOf(item).getPrice())
                .reduce(Money.zero(GBP), Money::plus).minus(discount);
    }

    private Money appleDiscountOffer() {
        return items.stream()
                .filter(x -> x.equals("apple"))
                .map(item -> ProductsPrice.valueOf(item).getPrice()
                        .multipliedBy(0.10, RoundingMode.HALF_DOWN)
                )
                .reduce(Money.zero(GBP), Money::plus);
    }
}
