package org.example;

public class BasicErrorHandler implements ErrorHandler {
    @Override
    public Transaction handleError(Transaction transaction, String errorMessage) {
        transaction.setStatus(errorMessage);
        return transaction;
    }
}