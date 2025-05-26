package org.demo;

import org.demo.model.Transaction;
import org.demo.service.TransactionManager;
import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class  FileManager {



    public static void saveTransactions(List<Transaction> transactions, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Transaction t : transactions) {
                writer.write(t.toString());
                writer.newLine();
            }
        }
    }

    public static void loadTransactions(TransactionManager manager, String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length != 4) continue;

                LocalDate date = LocalDate.parse(parts[0]);
                double amount = Double.parseDouble(parts[1]);
                String type = parts[2];
                String category = parts[3];


                Transaction transaction = new Transaction(date, amount, type, category);
                manager.addTransaction(transaction);
//                System.out.println(transaction);
            }
        }
    }

}

