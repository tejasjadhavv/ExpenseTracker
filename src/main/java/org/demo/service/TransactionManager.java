package org.demo.service;


import org.demo.model.Transaction;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionManager {
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction)
    {
        transactions.add(transaction);

    }

    public List<Transaction> getMonthlySummary(int year, Month month) {
        return transactions.stream()
                .filter(t -> t.getDate().getYear() == year && t.getDate().getMonth() == month)
                .collect(Collectors.toList());
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public double calculateTotal(String type, int year, Month month) {
        return transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase(type))
                .filter(t -> t.getDate().getYear() == year && t.getDate().getMonth() == month)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
    public boolean deleteTransaction(int index) {
        if (index >= 0 && index < transactions.size()) {
            transactions.remove(index);
            return true;
        }
        return false;
    }


}

