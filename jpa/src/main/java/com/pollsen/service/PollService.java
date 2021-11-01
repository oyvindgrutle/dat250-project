package com.pollsen.service;

import com.pollsen.domain.Poll;
import com.pollsen.domain.PollUser;
import com.pollsen.repository.PollRepository;
import com.pollsen.repository.PollUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PollService {
    @Autowired
    PollRepository pollRepository;
    @Autowired
    UserService userService;

    private final AtomicInteger counter = new AtomicInteger();


    public Poll add(Poll poll) {
        Integer accessCode = Integer.parseInt(String.format("%05d", counter.incrementAndGet()));

        PollUser pollUser = userService.getUserById(poll.getPollUser().getId()).get();
        Poll newPoll = new Poll(poll.getQuestion(), poll.isPublic(), accessCode, pollUser);
        return pollRepository.save(newPoll);
    }

    public void delete(long id) {
        pollRepository.deleteById(id);
    }

    public void deleteAll() {
        pollRepository.deleteAll();
    }

    public List<Poll> getPolls() {
        return pollRepository.findAll();
    }

    public List<Poll> getPolls(AtomicInteger accessCode) {
        return pollRepository.findByAccessCode(accessCode);
    }

    public Optional<Poll> getPollById(long id) {
        Optional<Poll> optionalPoll = pollRepository.findById(id);
        return optionalPoll;
    }

}
