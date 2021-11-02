package com.pollsen.controller;

import com.pollsen.domain.Answer;
import com.pollsen.repository.AnswerRepository;
import com.pollsen.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @GetMapping("/answers")
    public ResponseEntity<List<Answer>> getAllAnswers() {
        List<Answer> answers;

        try {
            answers = answerService.getAnswers();

            if (answers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(answers, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/answers/{id}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable Long id) {
        Optional<Answer> answerData = answerService.getAnswerById(id);

        if (answerData.isPresent()) {
            return new ResponseEntity<>(answerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/answers")
    public ResponseEntity<Answer> createAnswer(@RequestBody Answer newAnswer) {
        try {
            Answer answer = answerService
                    .add(newAnswer);
            return new ResponseEntity<>(answer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/answers/{id}")
    public ResponseEntity<HttpStatus> deleteAnswer(@PathVariable Long id) {
        try {
            answerService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/answers")
    public ResponseEntity<HttpStatus> deleteAllAnswers() {
        try {
            answerService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
