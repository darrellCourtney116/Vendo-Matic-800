package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Purchase{

    Scanner userInput = new Scanner(System.in);

    private double money;

    boolean ifFalse = false;

    private boolean isPurchaseMenu;

    public double getMoney() {
        return Math.round(money * 100.00)/100.00;
    }

    public boolean isPurchaseMenu() { return isPurchaseMenu  = true; }

    public Purchase() {
        itemsArray();
    }
    List<Items> productList = new ArrayList<>();
    Log write = new Log();

    public void itemsArray(){
        File vendingMachineFile = new File("vendingmachine.csv");
        try (Scanner itemInput = new Scanner(vendingMachineFile)) {
            while (itemInput.hasNextLine()) {
                String lineOfText = itemInput.nextLine();
                String[] productArr = lineOfText.split("\\|");
                String slotCode = productArr[0];
                String itemName = productArr[1];
                double price = Double.parseDouble(productArr[2]);
                String type = productArr[3];
                productList.add(new Items(slotCode, itemName, price, type, 5));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }


public void displayVendingItems() {
        for (Items item : productList) {
            if (item.getStock() > 0) {
                System.out.println(item.getSlotLocation() + ") " + item.getName() + " | $" + item.getPrice() + " | " + "Amount in stock: " + item.getStock());
            } else {
                System.out.println(item.getSlotLocation() + ") " + item.getName() + " | " + " **OUT OF STOCK**");
                // if item stock is 0 it prints out of stock

            }
        }
    }


    public void feedMoney() throws InputMismatchException{
        double dollarAmount = 0.0;
        String transactionType = "FEED MONEY: ";
        System.out.println("Current money provided: $"+ getMoney());
        //prints current balance in vending machine
        try {
            System.out.println("Insert dollar amount : ");
            dollarAmount = userInput.nextDouble();
        } catch (InputMismatchException e){
            System.out.println("Invalid input");;
        }
        // adds userinput to vending machine balance
        money += dollarAmount;
        userInput.nextLine();
        // adds action to log
        write.logWriter(transactionType, dollarAmount, getMoney());
    }


    public void selectProduct(){
        isPurchaseMenu = true;
        // displays items
        displayVendingItems();
        System.out.println("Enter item code : ");
        String code = userInput.nextLine();
        // gets code from user input and sends it to the getItems method
        getItems(code);
    }

    public void getItems(String code){

        for(Items product : productList){

            if (product.getSlotLocation().equalsIgnoreCase(code)) {
                // if code is equal to slot code
                if(product.getStock() > 0) {
                    // has stock
                    if(product.getPrice() > getMoney()) {
                        System.out.println("Not enough money! Current balance: $" + getMoney());
                    } else if (product.getType().equals("Chip")) {
                        product.decrement();
                        money -= product.getPrice();
                        // if item is a chip
                        System.out.println(product.getName() + " | " + product.getPrice() + " | Money remaining: $" + getMoney());
                        System.out.println("Crunch Crunch, Yum!");
                        // adds action to log
                        write.logWriter(product.getName() + " " + product.getSlotLocation(), product.getPrice(), getMoney());

                    } else if (product.getType().equals("Candy")) {
                        product.decrement();
                        money -= product.getPrice();
                        // if item is candy
                        System.out.println(product.getName() + " | " + product.getPrice() + " | Money remaining: $" + getMoney());
                        System.out.println("Munch Munch, Yum!");
                        // adds action to log
                        write.logWriter(product.getName() + " " + product.getSlotLocation(), product.getPrice(), getMoney());

                    } else if (product.getType().equals("Drink")) {
                        product.decrement();
                        money -= product.getPrice();
                        // if item is a drink
                        System.out.println(product.getName() + " | " + product.getPrice() + " | Money remaining: $" + getMoney());
                        System.out.println("Glug Glug, Yum!");
                        // adds action to log
                        write.logWriter(product.getName() + " " + product.getSlotLocation(), product.getPrice(), getMoney());

                    } else if (product.getType().equals("Gum")) {
                        product.decrement();
                        money -= product.getPrice();
                        // if item is gum
                        System.out.println(product.getName() + " | " + product.getPrice() + " | Money remaining: $" + getMoney());
                        System.out.println("Chew Chew, Yum!");
                        // adds action to log
                        write.logWriter(product.getName() + " " + product.getSlotLocation(), product.getPrice(), getMoney());

                    }
                    break;
                    // ends loop
                } else if (product.getStock() == 0) {
                    // sold out
                    System.out.println(product.getName() + " | " + product.getPrice() + " | SOLD OUT");
                    break;
                }
            } else if (code.isEmpty()){
                // if input is empty
                System.out.println("Invalid input");
                return;
            }else if (!product.getSlotLocation().equalsIgnoreCase(code)) {
                // if code length is one character
                if (code.length() == 1){
                    System.out.println("Item does not exist");
                    break;
                }
                // if code does not equal slot code
                char[] letters = {'A', 'B', 'C', 'D', 'a', 'b', 'c', 'd'};
                for (int i = 0; i < letters.length; i++){
                    // checks if first char is A, B, C, or D
                    if (!(code.charAt(0) == letters[i])){
                        ifFalse = false;
                    }
                    if (code.charAt(0) == letters[i]){
                        // if first char is A, B, C, or D, it breaks the loop and continues
                        ifFalse = true;
                        break;
                    }
                } if(ifFalse == false) {
                    // if the letterCode does not equal any of the char in the loop it does not exist
                    System.out.println("Item does not exist");
                    return;
                    // returns to purchase menu
                }
                else if (code.length() > 1) {
                    // checks if the length of code is longer than 1 character
                    int numberCode = Integer.valueOf(code.substring(1, 2));
                    if (numberCode > 4 || numberCode < 0) {
                        // checks if the 2nd char (number code) is bigger than 4 or less than 0
                        // checks less than 0 because if char at (0) is a letter it becomes -1
                        System.out.println("Item does not exist");
                        return;
                    }
                }
                }
            }
    }

    public void finishTransaction(){
        isPurchaseMenu = false;
        // gets original balance left
        double originalBalance = getMoney();

        int nickles = 0;
        int dimes = 0;
        int quarters = 0;

        while (getMoney() > 0){
            // loops through money balance while it is above 0
            money -= .05;
            // subtracts 5 cents each time it loops
            nickles++;

            if (nickles == 2){
                // if nickles = 2 it becomes a dime
                dimes++;
                nickles = 0;
            }
            if (dimes == 2 && nickles == 1){
                // if two dimes and a nickle it becomes a quarter
                quarters++;
                dimes = 0;
                nickles = 0;
            }
            if (getMoney() == 0){
                // ends loop when balance is at 0
                break;
            }
        }
        System.out.println(originalBalance);
        System.out.println("Your change: " + quarters + " Quarters ," + dimes + " Dimes and," + nickles + " Nickels.");
        // adds action to log
        write.logWriter("GIVE CHANGE: ", originalBalance, 0.00);
        // sets money to 0
        money = 0;

    }



}
