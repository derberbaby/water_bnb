package com.debbie.waterbnb.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.debbie.waterbnb.models.Pool;
import com.debbie.waterbnb.models.User;
import com.debbie.waterbnb.services.PoolService;
import com.debbie.waterbnb.services.UserService;
import com.debbie.waterbnb.validators.UserValidator;

@Controller
public class UserController {
	private UserService userService;
	private UserValidator userValidator;
	private PoolService poolService;
	
	public UserController(UserService userService, UserValidator userValidator, PoolService poolService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.poolService = poolService;
	}
	
	@RequestMapping("/landing")
	public String index() {
		return "landing";
	}
	
	@RequestMapping("/guest/signin")
	public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model, @ModelAttribute("userObject") User user) {
		if(error != null) {
			model.addAttribute("errorMessage", "Invalid Credentials, please try again.");
		}
		if(logout != null) {
			model.addAttribute("logoutMessage", "Logout Successful!");
		}
		return "login";
	}
	
	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute("userObject") User user, BindingResult result) {
		userValidator.validate(user, result);
		
		if(result.hasErrors()) {
			return "login";
		} else {
			if(user.getRole().equals("host")) {
				userService.saveUserWithHostRole(user);
			} else if(user.getRole().equals("guest")) {
				userService.saveUserWithGuestRole(user);
			}
			return "redirect:/reroute";
		}
	}
	
	@RequestMapping(value= {"/", "/reroute"})
	public String success(Principal principal) {
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
		
		
		if(currentUser.getRole().equals("host")) {
			return "redirect:/host/dashboard";
		} else if(currentUser.getRole().equals("guest")) {
			return "redirect:/search";
		} else {
			return "redirect:/landing";
		}
	}
	
	@RequestMapping("/host/dashboard")
	public String host(@Valid @ModelAttribute("pool") Pool pool, BindingResult result, Model model, Principal principal) {
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
		
		List<Pool> userPools = poolService.findUserPools(currentUser);
		model.addAttribute("userPools", userPools);
		return "dashboard";
	}
	
}
