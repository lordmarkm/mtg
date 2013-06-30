package com.mtg.interactive.posts.services;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mtg.commons.services.config.CommonsPersistenceConfig;
import com.mtg.interactive.posts.services.config.PostsServicesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsPersistenceConfig.class, PostsServicesConfig.class, PostsServicesTestConfig.class})
public class PostServiceTest {

	@Resource
	private PostService service;
	
	@Test
	public void config() {
		
	}
}
