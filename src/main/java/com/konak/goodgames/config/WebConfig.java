package com.konak.goodgames.config;

import com.konak.goodgames.security.JwtTokenFilter;
import com.konak.goodgames.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfig extends WebSecurityConfigurerAdapter {

  private final JwtTokenFilter jwtTokenFilter;

  private final CustomUserDetailsService customUserDetailsService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http = http.csrf().disable();

    http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

    http =
        http.exceptionHandling()
            .authenticationEntryPoint(
                ((request, response, authException) ->
                    response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage())))
            .and();

    http.authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/**")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/application/test")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/users")
        .permitAll()
        .anyRequest()
        .authenticated();

    http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CorsFilter corsFilter() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.setAllowedOriginPatterns(List.of("*"));
    config.setAllowedHeaders(
        Arrays.asList("Origin", "Content-Type", "Accept", "responseType", "Authorization"));
    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
