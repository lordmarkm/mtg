package com.mtg.commons.services.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:app.properties")
@ComponentScan("com.mtg.commons.services")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.mtg.commons.services", repositoryImplementationPostfix="CustomImpl")
public class CommonsServicesConfig {

}
