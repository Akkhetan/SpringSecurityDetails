package com.ank.ss_action1.config;

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
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults()); //Use HTTP Basic authentication approach

        http.authorizeHttpRequests(
            c -> c.anyRequest().authenticated()
        );  // configure the authorization rules at the endpoint level. Here all the requests require authentication.

        return http.build();

        /*
        For both methods, you had to use a Customizer object as a parameter. Customizer is a contract you implement to define the customization for either
        Spring Security element you configure: the authentication, the authorization, or particular protection mechanisms such as CSRF or CORS
         */
    }

    @Bean
    UserDetailsService userDetailsService() {
        var user = User.withUsername("john")
                .password("12345")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
