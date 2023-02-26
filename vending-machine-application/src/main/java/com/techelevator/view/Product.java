package com.techelevator.view;

import java.math.BigDecimal;

public class Product {
    private String productName;
    private String productType;
    //private String selectionName;
    private BigDecimal productPrice;
    private int stockQuantity = 5;
    private String dispenseMsg;

    public Product (String productName) {
        this.productName = productName;
    }

    public Product(String productName, String productType, BigDecimal productPrice, int stockQuantity) {
        this.productName = productName;
        this.productType = productType;
        this.productPrice = productPrice;
        this.stockQuantity = stockQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String dispenseMsg() {
        return dispenseMsg;
    }

    //Does not make use of the child types for Product, but does fulfill the message issue for now.
    public String productMsg() {
        if (this.getProductType().equals("Chip")) {
            return "Crunch Crunch, Yum!";
        }
        if (this.getProductType().equals("Candy")) {
            return "Munch Munch, Yum!";
        }
        if (this.getProductType().equals("Soda")) {
            return "Glug Glug, Yum!";
        }
        if (this.getProductType().equals("Gum")) {
            return "Chew Chew, Yum!";
        }
        else
            return "";
    }
}
