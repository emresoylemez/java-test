package com.henrysgrocery.shop;

import org.joda.money.Money;

import java.math.RoundingMode;
import java.util.HashMap;

import static org.joda.money.CurrencyUnit.GBP;

public class ShoppingCart {

    private final HashMap<String, Integer> items = new HashMap<>();

    public void addItem(final String name, final int count) {
        final Integer itemsCount = items.get(name);
        if (itemsCount == null) {
            items.put(name, count);
        } else {
            items.put(name, itemsCount + count);
        }
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public Money calculateTotal() {
        final Money discount = appleDiscountOffer();

        return items.entrySet().stream()
                .map(entry -> ProductsPrice.valueOf(entry.getKey())
                        .getPrice()
                        .multipliedBy(entry.getValue())
                )
                .reduce(Money.zero(GBP), Money::plus).minus(discount);
    }

    private Money appleDiscountOffer() {
        return items.entrySet().stream()
                .filter(entry -> entry.getKey().equals("apple"))
                .map(entry -> ProductsPrice.valueOf(entry.getKey()).getPrice()
                        .multipliedBy(0.10, RoundingMode.HALF_DOWN)
                        .multipliedBy(entry.getValue(), RoundingMode.HALF_DOWN)
                )
                .reduce(Money.zero(GBP), Money::plus);
    }
}
