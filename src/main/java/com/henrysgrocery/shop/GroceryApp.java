package com.henrysgrocery.shop;

import com.henrysgrocery.shop.product.ProductType;
import org.joda.money.Money;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.System.out;

public class GroceryApp {

    public static final String MENU = "To add an item, write the name of the product. \nAvailable products: soup  bread  bread  milk \nTo Exit : exit\n";
    private final ShoppingCart shoppingCart;
    private final Scanner scanner;


    public GroceryApp(final ShoppingCart shoppingCart, final Scanner scanner) {
        this.shoppingCart = shoppingCart;
        this.scanner = scanner;
    }

    public void showInfo(final String menu) {
        out.print(menu);
    }

    public void showMenu() {
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }

            shoppingCart.addItem(ProductType.valueOf(input), 1);
            out.println(input + " added");
        }
        out.print("exited");
    }

    public void showTotal() {
        final Money totalCost = shoppingCart.calculateTotal(LocalDate.now(Clock.systemDefaultZone()));
        out.print("Total cost: " + totalCost.getAmount() + " " + totalCost.getCurrencyUnit());
    }

    public static void main(final String[] args) {
        final GroceryApp app = new GroceryApp(new ShoppingCart(Clock.systemDefaultZone()), new Scanner(System.in));
        app.showInfo(MENU);
        app.showMenu();
        app.showTotal();
    }
}
