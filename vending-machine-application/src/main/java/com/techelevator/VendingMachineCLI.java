package com.techelevator;

import com.techelevator.view.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String HIDDEN_OPTION = "";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, HIDDEN_OPTION};
	private static final File VENDING_MACHINE_PRODUCTS = new File("./data/vendingmachine.csv");
VendingMachine vendMain = new VendingMachine();
	private Menu menu;
	private Menu purchaseMenu;


	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		Inventory machineInventory = new Inventory();
		machineInventory.initInventory();
	//	Inventory machineInventory = new Inventory();
	//		machineInventory.initInventory();
		//Start vending machine with full inventory.

		//===== you nay use/modify the existing Menu class or write your own ======
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vendMain.invList();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				(vendMain.getMachineInventory()).initInventory();
				vendMain.transactionMenuOptions();
			} else if (choice.equals(HIDDEN_OPTION)) {
				vendMain.salesReportOutput();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				return;
			}

			/*	while (true) {
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (purchaseChoice.equals(PURCHASE_MENU_FEED_MONEY)) {
						vendMain.addBalance();
					}
					if (purchaseChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
						vendMain.purchaseProduct();
					}
					if (purchaseChoice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
						vendMain.MachineReturnChange(vendMain.getMachineBalance());



						System.out.println("Please make a selection from the following. Use the slot location to make a selection");

						try (Scanner vendingCSV = new Scanner(new File("data/vendingmachine.csv"))) {
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
						//sout item and selection message*/


					}
				}
			}