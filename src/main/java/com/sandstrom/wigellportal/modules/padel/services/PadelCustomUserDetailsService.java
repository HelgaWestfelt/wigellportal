package com.sandstrom.wigellportal.modules.padel.services;

import com.sandstrom.wigellportal.customer.Customer;
import com.sandstrom.wigellportal.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class PadelCustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public PadelCustomUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Användare med användarnamn " + username + " hittades inte"));

        // Skapa en authority baserat på kundens roll (prefix "ROLE_" krävs av Spring Security)
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + customer.getRole().name());

        // Skapa och returnera ett User-objekt från Spring Security som använder rätt användarnamn, lösenord och roll
        return new User(
                customer.getUsername(),
                customer.getPassword(),
                Collections.singletonList(authority)
        );
    }
}
