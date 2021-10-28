package com.example.users.security;

import com.example.users.controller.UserRepository;
import com.example.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUserName(userName);
        if (!user.isPresent()){
            System.out.println("Hallå kastas nåt?????");
            throw new UsernameNotFoundException(userName);
        }
        MyUserDetails userDetails = new MyUserDetails(user.get());
        System.out.println("Hur ser det ut?" + userDetails.getUsername());
        return userDetails;
    }
}
