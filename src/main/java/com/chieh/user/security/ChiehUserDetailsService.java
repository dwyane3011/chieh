package com.chieh.user.security;

import com.chieh.user.entity.model.UserEntity;
import com.chieh.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userDetailsService")
public class ChiehUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).get();
        ChiehUserDetails chiehUserDetails = new ChiehUserDetails();
        chiehUserDetails.setUserId(userEntity.getId());
        chiehUserDetails.setUsername(username);
        chiehUserDetails.setPassword(userEntity.getPassword());
        return chiehUserDetails;
    }
}
