package com.pollsen.repository;

import com.pollsen.domain.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

    List<Poll> findByPollUserId(@Param("id") Long id);

    List<Poll> findByAccessCode(@Param("accessCode") AtomicInteger accessCode);
}