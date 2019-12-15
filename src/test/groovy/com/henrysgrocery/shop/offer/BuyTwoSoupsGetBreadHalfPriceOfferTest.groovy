package com.henrysgrocery.shop.offer


import org.joda.money.Money
import spock.lang.Specification

import static com.henrysgrocery.shop.product.ProductType.*
import static org.joda.money.CurrencyUnit.GBP

class BuyTwoSoupsGetBreadHalfPriceOfferTest extends Specification {
    BuyTwoSoupsGetBreadHalfPriceOffer buyTwoSoupsGetBreadHalfPriceOffer = new BuyTwoSoupsGetBreadHalfPriceOffer()

    def "calculateDiscount - should calculate bread discount correctly when there are other items"() {
        when:
        Money result = buyTwoSoupsGetBreadHalfPriceOffer.calculateDiscount([(soup): 2, (bread): 2, (milk): 2, (apple): 2])

        then:
        result == Money.of(GBP, 0.4)
    }
}
