package org.example;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {
    @XmlElement
    private double amount;

    @XmlElement
    private String originalCurrency;
    @XmlElement
    private String targetCurrency;
    @XmlElement
    private String convertedAmount;
    @XmlElement
    private String status;

    public Transaction() {
    }

    public Transaction(double amount, String originalCurrency, String targetCurrency) {
        this.amount = amount;
        this.originalCurrency = originalCurrency;
        this.targetCurrency = targetCurrency;
    }

    // Getters
    public double getAmount() {
        return amount;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public String getConvertedAmount() {
        return convertedAmount;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setConvertedAmount(String convertedAmount) {
        this.convertedAmount = convertedAmount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

        public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "amount=" + amount +
            ", originalCurrency='" + originalCurrency + '\'' +
            ", targetCurrency='" + targetCurrency + '\'' +
            ", convertedAmount='" + convertedAmount + '\'' +
            ", status='" + status + '\'' +
            '}';
    }
}