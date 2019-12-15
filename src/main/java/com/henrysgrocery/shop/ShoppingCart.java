package com.henrysgrocery.shop;

import com.henrysgrocery.shop.offer.AppleTenPercentDiscountOffer;
import com.henrysgrocery.shop.offer.BuyTwoSoupsGetBreadHalfPriceOffer;
import com.henrysgrocery.shop.product.ProductCatalog;
import com.henrysgrocery.shop.product.ProductType;
import org.joda.money.Money;

import java.util.HashMap;

import static org.joda.money.CurrencyUnit.GBP;

public class ShoppingCart {
    private final AppleTenPercentDiscountOffer appleTenPercentDiscountOffer = new AppleTenPercentDiscountOffer();
    private final BuyTwoSoupsGetBreadHalfPriceOffer buyTwoSoupsGetBreadHalfPriceOffer = new BuyTwoSoupsGetBreadHalfPriceOffer();
    private final HashMap<ProductType, Integer> items = new HashMap<>();

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

    public Money calculateTotal() {
        final Money appleDiscount = appleTenPercentDiscountOffer.calculateDiscount(items);
        final Money breadDiscount = buyTwoSoupsGetBreadHalfPriceOffer.calculateDiscount(items);

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
