package com.henrysgrocery.shop.offer;

import com.henrysgrocery.shop.ProductsPrice;
import org.joda.money.Money;

import java.math.RoundingMode;
import java.util.HashMap;

import static org.joda.money.CurrencyUnit.GBP;

public class AppleDiscountOffer {
    public Money calculateDiscount(final HashMap<String, Integer> items) {
        return items.entrySet().stream()
                .filter(entry -> entry.getKey().equals("apple"))
                .map(entry -> ProductsPrice.valueOf(entry.getKey()).getPrice()
                        .multipliedBy(0.10, RoundingMode.HALF_DOWN)
                        .multipliedBy(entry.getValue(), RoundingMode.HALF_DOWN)
                )
                .reduce(Money.zero(GBP), Money::plus);
    }
}
