package com.ank.ss_action10.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var manager = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("john")
                        .password("12345")
                        .authorities("READ")
                        .build();

        var user2 = User.withUsername("jane")
                        .password("12345")
                        .authorities("WRITE")
                        .build();

        var user3 = User.withUsername("ank")
                .password("12345")
                .authorities("DELETE")
                .build();

        manager.createUser(user1);
        manager.createUser(user2);
        manager.createUser(user3);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic(Customizer.withDefaults());

       // httpSecurity.authorizeHttpRequests(c -> c.anyRequest().hasAuthority("WRITE"));
        httpSecurity.authorizeHttpRequests(c -> c.anyRequest().hasAnyAuthority("WRITE","DELETE"));


        return httpSecurity.build();
    }
}
