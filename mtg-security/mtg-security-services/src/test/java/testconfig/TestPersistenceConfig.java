package testconfig;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mtg.audit.service.AuditLogger;
import com.mtg.mail.service.MailSenderService;

@Configuration
@PropertySource("classpath:db.properties")
@EnableJpaRepositories(basePackages="com.mtg.security.services", repositoryImplementationPostfix="CustomImpl")
@EnableTransactionManagement
public class TestPersistenceConfig {
	
	@Bean
	public MailSenderService mailSenderService() {
		return mock(MailSenderService.class);
	}
	
	@Bean
	public AuditLogger audit() {
		return mock(AuditLogger.class);
	}
	
}
