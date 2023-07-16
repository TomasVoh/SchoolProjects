package com.example.schoolProjects.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/webjars/**").permitAll();
                    auth.requestMatchers("/home", "/login").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}
