package com.pollsen.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pollsen.domain.Poll;
import com.pollsen.domain.PollUser;
import com.pollsen.domain.PollUserDAO;
import com.pollsen.repository.PollRepository;
import com.pollsen.repository.PollUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
public class PollController {

    @Autowired
    PollUserRepository pollUserRepository;
    @Autowired
    PollRepository pollRepository;

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
            //PollUserDAO.insertUser(newPollUser);
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



    @GetMapping("/polls")
    public ResponseEntity<List<Poll>> getAllPolls() {
        List<Poll> polls;

        try {
            polls = pollRepository.findAll();

            if (polls.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(polls, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/polls/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable Long id) {
        Optional<Poll> pollData = pollRepository.findById(id);

        if (pollData.isPresent()) {
            return new ResponseEntity<>(pollData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/polls/{id}")
    public ResponseEntity<Poll> updatePoll(@RequestBody Poll newPoll, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(pollRepository.findById(id)
                    .map(poll -> {
                        poll.setQuestion(newPoll.getQuestion());
                        poll.setPublic(newPoll.isPublic());
                        poll.setPollUser(newPoll.getPollUser());
                        return pollRepository.save(poll);
                    })
                    .orElseGet(() -> {
                        newPoll.setId(id);
                        //PollUserDAO.insertUser(newPollUser);
                        return pollRepository.save(newPoll);
                    }), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/polls")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll newPoll) {
        try {
            Poll poll = pollRepository
                    .save(newPoll);
            return new ResponseEntity<>(poll, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/polls/{id}")
    public ResponseEntity<HttpStatus> deletePoll(@PathVariable Long id) {
        try {
            pollRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/polls")
    public ResponseEntity<HttpStatus> deleteAllPolls() {
        try {
            pollRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}