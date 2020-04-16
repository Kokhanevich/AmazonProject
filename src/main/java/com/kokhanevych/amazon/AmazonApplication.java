package com.kokhanevych.amazon;

import com.kokhanevych.amazon.service.CsvParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AmazonApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazonApplication.class, args);
        CsvParserService csvParserService = new CsvParserService();
        csvParserService.printResults();
    }

}
