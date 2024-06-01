package org.example;

import java.io.File;
import java.util.List;

public interface FileWriter {
    void write(List<Transaction> transactions, File file);
}