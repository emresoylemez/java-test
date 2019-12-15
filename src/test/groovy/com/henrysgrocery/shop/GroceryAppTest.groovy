package com.henrysgrocery.shop

import spock.lang.Specification

class GroceryAppTest extends Specification {
    def outContent = new ByteArrayOutputStream()
    def shoppingCart = Mock(ShoppingCart)

    void setup() {
        System.setOut(new PrintStream(outContent))
    }

    def "showInfo - should show the Info"() {
        given:
        def app = new GroceryApp(shoppingCart, new Scanner(System.in))

        when:
        app.showInfo("testText")

        then:
        outContent.toString() == "testText"
    }

    def "showMenu - should exit from console when 'exit' entered"() {
        when:
        def app = new GroceryApp(shoppingCart, new Scanner("exit\n"))
        app.showMenu()

        then:
        outContent.toString() == "exited"
    }

}