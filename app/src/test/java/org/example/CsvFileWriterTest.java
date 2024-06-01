package org.example;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.opencsv.CSVReader;

public class CsvFileWriterTest {
    private CsvFileWriter writer;

    @Before
    public void setUp() {
        writer = new CsvFileWriter();
    }

    @Test
    public void testWrite() {
        Transaction transaction = new Transaction(1000.0, "USD", "EUR");
        transaction.setConvertedAmount("940.0");
        transaction.setStatus("Success");
        List<Transaction> transactions = Arrays.asList(transaction);

        File tempFile;
        try {
            tempFile = File.createTempFile("test", ".csv");
            writer.write(transactions, tempFile);

            try (CSVReader reader = new CSVReader(new FileReader(tempFile))) {
                String[] nextLine;
                int lineCount = 0;
                while ((nextLine = reader.readNext()) != null) {
                    if (lineCount > 0) { // Skip header
                        assertEquals("Success", nextLine[4]);
                        assertEquals(1000.0, Double.parseDouble(nextLine[0]), 0.001);
                        assertEquals("USD", nextLine[1]);
                        assertEquals("EUR", nextLine[2]);
                        assertEquals(940.0, Double.parseDouble(nextLine[3]), 0.001);
                    }
                    lineCount++;
                }
            }
        } catch (Exception e) {
            System.err.println("Error writing or reading CSV file: " + e.getMessage());
        }
    }
}