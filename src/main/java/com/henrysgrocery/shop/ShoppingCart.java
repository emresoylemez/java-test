package com.henrysgrocery.shop;

import com.henrysgrocery.shop.offer.Offer;
import com.henrysgrocery.shop.product.ProductCatalog;
import com.henrysgrocery.shop.product.ProductType;
import org.joda.money.Money;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.joda.money.CurrencyUnit.GBP;

public class ShoppingCart {
    private final HashMap<ProductType, Integer> items;
    private final List<Offer> offers;

    public ShoppingCart(final List<Offer> offers) {
        items = new HashMap<>();
        this.offers = offers;
    }

    public void addItem(final ProductType productType, final int count) {
        final Integer itemsCount = items.get(productType);

        if (itemsCount == null) {
            items.put(productType, count);
        } else {
            items.put(productType, itemsCount + count);
        }
    }

    public HashMap<ProductType, Integer> getItems() {
        return items;
    }

    public Money calculateTotal(final LocalDate purchaseDate) {

        final Money discountTotal = offers.stream()
                .map(x -> x.calculateDiscount(items, purchaseDate))
                .reduce(Money.zero(GBP), Money::plus);

        return items.entrySet().stream()
                .map(entry -> ProductCatalog.getProduct(entry.getKey())
                        .getCost()
                        .multipliedBy(entry.getValue())
                )
                .reduce(Money.zero(GBP), Money::plus)
                .minus(discountTotal);
    }
}
