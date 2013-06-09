package com.mtg.commons.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mtg.commons.models.Message;

/**
 * @author Mark
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.mtg.commons.services.config.CommonsServicesConfig.class})
public class MessageServiceTest {
	
	static Logger log = LoggerFactory.getLogger(MessageServiceTest.class);
	
	@Autowired
	private MessageService service;
	
	@Before
	public void setup() {
		service.deleteAll();
	}
	
	public Message dummy() {
		Message msg = new Message();
		msg.setKey("hello.world");
		msg.setMessage("Hello world!");
		return msg;
	}
	
	@Test
	public void testSave() {
		Message msg = dummy();
		service.save(msg);
	}
	
	@Test
	public void testFindAll() {
		Message msg = dummy();
		service.save(msg);
		
		List<Message> messages = service.findAll();
		assertNotNull(messages);
		assertTrue(messages.size() > 0);
		
		Message first = messages.get(0);
		log.info("First message: {}", first);
	}
	
	@Test
	public void testFindByKey() {
		Message msg = dummy();
		service.save(msg);
		
		Message msg1 = service.findByKey("hello.world");
		assertNotNull(msg1);
		assertEquals("Hello world!", msg1.getMessage());
	}

	@Test 
	public void testUpdateByKey() {
		Message m = dummy();
		
		Message saved = service.save(m);
		assertEquals("Hello world!", saved.getMessage());
		service.update(m.getKey(), "Goodbye, world!");
		
		Message updated = service.findByKey(m.getKey());
		assertNotNull(updated);
		assertEquals("Goodbye, world!", updated.getMessage());
	}
}
