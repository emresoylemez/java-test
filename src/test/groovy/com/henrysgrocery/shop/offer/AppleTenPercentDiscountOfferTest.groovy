package com.henrysgrocery.shop.offer


import org.joda.money.CurrencyUnit
import org.joda.money.Money
import spock.lang.Specification

import java.time.LocalDate

import static com.henrysgrocery.shop.product.ProductType.*
import static org.joda.money.CurrencyUnit.*

class AppleTenPercentDiscountOfferTest extends Specification {

    def "calculateDiscount - should calculate apple discount correctly when there are other items and offer is valid"() {
        given:
        def purchaseDate = LocalDate.of(2019, 6, 1)
        def offer = new AppleTenPercentDiscountOffer(purchaseDate, purchaseDate.plusDays(2))
        def items = [(soup): 2, (bread): 2, (milk): 2, (apple): 10]

        when:
        Money result = offer.calculateDiscount(items, purchaseDate)

        then:
        result == Money.of(GBP, 0.10)
    }

    def "calculateDiscount - should return zero when offer is not valid"() {
        given:
        def purchaseDate = LocalDate.of(2019, 6, 1)
        def offer = new AppleTenPercentDiscountOffer(purchaseDate.plusDays(1), purchaseDate.plusDays(2))
        def items = [(soup): 2, (bread): 2, (milk): 2, (apple): 10]

        when:
        Money result = offer.calculateDiscount(items, purchaseDate)

        then:
        result == Money.zero(GBP)
    }
}