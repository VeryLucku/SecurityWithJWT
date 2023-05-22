package com.edu.security.SecConfig;

import com.edu.security.Models.Entities.CustomUserDetails;
import com.edu.security.Repository.UserRepository;
import com.edu.security.Security.AppJwtHandler;
import com.edu.security.Security.BearerTokenServerAuthenticationConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${jwt.secret}")
    private String secret;

    private final String [] publicRoutes = {"/user/register", "/user/login"};

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(publicRoutes)
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .exceptionHandling((expHandling) ->
                        expHandling
                                .authenticationEntryPoint((req, res, e) -> {
                                    log.error("Unauthorized error in defaultSecurityFilterChain, error: {} {}", e.getMessage(), e.getClass());
                                    res.sendError(401, "Person isn't authorized");
                                })
                                .accessDeniedHandler((req, res, e) -> {
                                    log.error("Access denied in defaultSecurityFilterChain, error: {}", e.getMessage());
                                    res.sendError(403, "Access denied");
                                }))
                //.httpBasic((e) -> {})
                .addFilterBefore(bearerAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private AuthenticationFilter bearerAuthenticationFilter(AuthenticationManager authenticationManager) {

        return new AuthenticationFilter(authenticationManager,
                new BearerTokenServerAuthenticationConverter(new AppJwtHandler(secret)));
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> new CustomUserDetails(userRepository
                                                    .findByUsername(username)
                                                    .orElseThrow(() ->
                                                            new UsernameNotFoundException("Specified user doesn't exist")));
    }
}
