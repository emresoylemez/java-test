package com.henrysgrocery.shop

import org.joda.money.Money
import spock.lang.Specification

import static org.joda.money.CurrencyUnit.GBP

class ShoppingCartTest extends Specification {
    ShoppingCart shoppingCart = new ShoppingCart()

    def "addItem - multiple item should be added"() {
        when:
        shoppingCart.addItem("soup", 2)
        shoppingCart.addItem("bread", 2)
        def totalItems = shoppingCart.getItems().values().stream()
                .reduce(0, { subtotal, element -> subtotal + element })

        then:
        totalItems == 4
    }

    def "calculateTotal - when there is no item should calculate to zero"() {
        when:
        def total = shoppingCart.calculateTotal()

        then:
        total == Money.zero(GBP)
    }

    def "calculateTotal - when there is item in the cart should calculate sum correctly"() {
        given:
        shoppingCart.addItem("soup", 2)
        shoppingCart.addItem("bread", 2)
        shoppingCart.addItem("milk", 2)
        shoppingCart.addItem("apple", 2)

        when:
        def total = shoppingCart.calculateTotal()

        then:
        total == Money.of(GBP, 5.68d)
    }

    def "calculateTotal - should apply 'Apple 10% Discount' offer"() {
        given:
        shoppingCart.addItem("apple", 10)

        when:
        def total = shoppingCart.calculateTotal()

        then:
        total == Money.of(GBP, 0.9d)
    }

}