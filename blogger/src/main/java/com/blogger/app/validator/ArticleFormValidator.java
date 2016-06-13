package com.blogger.app.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.blogger.app.entity.Article;


@Component
public class ArticleFormValidator implements Validator {

	
	@Override
	public boolean supports(Class<?> clazz) {
		return Article.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Article category = (Article) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.categoryForm.name");

	}

}