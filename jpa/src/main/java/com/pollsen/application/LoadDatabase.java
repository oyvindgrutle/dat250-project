package com.pollsen.application;

import com.pollsen.domain.Poll;
import com.pollsen.domain.PollDAO;
import com.pollsen.domain.PollUser;
import com.pollsen.domain.PollUserDAO;
import com.pollsen.repository.PollRepository;
import com.pollsen.repository.PollUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PollUserRepository repository, PollRepository pollRepository) {

        return args -> {
            //log.info("Preloading all existing users " + repository.saveAll(PollUserDAO.getAll()));
            //log.info("Preloading all existing polls " + pollRepository.saveAll(PollDAO.getAll()));
            PollUser user1 = new PollUser("oyvind", "Oyvind Grutle", false);
            log.info("Preloading " + repository.save(user1));
            log.info("Preloading " + pollRepository.save(new Poll("test poll", false, user1)));

        };
    }


}