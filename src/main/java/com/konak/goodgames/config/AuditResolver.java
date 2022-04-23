package com.konak.goodgames.config;

import com.konak.goodgames.domain.model.CustomUserDetails;
import com.konak.goodgames.domain.model.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditResolver implements AuditorAware<User> {
  @Override
  public Optional<User> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
    User user = principal.user();
    if (user != null) {
      return Optional.of(user);
    }
    return Optional.empty();
  }
}
