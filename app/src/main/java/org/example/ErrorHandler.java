package org.example;

public interface ErrorHandler {
    Transaction handleError(Transaction transaction, String errorMessage);
}