package org.example;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class CurrencyConverterApplication {
    private FileParser fileParser;
    private CurrencyValidator currencyValidator;
    private CurrencyConverter currencyConverter;
    private ErrorHandler errorHandler;
    private FileWriter fileWriter;

    // Setters for the strategies
    public void setFileParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public void setCurrencyValidator(CurrencyValidator currencyValidator) {
        this.currencyValidator = currencyValidator;
    }

    public void setCurrencyConverter(CurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

  public void processTransactions(String inputFilePath, String outputFilePath) {
    File inputFile = new File(inputFilePath);
    File outputFile = new File(outputFilePath);
    
    List<Transaction> transactions = fileParser.parse(inputFile);
    List<Transaction> results = new ArrayList<>();
    
    for (Transaction transaction : transactions) {
        Transaction validatedTransaction = currencyValidator.validate(transaction);
        if (validatedTransaction.getStatus().equals("Success")) {
            results.add(currencyConverter.convert(validatedTransaction));
        } else {
            results.add(errorHandler.handleError(validatedTransaction, validatedTransaction.getStatus()));
        }
    }
    
    fileWriter.write(results, outputFile);
}
}


    ///Fix these errors with type mismatch