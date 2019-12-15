package com.henrysgrocery.shop.offer

import org.joda.money.CurrencyUnit
import org.joda.money.Money
import spock.lang.Specification

class AppleTenPercentDiscountOfferTest extends Specification {
    AppleTenPercentDiscountOffer offer = new AppleTenPercentDiscountOffer()

    def "calculateDiscount - should calculate apple discount correctly when there are other items"() {
        when:
        Money result = offer.calculateDiscount(["soup": 2, "bread": 2, "milk": 2, "apple": 10])

        then:
        result == Money.of(CurrencyUnit.GBP, 0.10)
    }
}