package com.konak.goodgames.config;

import com.konak.goodgames.domain.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditingConfig {

  @Bean
  AuditorAware<User> auditorProvider() {
    return new AuditResolver();
  }
}
