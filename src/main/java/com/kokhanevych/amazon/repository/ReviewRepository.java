package com.kokhanevych.amazon.repository;

import java.util.List;

import com.kokhanevych.amazon.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value =
            "SELECT profile_name FROM reviews GROUP BY profile_name "
                    + "ORDER BY COUNT(profile_name) DESC LIMIT ?1;",
            nativeQuery = true)
    List<String> getMostActiveUserProfiles(Integer limit);

    @Query(value =
            "SELECT product_id FROM reviews GROUP BY product_id "
                    + "ORDER BY COUNT(product_id) DESC LIMIT ?1;",
            nativeQuery = true)
    List<String> getMostCommentedItemsId(Integer limit);

    @Query(value = "SELECT text_review from reviews;", nativeQuery = true)
    List<String> getReviews();
}
