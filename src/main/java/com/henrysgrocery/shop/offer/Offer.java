package com.henrysgrocery.shop.offer;

import com.henrysgrocery.shop.product.ProductType;
import org.joda.money.Money;

import java.time.LocalDate;
import java.util.HashMap;

public abstract class Offer {
    private final LocalDate validFrom;
    private final LocalDate validTo;

    public Offer(final LocalDate validFrom, final LocalDate validTo) {
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public abstract Money calculateDiscount(final HashMap<ProductType, Integer> items, final LocalDate purchaseDate);

    protected boolean isOfferInValid(final LocalDate purchaseDate) {
        return purchaseDate.isBefore(validFrom) || purchaseDate.isAfter(validTo);
    }

}
