package org.example;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class CsvFileParserTest {
    private CsvFileParser parser;

    @Before
    public void setUp() {
        parser = new CsvFileParser();
    }

    @Test
    public void testParse() {
        File file = new File("C:\\Users\\vinhr\\CMPE 202  Individual Project\\app\\Sample_InputOutput_Files\\input.csv");
        List<Transaction> transactions = parser.parse(file);
        assertEquals(13, transactions.size());

        Transaction firstTransaction = transactions.get(0);
        assertEquals(1000.0, firstTransaction.getAmount(), 0.001);
        assertEquals("USD", firstTransaction.getOriginalCurrency());
        assertEquals("EUR", firstTransaction.getTargetCurrency());

        Transaction lastTransaction = transactions.get(transactions.size() - 1);
        assertEquals(3500.0, lastTransaction.getAmount(), 0.001);
        assertEquals("677", lastTransaction.getOriginalCurrency());
        assertEquals("GBP", lastTransaction.getTargetCurrency());
    }
}