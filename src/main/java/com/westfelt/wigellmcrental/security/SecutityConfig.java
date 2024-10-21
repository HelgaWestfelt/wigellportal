package com.westfelt.wigellmcrental.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecutityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT id, password, active FROM admin WHERE id=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT id, role FROM roles WHERE id=?");
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
                        .requestMatchers(HttpMethod.GET, "/api/v1/mcBookings").hasRole("USER"));
        http.httpBasic((Customizer.withDefaults()));
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }



}
