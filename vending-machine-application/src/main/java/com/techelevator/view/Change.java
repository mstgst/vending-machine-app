package com.techelevator.view;

import java.math.BigDecimal;

public class Change {

    private static int pennyCount;
    private static int nickelCount;
    private static int dimeCount;
    private static int quarterCount;
    private static final BigDecimal penny = new BigDecimal("0.01");
    private static final BigDecimal nickel = new BigDecimal("0.05");
    private static final BigDecimal dime = new BigDecimal("0.10");
    private static final BigDecimal quarter = new BigDecimal("0.25");
    private static BigDecimal placeholderChange;

    public Change() {
        this.pennyCount = 0;
        this.nickelCount = 0;
        this.dimeCount = 0;
        this.quarterCount = 0;
    }

    public static int getPennyCount() {
        return pennyCount;
    }

    public static int getNickelCount() {
        return nickelCount;
    }

    public static int getDimeCount() {
        return dimeCount;
    }

    public static int getQuarterCount() {
        return quarterCount;
    }

    public static BigDecimal getPlaceholderChange() {
        return placeholderChange;
    }

    public static void returnChange(BigDecimal machineBalance) {
        placeholderChange = machineBalance;
        while (machineBalance.compareTo(quarter) >= 0) {
            machineBalance = machineBalance.subtract(quarter);
            quarterCount++;
        }
        while (machineBalance.compareTo(dime) >= 0) {
            machineBalance = machineBalance.subtract(dime);
            dimeCount++;
        }
        while (machineBalance.compareTo(nickel) >= 0) {
            machineBalance = machineBalance.subtract(nickel);
            nickelCount++;
        }
        while (machineBalance.compareTo(penny) >= 0) {
            machineBalance = machineBalance.subtract(penny);
            pennyCount++;
        }

        //return ("Your total change is $" + placeholderChange + " , you will receive " + quarterCount + " quarters, " + dimeCount + " dimes, " + nickelCount + " nickels, and " + pennyCount + "pennies.");
    }
}
