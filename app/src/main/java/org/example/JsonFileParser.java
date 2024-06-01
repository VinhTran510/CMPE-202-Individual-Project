package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JsonFileParser implements FileParser {
    @Override
    public List<Transaction> parse(File file) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray jsonArray = jsonObject.getJSONArray("transactions");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject transactionObject = jsonArray.getJSONObject(i);
                double amount = transactionObject.getDouble("Amount");
                String originalCurrency = transactionObject.getString("OriginalCurrency");
                String targetCurrency = transactionObject.getString("TargetCurrency");
                transactions.add(new Transaction(amount, originalCurrency, targetCurrency));
            }
        } catch (IOException e) {
            // Handle exception
        }
        return transactions;
    }
}