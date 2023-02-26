package com.techelevator.view;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {
    public Inventory getMachineInventory() {
        return machineInventory;
    }

    public BigDecimal getMachineBalance() {
        return machineBalance;
    }

    public void setMachineBalance(BigDecimal machineBalance) {
        this.machineBalance = machineBalance;
    }

    private Inventory machineInventory = new Inventory();
    private BigDecimal machineBalance = new BigDecimal(0);
    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
    private DateTimeFormatter fileNameFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy-hh-mm-ss-a");
    private static LocalDateTime machineDate = LocalDateTime.now();
    private Map<String, Integer> machineSalesMap = new HashMap<>();
    private BigDecimal totalSales = new BigDecimal(0);
    private static final String PURCHASE_MENU_FEED_MONEY = "Feed money to the machine";
    private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select product to purchase";
    private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Complete transaction and purchase items";
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION, ""};


    public void invList() {
        // display vending machine items
        try (
                Scanner vendingCSV = new Scanner(new File("data/vendingmachine.csv"))) {
            while (vendingCSV.hasNextLine()) {
                String vendingCSVOutput = vendingCSV.nextLine();
                System.out.println(vendingCSVOutput);
            }
        } catch (
                FileNotFoundException e) {
            System.out.println("Couldn't find vendingmachine.csv");

        }
    }

    public void transactionMenuOptions() {
        Menu thisMenu = new Menu(System.in, System.out);

        while (true) {
            String thisChoice = (String) thisMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
            if (thisChoice.equals(PURCHASE_MENU_FEED_MONEY)) {
                addBalance();
            } else if (thisChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
                purchaseProduct();
            } else if (thisChoice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
                MachineReturnChange(getMachineBalance());
                return;
            }
        }
    }

    public void addBalance() {
        //try
        Scanner balanceInput = new Scanner(System.in);
        //{
        System.out.println("Enter the amount of money to add. Only whole dollar amounts are accepted: ");
        //} catch {}
        int balanceDeposit = balanceInput.nextInt();
        BigDecimal temp = new BigDecimal(balanceDeposit);
        if (balanceDeposit > 0) {
            machineBalance = machineBalance.add(temp);
        } else if (balanceDeposit < 0) {
            System.out.println("Negative values are not accepted, please try again.");
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter("./data/Log.txt", true))) {
            writer.println(machineDate.format(dateFormat) + " FEED MONEY: $" + balanceDeposit + " $" + getMachineBalance());
        } catch (IOException e) {
            System.err.println("Error appending entry. Msg: " + e.getMessage());
        }
        System.out.println("The new machine balance is $" + getMachineBalance());
    }

    public void MachineReturnChange(BigDecimal machineBalance) {
        Change.returnChange(machineBalance);
        setMachineBalance(new BigDecimal(0.0));

        System.out.println("Your total change is $ " + Change.getPlaceholderChange() + " , you will receive " + Change.getQuarterCount() + " quarters, " + Change.getDimeCount() + " dimes, " + Change.getNickelCount() + " nickels, " + Change.getPennyCount() + " pennies.");
        try (PrintWriter writer = new PrintWriter(new FileWriter("./data/Log.txt", true))) {
            writer.println(machineDate.format(dateFormat) + " GIVE CHANGE: $" + Change.getPlaceholderChange() + " $" + getMachineBalance());
        } catch (IOException e) {
            System.err.println("Error appending entry. Msg: " + e.getMessage());
        }
    }

    public void purchaseProduct() {
        String productSelection;
        Scanner userInput = new Scanner(System.in);
        invList();
        System.out.println("Please make a selection from the above items. Use the slot location to make a selection");
        productSelection = userInput.nextLine().toUpperCase(Locale.ROOT);
        if (machineInventory.getProductInventory().containsKey(productSelection)) {
            try {
                for (Map.Entry<String, Product> tempInventory : machineInventory.getProductInventory().entrySet()) {
                    if (productSelection.equals(tempInventory.getKey())) {
                        Product productSale = tempInventory.getValue();
                        double priceDouble = productSale.getProductPrice().doubleValue();
                        double balanceDouble = machineBalance.doubleValue();
                        String machineSalesKey = "";
                        int machineSalesValue = 5;
                        if (balanceDouble - priceDouble >= 0 && tempInventory.getValue().getStockQuantity() >= 1) {
                            machineBalance = machineBalance.subtract(productSale.getProductPrice());
                            (tempInventory.getValue()).setStockQuantity(((tempInventory.getValue()).getStockQuantity()) - 1);
                            machineSalesKey = tempInventory.getValue().getProductName();
                            machineSalesValue = (5 - (tempInventory.getValue().getStockQuantity()));
                            totalSales = totalSales.add(tempInventory.getValue().getProductPrice());
                            machineSalesMap.put(machineSalesKey, machineSalesValue);
                            System.out.println(tempInventory.getValue().productMsg() + " There are " + tempInventory.getValue().getStockQuantity() + " " + tempInventory.getValue().getProductName() + "(s) left.");
                            try (PrintWriter writer = new PrintWriter(new FileWriter("./data/Log.txt", true))) {
                                writer.println(machineDate.format(dateFormat) + " " + tempInventory.getValue().getProductName() + " " + tempInventory.getKey() +
                                        " $" + tempInventory.getValue().getProductPrice() + " $" + getMachineBalance());
                            } catch (IOException e) {
                                System.err.println("Error appending entry. Msg: " + e.getMessage());
                            }
                        } else if (balanceDouble - priceDouble >= 0 && tempInventory.getValue().getStockQuantity() < 1) {
                            System.out.println("That product is out of stock, please pick another product.");
                        } else if (balanceDouble - priceDouble <= 0) {
                            System.out.println("Insufficient balance, please add more funds.");
                        }
                        System.out.println("The balance is $" + getMachineBalance());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Could not compute input, likely due to invalid slot selection. Please select from the menu options.");
        }
        //salesReportOutput();
    }

    public void salesReportOutput() {
        String fileDate = ((machineDate.format(fileNameFormat)));
        for (Map.Entry<String, Integer> tempSalesMap : machineSalesMap.entrySet()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("./data/SalesReport" + fileDate + ".txt", true))) {
                writer.println(tempSalesMap.getKey() + "|" + tempSalesMap.getValue());
            } catch (IOException e) {
                System.err.println("Error appending entry. Msg: " + e.getMessage());
            }
        }
        try (PrintWriter writerTwo = new PrintWriter (new FileWriter("./data/SalesReport" + fileDate + ".txt", true))) {
            writerTwo.println("**TOTAL SALES**   " + totalSales.toString());
        } catch (IOException e) {
           System.err.println("Error appending entry. Msg: " + e.getMessage());
       }
    }
                /*try (Scanner vendingCSV = new Scanner(new File("data/vendingmachine.csv"))) {
                    while (vendingCSV.hasNextLine()) {
                        String vendingCSVOutput = vendingCSV.nextLine();
                        System.out.println(vendingCSVOutput);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Couldn't find vendingmachine.csv");

                }
                //try
                Scanner productSelection = new Scanner (System.in);
                //catch exceptions that do not fit selection format.
                String productSelectionStr = productSelection.nextLine();

                //reduce machine balance by selection price.
                //reduce machine inventory of item by 1.
                //return change.
                //record purchase in transaction log.
                //sout item and selection message

            }*/

        //Inventory invInit = new Inventory();
        //	invInit.buildInventory();
}