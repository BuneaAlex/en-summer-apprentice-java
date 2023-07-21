package org.restservices;

import org.business.ITicketManagementService;
import org.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    ITicketManagementService service;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = service.findCustomerByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found with email: " + email);
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(customer.getPassword());

        return new org.springframework.security.core.userdetails.User(
                customer.getEmail(),
                encodedPassword,
                getAuthorities() // Replace this with your actual roles/permissions setup if needed
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        // If you need to provide roles/permissions, you can customize this method accordingly.
        // For now, we are returning an empty list.
        return Collections.emptyList();
    }
}
