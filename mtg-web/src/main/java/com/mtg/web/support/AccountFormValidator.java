package com.mtg.web.support;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.mtg.web.dto.AccountForm;

@Component
public class AccountFormValidator implements Validator {

	private static Logger log = LoggerFactory.getLogger(AccountFormValidator.class);
	
    @Resource
    private LocalValidatorFactoryBean defaultValidator;
	
	@Override
	public boolean supports(Class<?> clazz) {
        return AccountForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		defaultValidator.validate(target, errors);
		
		AccountForm form = (AccountForm) target;
		if(!form.getPassword().equals(form.getConfirmpw())) {
			errors.reject("password.mismatch", "Password and Confirm password must match");
		}
		
		log.info("After validation: {}", errors);
	}

}
