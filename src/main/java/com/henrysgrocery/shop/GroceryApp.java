package com.henrysgrocery.shop;

public class GroceryApp {

    public static final String MENU = "To add item: add [itemCount] [soup | bread | bread | milk] /n";

    public void showMenu(final String menu) {
        System.out.print(menu);
    }

    public static void main(String[] args) {
        final GroceryApp groceryApp = new GroceryApp();
        groceryApp.showMenu(MENU);
    }
}
