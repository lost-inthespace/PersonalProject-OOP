package org.example;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

class Bank {
    private double balance;
    private final ArrayList<Double> purchaseAmount = new ArrayList<>();
    private final ArrayList<String> storeName = new ArrayList<>();
    private final ArrayList<String> category = new ArrayList<>();
    private static final Map<Integer, String> stringToNumber = new HashMap<>();

    public Bank() {
        stringToNumber.put(1, "new purchase");
        stringToNumber.put(2, "new deposit");
        stringToNumber.put(3, "view purchases");
        stringToNumber.put(4, "view balance");
        stringToNumber.put(5, "exit");
        stringToNumber.put(6, "help");
    }

    public void setBalance(double balance) {
        this.balance = balance;
        System.out.println("the balance is: " + this.balance + " SAR");
    }

    public void update() {
        for (Double Purr : purchaseAmount) {
            this.balance = this.balance + Purr;
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double newDepoAmount) {
        this.balance += newDepoAmount;
    }

    public void operations(String name, double amount, String cat) {
        storeName.add(name);
        purchaseAmount.add(amount);
        category.add(cat);
        balance -= amount;
    }

    public boolean testUserInput(String uI, boolean includeExit) {
        boolean result = false;
        if (!includeExit) {
            stringToNumber.remove(5);
        }
        for (Map.Entry<Integer, String> entry : stringToNumber.entrySet()) {
            if (uI.equalsIgnoreCase(entry.getValue())) {
                result = true;
                break;
            }
            else if (testInputIfItIsInteger(uI)){
                result = true;
                break;
            }
        }
        if (!result) {
            System.out.println("please enter valid input.");
        }
        return result;
    }

    public boolean testInputIfItIsInteger(String input) {
        for (Map.Entry<Integer, String> entry : stringToNumber.entrySet()) {
            if (input.equals(entry.getKey().toString())) {
                return true;
            }
        }
        return false;
    }

    public void printPurchases() {
        for (int i = 0; i < purchaseAmount.size(); i++) {
            System.out.println(storeName.get(i) + " " + purchaseAmount.get(i) + " SAR" + " for category: " + category.get(i));
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Bank mohammadAccount = new Bank();
        System.out.println("Welcome to your Personal Finance Tracker!\nPlease Enter your Current Balance");
        mohammadAccount.setBalance(input.nextDouble());
        input.nextLine(); // consume newline character
        boolean exit = false;
        while (!exit) {
            System.out.println("Please enter the command or 6 for help");
            String userInput = input.nextLine();
            if (mohammadAccount.testUserInput(userInput, true) || mohammadAccount.testInputIfItIsInteger(userInput)) {
                if (userInput.equalsIgnoreCase("new purchase") || userInput.equals("1")) {
                    System.out.println("How many Purchases you want to registered?");
                    int ite = input.nextInt();
                    for(int sc = 0; sc < ite; sc++){
                        System.out.println("Enter the name of the store:");
                        input.nextLine();
                        String store = input.nextLine();
                        System.out.println("Enter the amount of the purchase:");
                        double amount = input.nextDouble();
                        input.nextLine(); // consume newline character
                        System.out.println("Enter the category of the purchase:");
                        String cat = input.nextLine();
                        mohammadAccount.operations(store, amount, cat);;
                    }
                    System.out.println(ite+" purchases is registered successfully");
                } else if (userInput.equalsIgnoreCase("new deposit") || userInput.equals("2")) {
                    System.out.println("Enter the amount of the deposit:");
                    double amount = input.nextDouble();
                    input.nextLine(); // consume newline character
                    mohammadAccount.deposit(amount);
                } else if (userInput.equalsIgnoreCase("view purchases") || userInput.equals("3")) {
                    mohammadAccount.printPurchases();
                } else if (userInput.equalsIgnoreCase("view balance") || userInput.equals("4")) {
                    double currentBalance = mohammadAccount.getBalance();
                    System.out.println(currentBalance+" SAR");
                } else if (userInput.equalsIgnoreCase("exit") || userInput.equals("5")) {
                    exit = true;
                } else if (userInput.equalsIgnoreCase("help") || userInput.equals("6")) {
                    for (Map.Entry<Integer, String> entry : stringToNumber.entrySet()) {
                        //above is for-each
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                }
            }
        }
        System.out.println("Thank you!");
    }
}
