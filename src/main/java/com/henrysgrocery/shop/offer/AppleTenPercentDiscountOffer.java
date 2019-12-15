package com.henrysgrocery.shop.offer;

import com.henrysgrocery.shop.product.ProductCatalog;
import com.henrysgrocery.shop.product.ProductType;
import org.joda.money.Money;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashMap;

import static com.henrysgrocery.shop.product.ProductType.apple;
import static org.joda.money.CurrencyUnit.GBP;

public class AppleTenPercentDiscountOffer extends Offer {

    public AppleTenPercentDiscountOffer(final LocalDate validFrom, final LocalDate validTo) {
        super(validFrom, validTo);
    }

    @Override
    public Money calculateDiscount(final HashMap<ProductType, Integer> items, final LocalDate purchaseDate) {
        if (isOfferInValid(purchaseDate)) {
            return Money.zero(GBP);
        }

        return items.entrySet().stream()
                .filter(entry -> entry.getKey().equals(apple))
                .map(entry -> ProductCatalog.getProduct(apple).getCost()
                        .multipliedBy(0.10, RoundingMode.HALF_DOWN)
                        .multipliedBy(entry.getValue(), RoundingMode.HALF_DOWN)
                )
                .reduce(Money.zero(GBP), Money::plus);
    }

}
