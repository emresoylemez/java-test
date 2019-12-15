package com.henrysgrocery.shop;

import com.henrysgrocery.shop.offer.AppleDiscountOffer;
import org.joda.money.Money;

import java.util.HashMap;

import static org.joda.money.CurrencyUnit.GBP;

public class ShoppingCart {
    private final AppleDiscountOffer appleDiscountOffer = new AppleDiscountOffer();
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
        final Money discount = appleDiscountOffer.calculateDiscount(items);

        return items.entrySet().stream()
                .map(entry -> ProductsPrice.valueOf(entry.getKey())
                        .getPrice()
                        .multipliedBy(entry.getValue())
                )
                .reduce(Money.zero(GBP), Money::plus).minus(discount);
    }


}
