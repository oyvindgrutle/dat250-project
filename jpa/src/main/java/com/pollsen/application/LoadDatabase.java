package com.pollsen.application;

import com.pollsen.domain.*;
import com.pollsen.repository.AnswerRepository;
import com.pollsen.repository.PollRepository;
import com.pollsen.repository.PollUserRepository;
import com.pollsen.service.PollService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class LoadDatabase {

    @Autowired
    PollService pollService;

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PollUserRepository repository, PollRepository pollRepository,
                                   AnswerRepository answerRepository) {

        return args -> {
            //log.info("Preloading all existing users " + repository.saveAll(PollUserDAO.getAll()));
            //log.info("Preloading all existing polls " + pollRepository.saveAll(PollDAO.getAll()));
            PollUser user1 = new PollUser("oyvind", "Oyvind Grutle", false);
            Poll poll1 = new Poll("test poll", false, 00000, user1);
            log.info("Preloading " + repository.save(user1));
            log.info("Preloading " + pollRepository.save(poll1));
            log.info("Preloading " + answerRepository.save(new Answer(true, poll1)));

        };
    }


}