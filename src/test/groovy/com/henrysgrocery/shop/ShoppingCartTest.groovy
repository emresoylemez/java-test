package com.henrysgrocery.shop

import spock.lang.*

class ShoppingCartTest extends Specification {

    def "addItem - item should be added"() {
        given:
        ShoppingCart shoppingCart = new ShoppingCart()

        when:
        shoppingCart.addItem("soup", 1)

        then:
        shoppingCart.getItems().size() == 1
    }

    def "addItem - multiple item should be added"() {
        given:
        ShoppingCart shoppingCart = new ShoppingCart()

        when:
        shoppingCart.addItem("soup", 2)
        shoppingCart.addItem("bread", 2)

        then:
        shoppingCart.getItems().size() == 4
    }

}