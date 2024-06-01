package org.example;

import java.util.HashMap;
import java.util.Map;

public class BasicCurrencyConverter implements CurrencyConverter {
    private final Map<String, Double> exchangeRates;

    public BasicCurrencyConverter() {
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD-EUR", 0.94);
        exchangeRates.put("EUR-USD", 1 / 0.94);
        exchangeRates.put("EUR-GBP", 0.86);
        exchangeRates.put("GBP-EUR", 1 / 0.86);
        exchangeRates.put("GBP-INR", 103.98);
        exchangeRates.put("INR-GBP", 1 / 103.98);
        exchangeRates.put("AUD-CAD", 0.89);
        exchangeRates.put("CAD-AUD", 1 / 0.89);
        exchangeRates.put("CAD-USD", 0.73);
        exchangeRates.put("USD-CAD", 1 / 0.73);
        exchangeRates.put("CHF-AUD", 1.69);
        exchangeRates.put("AUD-CHF", 1 / 1.69);
        exchangeRates.put("USD-CHF", 0.91);
        exchangeRates.put("CHF-USD", 1 / 0.91);
        exchangeRates.put("JPY-USD", 0.0065);
        exchangeRates.put("USD-JPY", 1 / 0.0065);
    }

    @Override
    public Transaction convert(Transaction transaction) {
        String key = transaction.getOriginalCurrency() + "-" + transaction.getTargetCurrency();
        Double exchangeRate = exchangeRates.get(key);
        if (exchangeRate != null) {
            double convertedAmount = transaction.getAmount() * exchangeRate;
            transaction.setConvertedAmount(String.valueOf(convertedAmount));
            transaction.setStatus("Success");
        } else {
            throw new IllegalArgumentException("No exchange rate available for " + key);
        }
        return transaction;
    }

}