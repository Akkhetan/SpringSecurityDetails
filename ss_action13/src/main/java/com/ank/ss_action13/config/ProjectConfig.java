package com.ank.ss_action13.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .roles("ADMIN")
                .build();

        var user2 = User.withUsername("bill")
                .password("12345")
                .roles("MANAGER")
                .build();

        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests(
            c -> c
                    .requestMatchers(HttpMethod.GET, "/a").authenticated() //For path /a requests called with an HTTP GET method, the app needs to authenticate the user.
                    .requestMatchers(HttpMethod.POST, "/a").permitAll()  //Permits path /a requests to be called with an HTTP POST method for anyone
                    .requestMatchers("/a/b/c/**").authenticated() //The /a/b/c/** expression refers to all paths prefixed with /a/b.
                    .requestMatchers("/product/{code:^[0-9]*$}").permitAll() //We have an endpoint with a path variable, and we want to deny all requests that use a value for the path variable that has anything else other than digits.
                    .anyRequest().denyAll() //Denies any other request to any other path
        );


        http.csrf(
            c -> c.disable()  //Disables CSRF to enable a call to the /a path using the HTTP POST method
        );

        return http.build();
    }
}
