package com.debbie.waterbnb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.debbie.waterbnb.models.Pool;
import com.debbie.waterbnb.models.Review;

@Repository
public interface ReviewRepo extends CrudRepository<Review, Long> {

	@Query("SELECT g.firstName, g.lastName, r.rating, r.review FROM Review r JOIN r.guest g WHERE r.pool.id = ?1 ORDER BY r.createdAt DESC")
	List<Object[]> findPoolReviews(Long id);
	
}
