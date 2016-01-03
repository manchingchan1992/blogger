package com.blogger.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.blogger.app.entity.Blogpost;

@Component
public class BlogpostFormValidator implements Validator {

	
	@Override
	public boolean supports(Class<?> clazz) {
		return Blogpost.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Blogpost category = (Blogpost) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.categoryForm.name");

	}

}