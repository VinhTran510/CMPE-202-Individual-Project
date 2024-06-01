package org.example;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class BasicCurrencyValidatorTest {
    private BasicCurrencyValidator validator;

    @Before
    public void setUp() {
        validator = new BasicCurrencyValidator();
    }

    @Test
    public void testValidateWithValidCurrencies() {
        Transaction transaction = new Transaction(10.0, "USD", "EUR");
        Transaction validatedTransaction = validator.validate(transaction);
        assertEquals("Success", validatedTransaction.getStatus());
    }

    @Test
    public void testValidateWithInvalidOriginalCurrency() {
        Transaction transaction = new Transaction(10.0, "Invalid", "EUR");
        Transaction validatedTransaction = validator.validate(transaction);
        assertEquals("Invalid original currency code", validatedTransaction.getStatus());
    }

    @Test
    public void testValidateWithInvalidTargetCurrency() {
        Transaction transaction = new Transaction(10.0, "USD", "Invalid");
        Transaction validatedTransaction = validator.validate(transaction);
        assertEquals("Invalid target currency code", validatedTransaction.getStatus());
    }


}