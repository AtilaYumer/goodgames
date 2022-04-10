package com.konak.goodgames.service.impl;

import com.konak.goodgames.domain.model.CustomUserDetails;
import com.konak.goodgames.domain.model.User;
import com.konak.goodgames.repository.UserRepository;
import com.konak.goodgames.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

  private final UserRepository userRepository;

  @Override
  public CustomUserDetails loadUserByUsername(String username) {
    Optional<User> user = userRepository.findByEmail(username);
    return user.map(CustomUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found."));
  }
}
