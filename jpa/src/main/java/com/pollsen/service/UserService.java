package com.pollsen.service;

import com.pollsen.DTO.PollDTO;
import com.pollsen.DTO.PollUserDTO;
import com.pollsen.domain.Poll;
import com.pollsen.domain.PollUser;
import com.pollsen.repository.PollUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    PollUserRepository pollUserRepository;
    @Autowired
    PollService pollService;

    public PollUser add(PollUser pollUser) {
        pollUser.setPassword(passwordEncoder.encode(pollUser.getPassword()));
        return pollUserRepository.save(pollUser);
    }

    public void delete(long id) {
        pollUserRepository.deleteById(id);
    }

    public void deleteAll() {
        pollUserRepository.deleteAll();
    }

    public List<PollUserDTO> getUsers() {
        List<PollUserDTO> pollUserList = new ArrayList<>();

        pollUserRepository.findAll().forEach(pollUser -> {
            List<Long> pollIdList = new ArrayList<>();
            pollService.getPollsByUserId(pollUser.getId()).forEach(poll -> {
                pollIdList.add(poll.getId());
            });
            pollUserList.add(new PollUserDTO(pollUser.getId(), pollUser.getUsername(), pollUser.getPassword(), pollUser.getName(), pollUser.isAdmin(), pollIdList));
        });

        return pollUserList;
    }

    public List<PollUserDTO> getUsers(String username) {
        List<PollUserDTO> pollUserList = new ArrayList<>();

        pollUserRepository.findByUsername(username).forEach(pollUser -> {
            List<Long> pollIdList = new ArrayList<>();
            pollService.getPollsByUserId(pollUser.getId()).forEach(poll -> {
                pollIdList.add(poll.getId());
            });
            pollUserList.add(new PollUserDTO(pollUser.getId(), pollUser.getUsername(), pollUser.getPassword(), pollUser.getName(), pollUser.isAdmin(), pollIdList));
        });

        return pollUserList;
    }

    public PollUserDTO getUserDTOById(long id) {
        if (pollUserRepository.findById(id).isPresent()){
            PollUser pollUser = pollUserRepository.findById(id).get();
            List<Long> pollIdList = new ArrayList<>();
            pollService.getPollsByUserId(pollUser.getId()).forEach(poll -> {
                pollIdList.add(poll.getId());
            });
            return new PollUserDTO(pollUser.getId(), pollUser.getUsername(), pollUser.getPassword(), pollUser.getName(), pollUser.isAdmin(), pollIdList);
        }else{
            return null;
        }
    }

    public Optional<PollUser> getUserById(long id) {
        Optional<PollUser> optionalPollUser = pollUserRepository.findById(id);
        return optionalPollUser;
    }

}
