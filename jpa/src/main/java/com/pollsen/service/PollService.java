package com.pollsen.service;

import com.pollsen.DTO.PollDTO;
import com.pollsen.domain.Poll;
import com.pollsen.domain.PollUser;
import com.pollsen.dweet.DweetService;
import com.pollsen.messaging.RabbitMQSender;
import com.pollsen.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PollService {

    @Autowired
    PollRepository pollRepository;
    @Autowired
    UserService userService;
    @Autowired
    AnswerService answerService;
    @Autowired
    DweetService dweetService;


    private final AtomicInteger counter = new AtomicInteger();


    public Poll add(Poll poll) {
        Integer accessCode = Integer.parseInt(String.format("%05d", counter.incrementAndGet()));

        PollUser pollUser = userService.getUserById(poll.getPollUser().getId()).get();
        Poll newPoll = new Poll(poll.getQuestion(), poll.isPublic(), poll.getStartTime(), poll.getEndTime(), accessCode, pollUser);
        dweetService.send(newPoll, true);
        return pollRepository.save(newPoll);
    }

    public void delete(long id) {
        pollRepository.deleteById(id);
    }

    public void deleteAll() {
        pollRepository.deleteAll();
    }

    public List<PollDTO> getPolls() {
        List<PollDTO>  pollList = new ArrayList<>();

        pollRepository.findAll().forEach(poll -> {
            AtomicInteger numYes = new AtomicInteger();
            AtomicInteger numNo = new AtomicInteger();
            answerService.getAnswerByPollId(poll.getId()).forEach(answer -> {
                if (answer.isAnswer()) {
                    numYes.getAndIncrement();
                } else {
                    numNo.getAndIncrement();
                }
            });
            pollList.add(new PollDTO(poll.getId(), poll.getQuestion(), poll.getStartTime(), poll.getEndTime(), numYes.get(), numNo.get()));
        });

        return pollList;
    }

    public List<PollDTO> getPolls(AtomicInteger accessCode) {
        List<PollDTO>  pollList = new ArrayList<>();

        pollRepository.findByAccessCode(accessCode).forEach(poll -> {
            AtomicInteger numYes = new AtomicInteger();
            AtomicInteger numNo = new AtomicInteger();
            answerService.getAnswerByPollId(poll.getId()).forEach(answer -> {
                if (answer.isAnswer()) {
                    numYes.getAndIncrement();
                } else {
                    numNo.getAndIncrement();
                }
            });
            pollList.add(new PollDTO(poll.getId(), poll.getQuestion(), poll.getStartTime(), poll.getEndTime(), numYes.get(), numNo.get()));
        });

        return pollList;
    }


    public PollDTO getPollDTOById(long id) {
        if (pollRepository.findById(id).isPresent()){
            Poll poll = pollRepository.findById(id).get();
            AtomicInteger numYes = new AtomicInteger();
            AtomicInteger numNo = new AtomicInteger();
            answerService.getAnswerByPollId(poll.getId()).forEach(answer -> {
                if (answer.isAnswer()) {
                    numYes.getAndIncrement();
                } else {
                    numNo.getAndIncrement();
                }
            });
            return new PollDTO(poll.getId(), poll.getQuestion(), poll.getStartTime(), poll.getEndTime(), numYes.get(), numNo.get());
        }else{
            return null;
        }
    }

    public List<PollDTO> getPollsByUserId(long id) {
        List<PollDTO>  pollList = new ArrayList<>();

        pollRepository.findByPollUserId(id).forEach(poll -> {
            AtomicInteger numYes = new AtomicInteger();
            AtomicInteger numNo = new AtomicInteger();
            answerService.getAnswerByPollId(poll.getId()).forEach(answer -> {
                if (answer.isAnswer()) {
                    numYes.getAndIncrement();
                } else {
                    numNo.getAndIncrement();
                }
            });
            pollList.add(new PollDTO(poll.getId(), poll.getQuestion(), poll.getStartTime(), poll.getEndTime(), numYes.get(), numNo.get()));
        });

        return pollList;
    }

    public Optional<Poll> getPollById(long id) {
        Optional<Poll> optionalPoll = pollRepository.findById(id);
        return optionalPoll;
    }
}
