package com.kokhanevych.amazon.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kokhanevych.amazon.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<String> getMostActiveUserProfiles(Integer limit) {
        return reviewRepository.getMostActiveUserProfiles(limit);
    }

    public List<String> getMostCommentedItemsId(Integer limit) {
        return reviewRepository.getMostCommentedItemsId(limit);
    }

    public List<String> getMostUsedWords(Integer limit) {
        List<String> reviews = reviewRepository.getReviews();
        Map<String, Long> topWords = selectAndCountWords(reviews);
        return topWords.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .map(Map.Entry::getKey)
                .limit(limit)
                .collect(Collectors.toList());
    }

    private Map<String, Long> selectAndCountWords(List<String> reviews) {
        Map<String, Long> usedWords = new HashMap<>();
        reviews.forEach(review -> {
            String[] words = review.toUpperCase()
                    .replaceAll("\\W+", " ")
                    .split(" ");

            Arrays.stream(words).forEach(word -> {
                if (usedWords.containsKey(word)) {
                    Long value = usedWords.remove(word);
                    usedWords.put(word, ++value);
                } else {
                    usedWords.put(word, 0L);
                }
            });
        });
        return usedWords;
    }
}
