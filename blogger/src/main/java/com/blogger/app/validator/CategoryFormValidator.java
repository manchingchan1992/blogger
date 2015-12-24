package com.blogger.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.blogger.app.entity.Category;

@Component
public class CategoryFormValidator implements Validator {

	
	@Override
	public boolean supports(Class<?> clazz) {
		return Category.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Category category = (Category) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.categoryForm.name");

	}

}