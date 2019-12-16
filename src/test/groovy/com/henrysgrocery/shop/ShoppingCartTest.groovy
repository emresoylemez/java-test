package com.henrysgrocery.shop

import com.henrysgrocery.shop.offer.AppleTenPercentDiscountOffer
import com.henrysgrocery.shop.offer.BuyTwoSoupsGetBreadHalfPriceOffer
import org.joda.money.Money
import spock.lang.Specification

import java.time.LocalDate

import static com.henrysgrocery.shop.product.ProductType.*
import static org.joda.money.CurrencyUnit.GBP

class ShoppingCartTest extends Specification {

    def static testDate = LocalDate.of(2019, 6, 1)

    def "addItem - multiple item should be added"() {
        given:
        def shoppingCart = new ShoppingCart(Collections.emptyList())

        when:
        shoppingCart.addItem(soup, 2)
        shoppingCart.addItem(bread, 2)
        def totalItems = shoppingCart.getItems().values().stream()
                .reduce(0, { subtotal, element -> subtotal + element })

        then:
        totalItems == 4
    }

    def "calculateTotal - when there is no item should calculate to zero"() {
        given:
        def shoppingCart = new ShoppingCart(Collections.emptyList())

        when:
        def total = shoppingCart.calculateTotal(testDate)

        then:
        total == Money.zero(GBP)
    }

    def "calculateTotal - when there is item in the cart and no offer should calculate sum correctly"() {
        given:
        def shoppingCart = new ShoppingCart(Collections.emptyList())

        shoppingCart.addItem(soup, 2)
        shoppingCart.addItem(bread, 2)
        shoppingCart.addItem(milk, 2)
        shoppingCart.addItem(apple, 2)

        when:
        def total = shoppingCart.calculateTotal(testDate)

        then:
        total == Money.of(GBP, 5.70)
    }


    def "calculateTotal - should apply 'Apple 10% Discount' offer"() {
        given:
        def shoppingCart = new ShoppingCart([new AppleTenPercentDiscountOffer(testDate, testDate)])
        shoppingCart.addItem(apple, 10)

        when:
        def total = shoppingCart.calculateTotal(testDate)

        then:
        total == Money.of(GBP, 0.9)
    }

    def "calculateTotal - should apply 'Buy 2 tins of soup and get a loaf of bread half price' offer"() {
        given:
        def shoppingCart = new ShoppingCart([new BuyTwoSoupsGetBreadHalfPriceOffer(testDate, testDate)])
        shoppingCart.addItem(soup, 2)
        shoppingCart.addItem(bread, 2)

        when:
        def total = shoppingCart.calculateTotal(testDate)

        then:
        total == Money.of(GBP, 2.5)
    }

}