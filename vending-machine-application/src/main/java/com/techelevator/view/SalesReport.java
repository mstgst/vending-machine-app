package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesReport {

    private Map<Product, Integer> salesReport = new HashMap<>();
    private static final String VENDING_MACHINE_PRODUCTS = "./data/vendingmachine.csv";

    public SalesReport() {
        this.salesReport = salesReport;
    }

    public Map<Product, Integer> getSalesReport() {
        return salesReport;
    }

    public void setSalesReport(Map<Product, Integer> salesReport) {
        this.salesReport = salesReport;
    }

    public void totalSales () {
        try (Scanner vendingMachineProducts = new Scanner (new File(VENDING_MACHINE_PRODUCTS))) {
            while (vendingMachineProducts.hasNextLine()) {
                String product = vendingMachineProducts.nextLine();
                //ArrayList<Product> placeholder = new ArrayList<>();
                String[] productParts = product.split("\\|");
                this.salesReport.put((new Product(productParts[0])), 0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Inventory not found");
        }
    }
}
