package com.kokhanevych.amazon.controller;

import java.util.List;

import com.kokhanevych.amazon.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/getProfiles/{limit}")
    public List<String> getMostActiveUsersProfile(@PathVariable("limit") Integer limit) {
        return reviewService.getMostActiveUserProfiles(limit);
    }

    @GetMapping("/getItems/{limit}")
    public List<String> getMostCommentedItemsId(@PathVariable("limit") Integer limit) {
        return reviewService.getMostCommentedItemsId(limit);
    }

    @GetMapping("/getWords/{limit}")
    public List<String> getMostPopularWords(@PathVariable("limit") Integer limit) {
        return reviewService.getMostUsedWords(limit);
    }
}
