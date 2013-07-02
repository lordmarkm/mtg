package com.mtg.interactive.posts.services.config;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mtg.commons.models.interactive.Frontpage;
import com.mtg.interactive.posts.services.FrontpageService;

@Configuration
@ComponentScan("com.mtg.interactive.posts.services")
@EnableJpaRepositories(basePackages="com.mtg.interactive.posts.services", repositoryImplementationPostfix="CustomImpl")
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class PostsServicesConfig {

	@Resource
	private FrontpageService frontpageService;

	@PostConstruct
	public void ensureFrontpage() {
		Frontpage fp = frontpageService.getFrontpage();
		if(null == fp) {
			fp = new Frontpage();
			frontpageService.save(fp);
		}
	}

}
