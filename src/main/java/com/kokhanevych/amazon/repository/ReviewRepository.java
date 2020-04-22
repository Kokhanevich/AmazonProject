package com.kokhanevych.amazon.repository;

import com.kokhanevych.amazon.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
