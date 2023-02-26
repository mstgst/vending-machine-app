package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
//TO DO
    //CHANGE K, V to STRING, PRODUCT
    //EASIER TO SET UP PURCHASING METHOD
    private Map<String, Product> productInventory = new HashMap<>();
    private static final String VENDING_MACHINE_PRODUCTS = "./data/vendingmachine.csv";

//    public Inventory() {
//        this.productInventory = new HashMap<>();
//    }
//
//    public void setProductInventory(Map<String, Integer> productInventory) {
//        this.productInventory = productInventory;
//    }

    public Inventory() {
    }

    public Map<String, Product> getProductInventory() {
        return productInventory;
    }

    public void initInventory() {

        try (Scanner vendingMachineProducts = new Scanner (new File (VENDING_MACHINE_PRODUCTS))) {
            while (vendingMachineProducts.hasNextLine()) {
                String product = vendingMachineProducts.nextLine();
                //ArrayList<Product> placeholder = new ArrayList<>();
                String[] productParts = product.split("\\|");
                this.productInventory.put(productParts[0], new Product((productParts[1]), (productParts[3]), new BigDecimal(productParts[2]), 5));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Inventory not found");
        }
    }
}
