package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Before;
import org.junit.Test;

public class BasicCurrencyConverterTest {
    private BasicCurrencyConverter converter;

    @Before
    public void setUp() {
        converter = new BasicCurrencyConverter();
    }

    @Test
    public void testConvert() {
        Transaction transaction = new Transaction(10.0, "USD", "EUR");

        Transaction convertedTransaction = converter.convert(transaction);

        assertEquals("Success", convertedTransaction.getStatus());
        assertEquals(String.valueOf(10.0 * 0.94), convertedTransaction.getConvertedAmount());
    }

    @Test
    public void testConvertWithInvalidCurrency() {
        Transaction transaction = new Transaction(10.0, "Invalid", "Currency");

        assertThrows(IllegalArgumentException.class, () -> converter.convert(transaction));
    }
}