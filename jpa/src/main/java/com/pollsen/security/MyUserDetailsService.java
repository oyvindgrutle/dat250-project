package com.pollsen.security;

import com.pollsen.DTO.PollUserDTO;
import com.pollsen.domain.Poll;
import com.pollsen.domain.PollUser;
import com.pollsen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            PollUserDTO user = userService.getUserDTOByUsername(username);
            String password = user.getPassword();
            System.out.println(user.getUsername());
            return new User(username, password, new ArrayList<>());

        }catch (NullPointerException e) {
            throw new UsernameNotFoundException("Invalid username");
        }
    }
}
