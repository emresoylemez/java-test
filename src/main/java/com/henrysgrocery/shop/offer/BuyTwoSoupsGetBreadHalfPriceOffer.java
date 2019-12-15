package com.henrysgrocery.shop.offer;

import com.henrysgrocery.shop.product.ProductCatalog;
import com.henrysgrocery.shop.product.ProductType;
import org.joda.money.Money;

import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Optional;

import static com.henrysgrocery.shop.product.ProductType.bread;
import static com.henrysgrocery.shop.product.ProductType.soup;
import static org.joda.money.CurrencyUnit.GBP;

public class BuyTwoSoupsGetBreadHalfPriceOffer {
    public Money calculateDiscount(final HashMap<ProductType, Integer> items) {
        final Integer soupCount = Optional.ofNullable(items.get(soup)).orElse(0);
        if (soupCount < 2) {
            return Money.zero(GBP);
        }

        final int availableDiscount = soupCount / 2;
        final Integer breadCount = Optional.ofNullable(items.get(bread)).orElse(0);
        final int applicableDiscount = Math.min(breadCount, availableDiscount);

        return ProductCatalog.getProduct(bread).getCost()
                .dividedBy(2, RoundingMode.HALF_DOWN)
                .multipliedBy(applicableDiscount);
    }
}
