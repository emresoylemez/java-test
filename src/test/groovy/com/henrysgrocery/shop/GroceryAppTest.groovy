package com.henrysgrocery.shop


import spock.lang.Specification

class GroceryAppTest extends Specification {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream()

    void setup() {
        System.setOut(new PrintStream(outContent))
    }

    def "addItem - should show the menu"() {
        given:
        def app = new GroceryApp()

        when:
        app.showMenu("testText")

        then:
        outContent.toString() == "testText"
    }
}