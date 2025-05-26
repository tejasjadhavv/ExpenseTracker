package org.demo;

import org.demo.model.Transaction;
import org.demo.service.TransactionManager;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        TransactionManager manager = new TransactionManager();
        String url="C:\\Users\\ASUS\\Documents\\ExpenseTracker\\src\\";

        File usersDir = new File(url+"users");
        if (!usersDir.exists()) {
            usersDir.mkdir();
        }

        System.out.print("Enter your username: ");
        String username = scanner.nextLine().trim().toLowerCase();
        String fileName = url +"users\\" + username + ".txt";

        File userFile = new File(fileName);
        if (userFile.exists()) {
            FileManager.loadTransactions(manager, fileName);
            System.out.println("Welcome back, " + username + "! Transactions loaded.");
        } else {
            System.out.println("New user detected. Creating new file for " + username);
            userFile.createNewFile();
        }

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Monthly Summary");
            System.out.println("4. Delete a Transaction");
            System.out.println("5. Exit");


            switch (scanner.nextInt()) {
                case 1 -> {
                    addTransaction(scanner, manager, "Income");
                    FileManager.saveTransactions(manager.getAllTransactions(), fileName);
                    System.out.println("Transactions saved to: " + fileName);
                }
                case 2 -> {
                    addTransaction(scanner, manager, "Expense");
                    FileManager.saveTransactions(manager.getAllTransactions(), fileName);
                    System.out.println("Transactions saved to: " + fileName);
                }
                case 3 -> viewSummary(scanner, manager);

                case 4 -> deleteTransaction(scanner, manager, fileName);
                case 5 -> running = false;

                default -> System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }



    private static void addTransaction(Scanner scanner, TransactionManager manager, String type) {
        scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        String category = type.equals("Income") ? "Salary/Business" : "Food/Rent/Travel";
        System.out.print("Choose subcategory from [" + category + "]: ");
        category = scanner.nextLine();

        LocalDate date = LocalDate.now(); // assuming current date
        Transaction transaction = new Transaction(date, amount, type, category);
        manager.addTransaction(transaction);

        System.out.println(type + " added successfully!");
    }

    private static void viewSummary(Scanner scanner, TransactionManager manager) {
        System.out.print("Enter year (e.g. 2025): ");
        int year = scanner.nextInt();

        System.out.print("Enter month (1-12): ");
        int month = scanner.nextInt();
//        System.out.println(manager);
        var summary = manager.getMonthlySummary(year, Month.of(month));
        double totalIncome = manager.calculateTotal("Income", year, Month.of(month));
        double totalExpense = manager.calculateTotal("Expense", year, Month.of(month));

        System.out.println("\n--- Transation Statement ---");
        for (var t : summary) {
            System.out.println(t.getDate() + " - " + t.getType() + ": $" + t.getAmount() );
        }
        double foodExpense = 0.0;
        double rentExpense = 0.0;
        double travelExpense = 0.0;
        for (Transaction t : summary) {
//            System.out.println(t.getDate() + " - " + t.getType() + " (" + t.getCategory() + "): $" + t.getAmount());

            if (t.getType().equalsIgnoreCase("EXPENSE")) {
                switch (t.getCategory().toUpperCase()) {
                    case "FOOD" -> foodExpense += t.getAmount();
                    case "RENT" -> rentExpense += t.getAmount();
                    case "TRAVEL" -> travelExpense += t.getAmount();
                }
            }
        }

        System.out.println("\n--- Expense Breakdown ---");
        System.out.printf("FOOD   : $%.2f\n", foodExpense);
        System.out.printf("RENT   : $%.2f\n", rentExpense);
        System.out.printf("TRAVEL : $%.2f\n", travelExpense);
        System.out.println("\n--- OverAll Summary ---");
        System.out.println("Total Income: $" + totalIncome);
        System.out.println("Total Expenses: $" + totalExpense);
        System.out.println("Net Savings: $" + (totalIncome - totalExpense));
    }
    private static void deleteTransaction(Scanner scanner, TransactionManager manager, String fileName) {
        var transactions = manager.getAllTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No transactions to delete.");
            return;
        }

        System.out.println("\n--- Transaction List ---");
        for (int i = 0; i < transactions.size(); i++) {
            Transaction t = transactions.get(i);
            System.out.println(i + ": " + t.getDate() + " - " + t.getType() + " - $" + t.getAmount() );
        }

        System.out.print("Enter index of transaction to delete: ");
        int index = scanner.nextInt();

        if (manager.deleteTransaction(index)) {
            try {
                FileManager.saveTransactions(manager.getAllTransactions(), fileName);
                System.out.println("Transaction deleted and file updated.");
            } catch (IOException e) {
                System.out.println("Failed to save updated file: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid index.");
        }
    }

}
