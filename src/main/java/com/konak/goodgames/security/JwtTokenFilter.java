package com.konak.goodgames.security;

import com.konak.goodgames.service.CustomUserDetailsService;
import com.konak.goodgames.util.JwtTokenUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
  private final JwtTokenUtil jwtTokenUtil;
  private final CustomUserDetailsService customUserDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain)
      throws ServletException, IOException {
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (header == null || !header.startsWith("Bearer ")) {
      chain.doFilter(request, response);
      return;
    }

    final String token = header.split(" ")[1].trim();
    if (!jwtTokenUtil.validate(token)) {
      chain.doFilter(request, response);
      return;
    }

    UserDetails userDetails =
        customUserDetailsService.loadUserByUsername(jwtTokenUtil.getUsername(token));

    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails == null ? List.of() : userDetails.getAuthorities());

    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(request, response);
  }
}
