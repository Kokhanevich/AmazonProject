package com.kokhanevych.amazon.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import com.kokhanevych.amazon.entity.Review;
import com.kokhanevych.amazon.repository.ReviewRepository;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvParserService {
    private static Logger logger = LoggerFactory
            .getLogger(CsvParserService.class);

    @Autowired
    private ReviewRepository reviewRepository;
    private static final char DEFAULT_SEPARATOR = ',';
    private static final String FILE_NAME = "Reviews.csv";
//    private static final String FILE_NAME = "Test.csv";
    private List<String[]> strings = new ArrayList<>();

    private List<String[]> parseFile(String fileName) throws IOException {
        logger.trace("Init parse file method");
        CSVReader csvReader;
        char symbol = 127;
        ClassLoader classLoader = CsvParserService.class.getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader
                .getResource(fileName)).getFile());
        try {
            csvReader = new CSVReader(new FileReader(file, StandardCharsets.UTF_8), DEFAULT_SEPARATOR, symbol, 1);
            logger.trace("Parsing finished success");
        } catch (FileNotFoundException e) {
            logger.error(String.format("Can’t read file %s", fileName), e);
            throw new RuntimeException(e);
        }
        return csvReader.readAll();
    }

    @PostConstruct
    public void addReviewToDb() {
        try {
            strings = parseFile(FILE_NAME);
        } catch (IOException e) {
            logger.error(String.format("Can’t parse file %s", FILE_NAME), e);
            throw new RuntimeException(e);
        }
        for (String[] stringsArr : strings) {
            Review review = createReview(stringsArr);
            reviewRepository.save(review);
        }
    }

    private Review createReview(String[] strings) {
        Review review = new Review();
        review.setProductId(strings[1]);
        review.setUserId(strings[2]);
        review.setProfileName(strings[3]);
        review.setSummary(strings[8]);
        review.setTextReview(strings[9]);
        return review;
    }
}
