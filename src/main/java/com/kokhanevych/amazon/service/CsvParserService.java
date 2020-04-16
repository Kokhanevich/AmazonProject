package com.kokhanevych.amazon.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import au.com.bytecode.opencsv.CSVReader;
import org.springframework.stereotype.Service;

@Service
public class CsvParserService {
    private static final int FIELDS_COUNT = 10;
    private static final char DEFAULT_SEPARATOR = ',';
    private static final String FILE_NAME = "Test.csv";
    private List<String[]> strings = new ArrayList<>();

    public List<String[]> parseFile(String fileName) throws IOException {
        CSVReader csvReader = null;
        ClassLoader classLoader = CsvParserService.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        try {
            csvReader = new CSVReader(new FileReader(file), DEFAULT_SEPARATOR, '"', 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return csvReader.readAll();
    }

    public void printResults(){
        try {
            strings = parseFile(FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String[] stringsArr : strings) {
            System.out.println("ProductId: " + stringsArr[1]);
            System.out.println("UserId: " + stringsArr[2]);
            System.out.println("ProfileName: " + stringsArr[3]);
            System.out.println("Text: " + stringsArr[9]);
        }
    }
}
