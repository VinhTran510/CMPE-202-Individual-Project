package org.example;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonFileWriter implements FileWriter {
    @Override
    public void write(List<Transaction> transactions, File file) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Enable pretty printing

        TransactionList transactionList = new TransactionList(transactions);
        try {
            mapper.writeValue(file, transactionList);
        } catch (Exception e) {
            System.err.println("Error writing JSON file: " + e.getMessage());
        }
    }
}