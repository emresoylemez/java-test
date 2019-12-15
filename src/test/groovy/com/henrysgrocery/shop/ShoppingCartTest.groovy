package com.henrysgrocery.shop

import org.joda.money.Money
import spock.lang.Specification

import static org.joda.money.CurrencyUnit.GBP

class ShoppingCartTest extends Specification {
    ShoppingCart shoppingCart = new ShoppingCart()

    def "addItem - item should be added"() {
        when:
        shoppingCart.addItem("soup", 1)

        then:
        shoppingCart.getItems().size() == 1
    }

    def "addItem - multiple item should be added"() {
        when:
        shoppingCart.addItem("soup", 2)
        shoppingCart.addItem("bread", 2)

        then:
        shoppingCart.getItems().size() == 4
    }

    def "calculateTotal - when there is no item should calculate to zero"() {
        when:
        def total = shoppingCart.calculateTotal()

        then:
        total == Money.zero(GBP)
    }

    def "calculateTotal - when there is item should calculate to sum"() {
        given:
        shoppingCart.addItem("soup", 2)
        shoppingCart.addItem("bread", 2)
        shoppingCart.addItem("milk", 2)
        shoppingCart.addItem("apple", 2)

        when:
        def total = shoppingCart.calculateTotal()

        then:
        total == Money.of(GBP, 5.7d)
    }

}