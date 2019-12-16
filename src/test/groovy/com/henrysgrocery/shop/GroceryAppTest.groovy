package com.henrysgrocery.shop

import com.henrysgrocery.shop.product.ProductType
import org.joda.money.Money
import spock.lang.Specification
import spock.lang.Unroll

import static com.henrysgrocery.shop.GroceryApp.MENU
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
        outContent.toString() == "exited"
    }

    @Unroll
    def "showMenu - should add #item from console when '#item' entered"() {
        given:
        def app = new GroceryApp(shoppingCart, new Scanner("${item}\nexit\n"))

        and: "verify addItem called once with correct item"
        1 * shoppingCart.addItem(ProductType.valueOf(item), 1)
        app.showMenu()

        expect:
        outContent.toString() == "${item} added\nexited"

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
        outContent.toString() == "Total cost: 12.34 GBP"
    }

}