package com.pollsen.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pollsen.domain.Poll;
import com.pollsen.domain.PollUser;
import com.pollsen.repository.PollUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PollController {

    @Autowired
    PollUserRepository pollUserRepository;

    @GetMapping("/user")
    public ResponseEntity<List<PollUser>> getAllUsers(@RequestParam(required = false) String username) {
        List<PollUser> pollUsers = new ArrayList<>();

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

    @GetMapping("/user/{id}")
    public ResponseEntity<PollUser> getPollUserById(@PathVariable("id") long id) {
        Optional<PollUser> pollUserData = pollUserRepository.findById(id);

        if (pollUserData.isPresent()) {
            return new ResponseEntity<>(pollUserData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<PollUser> createTutorial(@RequestBody PollUser pollUser) {
        try {
            PollUser _pollUser = pollUserRepository
                    .save(pollUser);
            return new ResponseEntity<>(_pollUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}