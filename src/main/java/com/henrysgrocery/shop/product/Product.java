package com.henrysgrocery.shop.product;

import lombok.Data;
import org.joda.money.Money;

@Data
public class Product {
    private final String unit;
    private final Money cost;
}
