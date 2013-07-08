package com.mtg.interactive.chat.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.mtg.interactive.chat.services")
@EnableJpaRepositories(basePackages="com.mtg.interactive.chat.services", repositoryImplementationPostfix="CustomImpl")
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class ChatServicesConfig {

}
