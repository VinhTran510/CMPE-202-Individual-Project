package org.example;


import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CurrencyConverterApplicationTest {
    private CurrencyConverterApplication app;
    private FileParser fileParser;
    private BasicCurrencyValidator currencyValidator;
    private BasicCurrencyConverter currencyConverter;
    private ErrorHandler errorHandler;
    private FileWriter fileWriter;

    @Before
    public void setUp() {
        app = new CurrencyConverterApplication();
        fileParser = mock(FileParser.class);
        currencyValidator = mock(BasicCurrencyValidator.class);
        currencyConverter = mock(BasicCurrencyConverter.class);
        errorHandler = mock(ErrorHandler.class);
        fileWriter = mock(FileWriter.class);

        app.setFileParser(fileParser);
        app.setCurrencyValidator(currencyValidator);
        app.setCurrencyConverter(currencyConverter);
        app.setErrorHandler(errorHandler);
        app.setFileWriter(fileWriter);
    }

    @Test
    public void testProcessTransactions() {
        Transaction transaction = new Transaction(1000.0, "USD", "EUR");
        transaction.setConvertedAmount("940.0");
        transaction.setStatus("Success");
        transaction.setStatus("Success");
        when(fileParser.parse(any(File.class))).thenReturn(List.of(transaction));
        when(currencyValidator.validate(any(Transaction.class))).thenReturn(transaction);
        when(currencyConverter.convert(any(Transaction.class))).thenReturn(transaction);

        app.processTransactions("C:\\Users\\vinhr\\CMPE 202  Individual Project\\app\\Sample_InputOutput_Files\\input.csv", "C:\\Users\\vinhr\\CMPE 202  Individual Project\\app\\Sample_InputOutput_Files\\output.csv");

        verify(fileParser).parse(any(File.class));
        verify(currencyValidator).validate(any(Transaction.class));
        verify(currencyConverter).convert(any(Transaction.class));
        verify(fileWriter).write(anyList(), any(File.class));
    }
}