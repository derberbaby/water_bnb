package com.debbie.waterbnb.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.debbie.waterbnb.models.Pool;
import com.debbie.waterbnb.models.User;
import com.debbie.waterbnb.services.PoolService;
import com.debbie.waterbnb.services.ReviewService;
import com.debbie.waterbnb.services.UserService;
import com.debbie.waterbnb.validators.PoolValidator;

@Controller
public class PoolController {
	private PoolService poolService;
	private UserService userService;
	private ReviewService reviewService;
	private PoolValidator poolValidator;
	
	public PoolController(PoolService poolService, UserService userService, ReviewService reviewService, PoolValidator poolValidator) {
		this.poolService = poolService;
		this.userService = userService;
		this.reviewService = reviewService;
		this.poolValidator = poolValidator;
	}
	
	@PostMapping("/host/pools/new")
	public String newPool(@Valid @ModelAttribute("pool") Pool pool, BindingResult result, Model model, Principal principal) {
		poolValidator.validate(pool, result);
		
		if(result.hasErrors()) {
			String email = principal.getName();
			User currentUser = userService.findByEmail(email);
			
			List<Pool> userPools = poolService.findUserPools(currentUser);
			model.addAttribute("userPools", userPools);
			return "dashboard";
		} else {
			String email = principal.getName();
			User currentUser = userService.findByEmail(email);
			pool.setHost(currentUser);
			poolService.addPool(pool);
			return "redirect:/host/dashboard";
		}
	}
	
	@RequestMapping("/host/pools/{poolId}")
	public String editPool(Model model, @PathVariable("poolId") Long id, Principal principal, @ModelAttribute("pool") Pool pool) {
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
		Pool currentPool = poolService.findPoolById(id);
		List<Object[]> poolReviews = reviewService.findPoolReviews(id);
		
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("pool", currentPool);
		model.addAttribute("poolReviews", poolReviews);
		return "editPool";
	}
	
	@PostMapping("/host/pools/edit")
	public String editPool(Principal principal, @Valid @ModelAttribute("pool") Pool pool, BindingResult result, Model model) {
		poolValidator.validate(pool, result);
		
		if(result.hasErrors()) {
			String email = principal.getName();
			User currentUser = userService.findByEmail(email);
			List<Object[]> poolReviews = reviewService.findPoolReviews(pool.getId());
			
			model.addAttribute("currentUser", currentUser);
			model.addAttribute("pool", pool);
			model.addAttribute("poolReviews", poolReviews);
			return "editPool";
		} else {
			poolService.updatePool(pool);
			return "redirect:/host/pools/" + pool.getId();
		}
	}
	
	@RequestMapping("/search")
	public String findByAddress(Model model, Principal principal) {
		model.addAttribute("principal", principal);
		List<Pool> allPools = poolService.getAllPools();
		model.addAttribute("foundPools", allPools);
		return "search";
	}
	
	@PostMapping("/search")
	public String foundByAddress(Model model, @RequestParam("searchQuery") String search, Principal principal) {
		model.addAttribute("principal", principal);
		List<Pool> foundPools = poolService.findByAddress(search);
		model.addAttribute("foundPools", foundPools);
		return "search";
	}
	
	@RequestMapping("/pools/{id}")
	public String showPool(Model model, @PathVariable("id") Long id, Principal principal) {
		model.addAttribute("principal", principal);
		
		if(principal != null) {
			String email = principal.getName();
			User currentUser = userService.findByEmail(email);
			model.addAttribute("currentUser", currentUser);
		}
		
		Pool pool = poolService.findPoolById(id);
		model.addAttribute("pool", pool);
		List<Object[]> poolReviews = reviewService.findPoolReviews(id);
		model.addAttribute("poolReviews", poolReviews);
		return "showPool";
	}
	
}
