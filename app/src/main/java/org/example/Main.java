package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java -jar CurrencyConverter.jar <inputfile> <outputfile>");
            System.exit(1);
        }

        // Create the application
        CurrencyConverterApplication app = new CurrencyConverterApplication();

        // Set the strategies
        app.setCurrencyValidator(new BasicCurrencyValidator());
        app.setCurrencyConverter(new BasicCurrencyConverter());
        app.setErrorHandler(new BasicErrorHandler());

        // Process transactions
        String inputFilePath = args[0]; // Use the first command line argument as the input file path
        String outputFilePath = args[1]; // Use the second command line argument as the output file path

        // Determine the file format from the file extension
        String fileFormat = getFileExtension(new File(inputFilePath));

        // Set the appropriate file parser and writer based on the file format
        switch (fileFormat) {
            case "csv":
                app.setFileParser(new CsvFileParser());
                app.setFileWriter(new CsvFileWriter());
                break;
            case "json":
                app.setFileParser(new JsonFileParser());
                app.setFileWriter(new JsonFileWriter());
                break;
            case "xml":
                app.setFileParser(new XmlFileParser());
                app.setFileWriter(new XmlFileWriter());
                break;
            default:
                throw new IllegalArgumentException("Unsupported file format: " + fileFormat);
        }

        app.processTransactions(inputFilePath, outputFilePath);
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}