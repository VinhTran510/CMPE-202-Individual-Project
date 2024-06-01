package org.example;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class XmlFileWriterTest {
    private XmlFileWriter writer;
    private JAXBContext context;

    @Before
    public void setUp() throws Exception {
        writer = new XmlFileWriter();
        context = JAXBContext.newInstance(Transaction.class);
    }

    
    @Test
    public void testWrite() throws Exception {
        Transaction transaction = new Transaction(1000.0, "USD", "EUR");
        transaction.setConvertedAmount("940.0");
        transaction.setStatus("Success");
        List<Transaction> transactions = Arrays.asList(transaction);
    
        System.out.println("Original Transaction: " + transaction);  // Print original transaction
    
        File tempFile = File.createTempFile("test", ".xml");
        writer.write(transactions, tempFile);
    
        // Add a delay before reading the file
        try {
            Thread.sleep(2000);  // wait for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Transaction transactionFromFile = (Transaction) unmarshaller.unmarshal(tempFile);
    
        System.out.println("Unmarshalled Transaction: " + transactionFromFile);  // Print unmarshalled transaction
    
        assertEquals("Success", transactionFromFile.getStatus());
        assertEquals(1000.0, transactionFromFile.getAmount(), 0.001);
        assertEquals("USD", transactionFromFile.getOriginalCurrency());
        assertEquals("EUR", transactionFromFile.getTargetCurrency());
        assertEquals(940.0, Double.parseDouble(transactionFromFile.getConvertedAmount()), 0.001);
    }
}