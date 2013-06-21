package com.mtg.mail.config;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mtg.mail.service.MailSenderService;

import freemarker.template.Configuration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MailConfiguration.class})
public class ConfigTest {

    private static Logger log = LoggerFactory.getLogger(ConfigTest.class);
    
    @Resource
    private MailSenderService sender;
    
    @Resource
    private MailSender springMailSender;
    
    @Resource
    private Configuration freemarkerConfig;
    
    @Test
    public void testConfigLoaded() {
        assertNotNull(sender);
        assertNotNull(springMailSender);
        assertNotNull(freemarkerConfig);
    }
    
    @Test
    public void testRelativePath() throws IOException {
    }
}
