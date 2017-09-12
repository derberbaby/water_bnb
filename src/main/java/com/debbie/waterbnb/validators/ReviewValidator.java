package com.debbie.waterbnb.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.debbie.waterbnb.models.Review;

@Component
public class ReviewValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Review.class.equals(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		Review review = (Review) object;
		
	}
}
