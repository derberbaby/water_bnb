package com.debbie.waterbnb.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.debbie.waterbnb.models.Pool;
import com.debbie.waterbnb.models.Review;
import com.debbie.waterbnb.models.User;
import com.debbie.waterbnb.services.PoolService;
import com.debbie.waterbnb.services.ReviewService;
import com.debbie.waterbnb.services.UserService;

@Controller
public class ReviewController {
	private UserService userService;
	private ReviewService reviewService;
	private PoolService poolService;
	
	public ReviewController(UserService userService, ReviewService reviewService, PoolService poolService) {
		this.userService = userService;
		this.reviewService = reviewService;
		this.poolService = poolService;
	}
	
	@RequestMapping("/pools/{id}/review")
	public String newReview(@ModelAttribute("reviewObj") Review review, @PathVariable("id") Long id, Model model) {
		Pool pool = poolService.findPoolById(id);
		model.addAttribute("pool", pool);
		return "newReview";
	}
	
	@PostMapping("/pools/{poolId}/review/add")
	public String addReview(Model model, @PathVariable("poolId") Long id, @Valid @ModelAttribute("reviewObj") Review review, BindingResult result, Principal principal) {
		if(result.hasErrors()) {
			model.addAttribute("review", review);
			return "newReview";
		} else {
			String email = principal.getName();
			User currentUser = userService.findByEmail(email);
			Pool pool = poolService.findPoolById(id);
			review.setPool(pool);
			review.setGuest(currentUser);
			reviewService.addReview(review);
			poolService.updateRatings(id);
			return "redirect:/pools/" + id;
		}
	}
	
}
