package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlFileParser implements FileParser {
    @Override
    public List<Transaction> parse(File file) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList transactionNodes = doc.getElementsByTagName("transaction");
            for (int i = 0; i < transactionNodes.getLength(); i++) {
                Node node = transactionNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    double amount = Double.parseDouble(element.getElementsByTagName("Amount").item(0).getTextContent());
                    String originalCurrency = element.getElementsByTagName("OriginalCurrency").item(0).getTextContent().trim();
                    String targetCurrency = element.getElementsByTagName("TargetCurrency").item(0).getTextContent().trim();
                    transactions.add(new Transaction(amount, originalCurrency, targetCurrency));
                }
            }
        } catch (Exception e) {
            // Handle exception
        }
        return transactions;
    }
}