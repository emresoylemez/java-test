package com.henrysgrocery.shop;

import com.henrysgrocery.shop.offer.AppleTenPercentDiscountOffer;
import com.henrysgrocery.shop.offer.BuyTwoSoupsGetBreadHalfPriceOffer;
import com.henrysgrocery.shop.offer.Offer;
import com.henrysgrocery.shop.product.ProductCatalog;
import com.henrysgrocery.shop.product.ProductType;
import org.joda.money.Money;

import java.time.Clock;
import java.time.LocalDate;
import java.util.HashMap;

import static java.time.LocalDate.now;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static org.joda.money.CurrencyUnit.GBP;

public class ShoppingCart {
    private final HashMap<ProductType, Integer> items;
    private final Offer appleTenPercentDiscountOffer;
    private final Offer buyTwoSoupsGetBreadHalfPriceOffer;

    public ShoppingCart(final Clock clock) {
        final LocalDate now = now(clock);
        items = new HashMap<>();
        buyTwoSoupsGetBreadHalfPriceOffer = new BuyTwoSoupsGetBreadHalfPriceOffer(now.minusDays(1),
                now.plusDays(7));
        appleTenPercentDiscountOffer = new AppleTenPercentDiscountOffer(now.plusDays(3),
                now.plusMonths(1).with(lastDayOfMonth()));
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
        final Money appleDiscount = appleTenPercentDiscountOffer.calculateDiscount(items, purchaseDate);
        final Money breadDiscount = buyTwoSoupsGetBreadHalfPriceOffer.calculateDiscount(items, purchaseDate);

        return items.entrySet().stream()
                .map(entry -> ProductCatalog.getProduct(entry.getKey())
                        .getCost()
                        .multipliedBy(entry.getValue())
                )
                .reduce(Money.zero(GBP), Money::plus)
                .minus(appleDiscount)
                .minus(breadDiscount);
    }
}
