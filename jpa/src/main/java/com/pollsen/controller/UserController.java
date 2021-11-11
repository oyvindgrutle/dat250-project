package com.pollsen.controller;

import com.pollsen.DTO.PollUserDTO;
import com.pollsen.domain.PollUser;
import com.pollsen.service.UserService;
import com.pollsen.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("users")
    public ResponseEntity<List<PollUserDTO>> getAllUsers(@RequestParam(required = false) String username) {
        List<PollUserDTO> pollUsers;

        try {
            if (username == null)
                pollUsers = userService.getUsers();
            else
                pollUsers = userService.getUsers(username);

            if (pollUsers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pollUsers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity<PollUserDTO> getPollUserById(@PathVariable Long id) {
        PollUserDTO pollUser = userService.getUserDTOById(id);

        if (!pollUser.equals(null)) {
            return new ResponseEntity<>(pollUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<PollUser> updatePollUser(@RequestBody PollUser newPollUser, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id)
                    .map(pollUser -> {
                        pollUser.setUsername(newPollUser.getUsername());
                        pollUser.setName(newPollUser.getName());
                        pollUser.setAdmin(newPollUser.isAdmin());

                        return userService.add(pollUser);
                    })
                    .orElseGet(() -> {
                        newPollUser.setId(id);
                        return userService.add(newPollUser);
                    }), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<PollUser> createPollUser(@RequestBody PollUser newPollUser) {
        try {
            PollUser pollUser = userService
                    .add(newPollUser);
            return new ResponseEntity<>(pollUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deletePollUser(@PathVariable Long id) {
        try {
            //PollUserDAO.deleteUser(pollUserRepository.findById(id).get());
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllPollUsers() {
        try {
            userService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
