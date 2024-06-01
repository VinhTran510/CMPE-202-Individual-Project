package org.example;

import java.io.File;
import java.util.List;

public interface FileParser {
    List<Transaction> parse(File file);
}