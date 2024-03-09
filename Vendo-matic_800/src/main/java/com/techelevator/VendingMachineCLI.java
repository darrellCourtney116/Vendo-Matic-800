package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.Purchase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.*;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";

	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String MAIN_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String MAIN_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String MAIN_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { MAIN_MENU_OPTION_FEED_MONEY, MAIN_MENU_OPTION_SELECT_PRODUCT, MAIN_MENU_OPTION_FINISH_TRANSACTION };

	private Menu menu;

	Purchase vendingMachineItems = new Purchase();



	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				vendingMachineItems.displayVendingItems();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (vendingMachineItems.isPurchaseMenu()) {
					// go to purchase menu
					System.out.println("Current balance: $" + vendingMachineItems.getMoney());
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (purchaseChoice.equals(MAIN_MENU_OPTION_FEED_MONEY)) {
						// feed money
						vendingMachineItems.feedMoney();

					} else if (purchaseChoice.equals(MAIN_MENU_OPTION_SELECT_PRODUCT)) {
						// select product
						vendingMachineItems.selectProduct();

					} else if (purchaseChoice.equals(MAIN_MENU_OPTION_FINISH_TRANSACTION)) {
						// finish transaction
						vendingMachineItems.finishTransaction();
						break;
					}
				}

			} else {
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

}
