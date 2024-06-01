package org.example;

import java.util.HashSet;
import java.util.Set;

public class BasicCurrencyValidator implements CurrencyValidator {
    private final Set<String> validCurrencyCodes;

    public BasicCurrencyValidator() {
        validCurrencyCodes = new HashSet<>();
        validCurrencyCodes.add("USD");
        validCurrencyCodes.add("EUR");
        validCurrencyCodes.add("GBP");
        validCurrencyCodes.add("AUD");
        validCurrencyCodes.add("CAD");
        validCurrencyCodes.add("CHF");
        validCurrencyCodes.add("JPY");
        validCurrencyCodes.add("INR");
    }

    @Override
    public Transaction validate(Transaction transaction) {
        String originalCurrency = transaction.getOriginalCurrency();
        String targetCurrency = transaction.getTargetCurrency();
        if (!validCurrencyCodes.contains(originalCurrency)) {
            if (originalCurrency.matches(".*\\d.*")) {
                transaction.setStatus("Invalid original currency code should not contains numbers");
            } else {
                transaction.setStatus("Invalid original currency code");
            }
        } else if (!validCurrencyCodes.contains(targetCurrency)) {
            if (targetCurrency.matches(".*\\d.*")) {
                transaction.setStatus("Invalid target currency code should not contains numbers");
            } else {
                transaction.setStatus("Invalid target currency code");
            }
        } else {
            transaction.setStatus("Success");
        }
        return transaction;
    }
}