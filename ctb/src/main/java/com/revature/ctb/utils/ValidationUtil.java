package com.revature.ctb.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.revature.ctb.exceptions.BadRequestException;

public class ValidationUtil {

	/**
	 * Check the model for validation errors. Errors can be stored in a list to show
	 * them to the user afterwards in Angular
	 * 
	 * @param theBindingResult The Binding Result
	 * @throws BadRequestException 
	 */
	public static void checkModelForValidationErrors(BindingResult theBindingResult) throws BadRequestException {
		if (theBindingResult.hasErrors()) {
			BadRequestException badRequestException = new BadRequestException("Input validation errors");

			for (FieldError error : theBindingResult.getFieldErrors()) {
				badRequestException.addError(error.getDefaultMessage()); // putting the error in the list
			}

			throw badRequestException;
		}
	}

}
