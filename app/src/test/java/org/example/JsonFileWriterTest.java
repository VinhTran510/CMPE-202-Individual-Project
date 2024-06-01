package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonFileWriterTest {
    private JsonFileWriter writer;
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        writer = new JsonFileWriter();
        mapper = new ObjectMapper();
    }

    @Test
    public void testWrite() throws IOException {
        Transaction transaction = new Transaction(1000.0, "USD", "EUR");
        transaction.setConvertedAmount("940.0");
        transaction.setStatus("Success");
        List<Transaction> transactions = Arrays.asList(transaction);

        File tempFile = File.createTempFile("test", ".json");
        writer.write(transactions, tempFile);

        ArrayNode array = (ArrayNode) mapper.readTree(tempFile);
        assertEquals(1, array.size());

        ObjectNode transactionNode = (ObjectNode) array.get(0);
        assertEquals("Success", transactionNode.get("status").asText());
        assertEquals(1000.0, transactionNode.get("amount").asDouble(), 0.001);
        assertEquals("USD", transactionNode.get("originalCurrency").asText());
        assertEquals("EUR", transactionNode.get("targetCurrency").asText());
        assertEquals(940.0, transactionNode.get("convertedAmount").asDouble(), 0.001);
}
}