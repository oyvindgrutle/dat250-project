package com.pollsen.repository;

import com.pollsen.domain.Answer;
import com.pollsen.domain.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByPollId(@Param("id") Long id);
}