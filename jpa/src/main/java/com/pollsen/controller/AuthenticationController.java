package com.pollsen.controller;

import com.pollsen.DAO.PollUserDAO;
import com.pollsen.domain.PollUser;
import com.pollsen.models.AuthenticationRequest;
import com.pollsen.models.AuthenticationResponse;
import com.pollsen.models.SignUpRequest;
import com.pollsen.security.MyUserDetailsService;
import com.pollsen.service.UserService;
import com.pollsen.util.JwtUtil;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/authenticate/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            System.out.println(authenticationRequest.getUsername());
            System.out.println(authenticationRequest.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("Bad credentials", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        System.out.println(jwt);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @RequestMapping(value = "/authenticate/signup", method = RequestMethod.POST)
    public ResponseEntity<PollUser> SignUp(@RequestBody PollUser newPollUser) {
        try {
            PollUser pollUser = userService
                    .add(newPollUser);
            return new ResponseEntity<>(pollUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
