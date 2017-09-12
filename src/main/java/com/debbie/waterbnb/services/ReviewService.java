package com.debbie.waterbnb.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.debbie.waterbnb.models.Review;
import com.debbie.waterbnb.repositories.ReviewRepo;

@Service
public class ReviewService {
	private ReviewRepo reviewRepo;
	
	public ReviewService(ReviewRepo reviewRepo) {
		this.reviewRepo = reviewRepo;
	}
	
	public void addReview(Review review) {
		reviewRepo.save(review);
	}
	
	public List<Object[]> findPoolReviews(Long id) {
		return reviewRepo.findPoolReviews(id);
	}
	
}
