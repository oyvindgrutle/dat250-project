package com.pollsen.service;

import com.pollsen.domain.Poll;
import com.pollsen.domain.PollUser;
import com.pollsen.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PollService {
    @Autowired
    PollRepository pollRepository;

    public Poll add(Poll poll) {
        return pollRepository.save(poll);
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

    public Optional<Poll> getPollById(long id) {
        Optional<Poll> optionalPoll = pollRepository.findById(id);
        return optionalPoll;
    }
}
