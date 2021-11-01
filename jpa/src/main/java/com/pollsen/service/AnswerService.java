package com.pollsen.service;


import com.pollsen.domain.Answer;
import com.pollsen.domain.PollUser;
import com.pollsen.repository.AnswerRepository;
import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;

    public Answer add(Answer answer) {
        return answerRepository.save(answer);
    }

    public void delete(long id) {
        answerRepository.deleteById(id);
    }

    public void deleteAll() {
        answerRepository.deleteAll();
    }

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }

    public Optional<Answer> getAnswerById(long id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer;
    }

}
