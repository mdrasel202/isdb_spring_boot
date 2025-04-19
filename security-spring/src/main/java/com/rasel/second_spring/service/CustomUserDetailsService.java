package com.rasel.second_spring.service;


import com.rasel.second_spring.model.CustomUser;
import com.rasel.second_spring.model.CustomUserDetails;
import com.rasel.second_spring.repository.CustomUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserRepository userRepository;

    public CustomUserDetailsService(CustomUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = this.userRepository.findCustomUserByEmail(username);
        if (customUser == null) {
            throw new UsernameNotFoundException("username " + username + " is not found");
        }
        return new CustomUserDetails(customUser);
    }

}