package com.mtg.security.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.EnableWebSecurity;
import org.springframework.security.config.annotation.web.HttpConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mtg.security.services.support.MtgUserDetailsService;
import com.mtg.security.services.support.Roles;

@Configuration
@EnableWebSecurity
@ComponentScan("com.kemika.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private MtgUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}
	
	@Override
	public void configure(WebSecurityBuilder builder) throws Exception {
		builder
			.ignoring()
				.antMatchers("/css/**","/images/**","/javascript/**","/libs/**");
	}
	
	@Override
	protected void configure(HttpConfiguration http) throws Exception {
		http
			.authorizeUrls()
				.antMatchers("/admin*").hasRole(Roles.ADMIN)
				.antMatchers("/admin/**").hasRole(Roles.ADMIN)
				.antMatchers("/account*").authenticated()
				.antMatchers("/account/**").authenticated()
				.antMatchers("/**").permitAll()
				.and()
			.logout()
				.deleteCookies("JSESSIONID")
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.permitAll()
				.and()
			.formLogin()
				.loginPage("/auth/login")
				.loginProcessingUrl("/login/authenticate")
				.defaultSuccessUrl("/account/dashboard", true)
				.failureUrl("/auth/login/bad_credentials")
				.permitAll();
	}
	
	@Override
	protected void registerAuthentication(AuthenticationManagerBuilder  builder) throws Exception {
		builder.userDetailsService(userDetailsService);
	}
}
