package com.pollsen.service;

import com.pollsen.domain.PollUser;
import com.pollsen.repository.PollUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    PollUserRepository pollUserRepository;

    public PollUser add(PollUser pollUser) {
        return pollUserRepository.save(pollUser);
    }

    public void delete(long id) {
        pollUserRepository.deleteById(id);
    }

    public void deleteAll() {
        pollUserRepository.deleteAll();
    }

    public List<PollUser> getUsers() {
        return pollUserRepository.findAll();
    }

    public List<PollUser> getUsers(String username) {
        return pollUserRepository.findByUsername(username);
    }

    public Optional<PollUser> getUserById(long id) {
        Optional<PollUser> optionalPollUser = pollUserRepository.findById(id);
        return optionalPollUser;
    }



}
