package com.mtg.web.dto;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ValidatorConfig.class)
public class AccountFormTest {

    private static Logger log = LoggerFactory.getLogger(AccountFormTest.class);
    
    @Resource
    private Validator val;
    
    @Test
    public void testConfig() {
        assertNotNull(val);
    }
    
    private AccountForm valid() {
        AccountForm form = new AccountForm();
        form.setUsername("markm");
        form.setPassword("123qwe");
        form.setConfirmpw("123qwe");
        form.setEmail("lordmarkm@gmail.com");
        return form;
    }
    
    @Test
    public void test() {
        Set<ConstraintViolation<AccountForm>> errors = val.validate(valid(), Default.class);
        assertEquals(0, errors.size());
    }
    
    @Test
    public void malformedEmail() {
        AccountForm form = valid();
        form.setEmail("www.gmail.com");
        Set<ConstraintViolation<AccountForm>> errors = val.validate(form, Default.class);
        assertEquals(1, errors.size());
        
        ConstraintViolation<AccountForm> error = errors.iterator().next();
        assertEquals("email", error.getPropertyPath().toString());
        log.debug("Error={}", error.getMessage());
        
        form.setEmail("m arkm@gmail.com");
        errors = val.validate(form, Default.class);
        assertEquals(1, errors.size());
        
        error = errors.iterator().next();
        assertEquals("email", error.getPropertyPath().toString());
        log.debug("Error={}", error.getMessage());
    }
    
}
