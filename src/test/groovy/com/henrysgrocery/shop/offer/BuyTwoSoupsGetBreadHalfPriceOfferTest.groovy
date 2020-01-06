package com.henrysgrocery.shop.offer


import org.joda.money.Money
import spock.lang.Specification

import java.time.LocalDate

import static com.henrysgrocery.shop.product.ProductType.*
import static org.joda.money.CurrencyUnit.GBP

class BuyTwoSoupsGetBreadHalfPriceOfferTest extends Specification {

    def "calculateDiscount - should calculate bread discount correctly when there are other items "() {
        given:
        def purchaseDate = LocalDate.of(2019, 6, 1)
        def offer = new BuyTwoSoupsGetBreadHalfPriceOffer(purchaseDate, purchaseDate.plusDays(2))
        def items = [(soup): 2, (bread): 2, (milk): 2, (apple): 2]

        when:
        Money result = offer.calculateDiscount(items)

        then:
        result == Money.of(GBP, 0.4)
    }
}
