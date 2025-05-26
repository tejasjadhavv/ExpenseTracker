package org.demo.model;



import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private double amount;
    private String type; // "Income" or "Expense"
    private String category;


    public Transaction(LocalDate date, double amount, String type, String category) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }



    @Override
    public String toString() {
        return date + "," + amount + "," + type + "," + category ;
    }

}
