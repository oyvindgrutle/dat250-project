package com.pollsen.controller;

import com.pollsen.domain.Poll;
import com.pollsen.repository.PollRepository;
import com.pollsen.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PollController {

    @Autowired
    PollService pollService;

    @GetMapping("/polls")
    public ResponseEntity<List<Poll>> getAllPolls() {
        List<Poll> polls;

        try {
            polls = pollService.getPolls();

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
        Optional<Poll> pollData = pollService.getPollById(id);

        if (pollData.isPresent()) {
            return new ResponseEntity<>(pollData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/polls/{id}")
    public ResponseEntity<Poll> updatePoll(@RequestBody Poll newPoll, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(pollService.getPollById(id)
                    .map(poll -> {
                        poll.setQuestion(newPoll.getQuestion());
                        poll.setPublic(newPoll.isPublic());
                        poll.setPollUser(newPoll.getPollUser());
                        return pollService.add(poll);
                    })
                    .orElseGet(() -> {
                        newPoll.setId(id);
                        return pollService.add(newPoll);
                    }), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/polls")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll newPoll) {
        try {
            Poll poll = pollService
                    .add(newPoll);
            return new ResponseEntity<>(poll, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/polls/{id}")
    public ResponseEntity<HttpStatus> deletePoll(@PathVariable Long id) {
        try {
            pollService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/polls")
    public ResponseEntity<HttpStatus> deleteAllPolls() {
        try {
            pollService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
