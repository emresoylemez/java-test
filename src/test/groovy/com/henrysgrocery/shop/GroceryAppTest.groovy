package com.henrysgrocery.shop

import com.henrysgrocery.shop.product.ProductType
import org.joda.money.Money
import spock.lang.Specification
import spock.lang.Unroll

import static com.henrysgrocery.shop.GroceryApp.MENU
import static com.henrysgrocery.shop.GroceryApp.NOT_FOUND_MESSAGE
import static org.joda.money.CurrencyUnit.GBP

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
        app.showInfo()

        then:
        outContent.toString() == MENU
    }

    def "showMenu - should exit from console when 'exit' entered"() {
        when:
        def app = new GroceryApp(shoppingCart, new Scanner("exit\n"))
        app.showMenu()

        then:
        outContent.toString() == "exited\n"
    }

    def "showMenu - should inform when wrong product name entered"() {
        when:
        def productName = "wrongProductName"
        def app = new GroceryApp(shoppingCart, new Scanner("${productName}\nexit\n"))
        app.showMenu()

        then:
        outContent.toString() == "${productName}${NOT_FOUND_MESSAGE}\nexited\n"
    }

    @Unroll
    def "showMenu - should add #item from console when '#item' entered"() {
        given:
        def app = new GroceryApp(shoppingCart, new Scanner("${item}\nexit\n"))

        and: "verify addItem called once with correct item"
        1 * shoppingCart.addItem(ProductType.valueOf(item), 1)
        app.showMenu()

        expect:
        outContent.toString() == "${item} added\nexited\n"

        where:
        item << ["soup", "bread", "milk", "apple"]
    }


    def "showTotal - should show calculated value from shopping cart"() {
        given:
        shoppingCart.calculateTotal(_) >> Money.of(GBP, 12.34)
        def app = new GroceryApp(shoppingCart, new Scanner(System.in))

        when:
        app.showTotal()

        then:
        outContent.toString() == "Total cost: 12.34 GBP\n"
    }

}