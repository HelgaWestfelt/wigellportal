package com.sandstrom.wigellportal.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
@EnableWebSecurity
@Configuration

public class SecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    public SecurityConfig() {
        logger.info("SecurityConfig instantiated");
    }

    @Autowired
    private DataSource dataSource;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsManager(dataSource)).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT username, password, true FROM cinema_customer WHERE username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT username,role FROM cinema_customer WHERE username=?");

        return jdbcUserDetailsManager;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.POST, "/api/v1/add/bikes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/update/bikes/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/list/bikes").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/customers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/customers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/customers/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/delete/customers/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/delete/bookings/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/bikes").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/book/bikes").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/update/mcBooking{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/mcBookings").hasRole("USER")

                        .requestMatchers(HttpMethod.POST, "/api/v1/cinvenbookings").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cinvenbookings/*").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/cinvenbookings/customer/*").hasRole("USER")

                        .requestMatchers(HttpMethod.GET, "/api/v1/movies").hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/v1/cinema/customers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cinema/customers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/cinema/customers/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cinema/customers/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/movies").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/movies/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/movies/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/v1/cinema/rooms/*").hasRole("ADMIN")

                        .anyRequest().authenticated());
        http.httpBasic((Customizer.withDefaults()));
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }



}
