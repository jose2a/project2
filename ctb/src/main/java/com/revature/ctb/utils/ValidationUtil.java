package com.revature.ctb.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.revature.ctb.exceptions.BadRequestException;

public class ValidationUtil {

	/**
	 * Check the model for validation errors. Errors can be stored in a list to show
	 * them to the user afterwards
	 * 
	 * @param theBindingResult The Binding Result
	 */
	public static void checkModelForValidationErrors(BindingResult theBindingResult) {
		if (theBindingResult.hasErrors()) {
			BadRequestException badRequestException = new BadRequestException("Validation error");

			System.out.println(theBindingResult);

			for (FieldError error : theBindingResult.getFieldErrors()) {
				badRequestException.addError(error.getDefaultMessage());
			}

			throw badRequestException;
		}
	}

}
