package com.techelevator.view;

import java.util.HashMap;
import java.util.Map;

public class ProductPrice {

    private Map<String, Integer> productPrice = new HashMap<>();

    public ProductPrice(Map<String, Integer> productPrice) {
        this.productPrice = productPrice;
    }

    public Map<String, Integer> getProductPrice() {
        return productPrice;
    }
}
