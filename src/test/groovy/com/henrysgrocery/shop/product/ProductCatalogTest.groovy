package com.henrysgrocery.shop.product


import org.joda.money.Money
import spock.lang.Specification

import static com.henrysgrocery.shop.product.ProductType.*
import static org.joda.money.CurrencyUnit.GBP

class ProductCatalogTest extends Specification {

    def "getProduct - Should return product cost correctly"() {
        expect:
        def product = ProductCatalog.getProduct(type)
        with(product) {
            unit == expectedUnit
            cost == Money.of(GBP, expectedCost)
        }

        where:
        type   | expectedUnit | expectedCost
        soup   | "tin"        | 0.65
        bread  | "loaf"       | 0.80
        milk   | "bottle"     | 1.30
        apple | "single"     | 0.10
    }
}
