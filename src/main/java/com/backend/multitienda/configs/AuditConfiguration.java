package com.backend.multitienda.configs;

import com.backend.multitienda.audit.AuditorAwareImpl;
import com.backend.multitienda.audit.DateTimeProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "auditingDateTimeProvider")
public class AuditConfiguration {
  @Bean
  public AuditorAware<String> auditorAware(){
    return new AuditorAwareImpl();
  }

  @Bean(name = "auditingDateTimeProvider")
  public DateTimeProvider dateTimeProvider() {
    return new DateTimeProviderImpl();
  }
}
