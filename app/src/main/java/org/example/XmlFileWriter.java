package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class XmlFileWriter implements FileWriter {
    @Override
    public void write(List<Transaction> transactions, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(TransactionList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            TransactionList transactionList = new TransactionList(transactions);
            marshaller.marshal(transactionList, new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("Error writing XML file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}