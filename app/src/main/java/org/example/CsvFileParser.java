package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileParser implements FileParser {
    @Override
    public List<Transaction> parse(File file) {
        if (!file.exists()) {
            System.out.println("File does not exist");
        } else if (!file.canRead()) {
            System.out.println("File cannot be read");
        }
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double amount = Double.parseDouble(values[0]);
                String originalCurrency = values[1].trim();
                String targetCurrency = values[2].trim();
                transactions.add(new Transaction(amount, originalCurrency, targetCurrency));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return transactions;
    }
}