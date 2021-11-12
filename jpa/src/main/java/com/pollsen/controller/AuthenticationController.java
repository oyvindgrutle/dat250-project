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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            String username = authenticationRequest.getUsername();
            String password = authenticationRequest.getPassword();
            String hashedPassword = passwordEncoder.encode(password);
            System.out.println("Password: " + password + "\n Hashed password: " + hashedPassword);
            System.out.println(authenticationRequest.getUsername());
            System.out.println(authenticationRequest.getPassword());
            //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, hashedPassword));


            try {
                if (passwordEncoder.matches(password, userDetailsService.loadUserByUsername(username).getPassword())) {
                    System.out.println("Password matches");
                } else throw new BadCredentialsException("Username and password don't match");
            } catch (Exception e) {
                return new ResponseEntity<>("{\"status\": 401}", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception("Bad credentials", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        System.out.println(jwt);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping(value = "/signup")
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
