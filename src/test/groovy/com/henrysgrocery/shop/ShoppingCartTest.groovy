package com.henrysgrocery.shop

import org.joda.money.Money
import spock.lang.Specification

import java.time.Clock
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset

import static com.henrysgrocery.shop.product.ProductType.*
import static org.joda.money.CurrencyUnit.GBP

class ShoppingCartTest extends Specification {

    def testDate = LocalDate.of(2019, 6, 1)
    def testClock = Clock.fixed(testDate.atStartOfDay().toInstant(ZoneOffset.UTC), ZoneId.systemDefault())
    def shoppingCart = new ShoppingCart(testClock)

    def "addItem - multiple item should be added"() {
        when:
        shoppingCart.addItem(soup, 2)
        shoppingCart.addItem(bread, 2)
        def totalItems = shoppingCart.getItems().values().stream()
                .reduce(0, { subtotal, element -> subtotal + element })

        then:
        totalItems == 4
    }

    def "calculateTotal - when there is no item should calculate to zero"() {
        when:
        def total = shoppingCart.calculateTotal(testDate)

        then:
        total == Money.zero(GBP)
    }

    def "calculateTotal - when there is item in the cart should calculate sum and correctly"() {
        given:
        shoppingCart.addItem(soup, 2)
        shoppingCart.addItem(bread, 2)
        shoppingCart.addItem(milk, 2)
        shoppingCart.addItem(apple, 2)

        when:
        def total = shoppingCart.calculateTotal(testDate.plusDays(3))

        then:
        total == Money.of(GBP, 5.28)
    }

    def "calculateTotal - should apply 'Apple 10% Discount' offer"() {
        given:
        shoppingCart.addItem(apple, 10)

        when:
        def total = shoppingCart.calculateTotal(testDate.plusDays(3))

        then:
        total == Money.of(GBP, 0.9)
    }

    def "calculateTotal - should apply 'Buy 2 tins of soup and get a loaf of bread half price' offer"() {
        given:
        shoppingCart.addItem(soup, 2)
        shoppingCart.addItem(bread, 2)

        when:
        def total = shoppingCart.calculateTotal(testDate)

        then:
        total == Money.of(GBP, 2.5)
    }

}