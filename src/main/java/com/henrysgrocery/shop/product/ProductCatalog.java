package com.henrysgrocery.shop.product;

import org.joda.money.Money;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.henrysgrocery.shop.product.ProductType.apple;
import static com.henrysgrocery.shop.product.ProductType.bread;
import static com.henrysgrocery.shop.product.ProductType.milk;
import static com.henrysgrocery.shop.product.ProductType.soup;
import static org.joda.money.CurrencyUnit.GBP;

public class ProductCatalog {
    private static final Map<ProductType, Product> productsList;

    static {
        productsList = new HashMap<>();
        productsList.put(soup, new Product("tin", Money.of(GBP, 0.65)));
        productsList.put(bread, new Product("loaf", Money.of(GBP, 0.80)));
        productsList.put(milk, new Product("bottle", Money.of(GBP, 1.30)));
        productsList.put(apple, new Product("single", Money.of(GBP, 0.10)));
    }

    public static Product getProduct(final ProductType productType) {
        return Optional.ofNullable(productsList.get(productType))
                .orElseThrow(NoSuchElementException::new);
    }
}
