package com.pollsen.controller;

import com.pollsen.domain.PollUser;
import com.pollsen.domain.PollUserDAO;
import com.pollsen.repository.PollUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    PollUserRepository pollUserRepository;

    @GetMapping("users")
    public ResponseEntity<List<PollUser>> getAllUsers(@RequestParam(required = false) String username) {
        List<PollUser> pollUsers;

        try {
            if (username == null)
                pollUsers = (List<PollUser>) pollUserRepository.findAll();
            else
                pollUsers = pollUserRepository.findByUsername(username);

            if (pollUsers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pollUsers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<PollUser> getPollUserById(@PathVariable Long id) {
        Optional<PollUser> pollUserData = pollUserRepository.findById(id);

        if (pollUserData.isPresent()) {
            return new ResponseEntity<>(pollUserData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<PollUser> updatePollUser(@RequestBody PollUser newPollUser, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(pollUserRepository.findById(id)
                    .map(pollUser -> {
                        pollUser.setUsername(newPollUser.getUsername());
                        pollUser.setName(newPollUser.getName());
                        pollUser.setAdmin(newPollUser.isAdmin());
                        //PollUserDAO.updateUser(pollUser);
                        return pollUserRepository.save(pollUser);
                    })
                    .orElseGet(() -> {
                        newPollUser.setId(id);
                        //PollUserDAO.insertUser(newPollUser);
                        return pollUserRepository.save(newPollUser);
                    }), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<PollUser> createPollUser(@RequestBody PollUser newPollUser) {
        try {
            PollUser pollUser = pollUserRepository
                    .save(newPollUser);
            PollUserDAO.insertUser(newPollUser);
            return new ResponseEntity<>(pollUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deletePollUser(@PathVariable Long id) {
        try {
            //PollUserDAO.deleteUser(pollUserRepository.findById(id).get());
            pollUserRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllPollUsers() {
        try {
            pollUserRepository.deleteAll();
            //PollUserDAO.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}