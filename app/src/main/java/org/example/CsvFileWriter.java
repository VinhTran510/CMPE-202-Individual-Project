package org.example;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.opencsv.CSVWriter;

public class CsvFileWriter implements org.example.FileWriter {
    @Override
    public void write(List<Transaction> transactions, File file) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            // Write header
            String[] header = {"Amount", "OriginalCurrency", "TargetCurrency", "ConvertedAmount", "Status"};
            writer.writeNext(header);

            // Write transactions
            for (Transaction transaction : transactions) {
                String[] line = {
                        Double.toString(transaction.getAmount()),
                        transaction.getOriginalCurrency(),
                        transaction.getTargetCurrency(),
                        transaction.getConvertedAmount(),
                        transaction.getStatus()
                };
                writer.writeNext(line);
            }
        } catch (Exception e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}