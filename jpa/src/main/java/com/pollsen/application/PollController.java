package com.pollsen.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pollsen.domain.Poll;
import com.pollsen.domain.PollUser;
import com.pollsen.repository.PollUserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PollController {

    @Autowired
    PollUserRepository pollUserRepository;

    @GetMapping("/users")
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
                        return pollUserRepository.save(pollUser);
                    })
                    .orElseGet(() -> {
                        newPollUser.setId(id);
                        return pollUserRepository.save(newPollUser);
                    }), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/users")
    public ResponseEntity<PollUser> createTutorial(@RequestBody PollUser pollUser) {
        try {
            PollUser _pollUser = pollUserRepository
                    .save(pollUser);
            return new ResponseEntity<>(_pollUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deletePollUser(@PathVariable Long id) {
        try {
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}