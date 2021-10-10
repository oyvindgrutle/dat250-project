package com.pollsen.repository;

import com.pollsen.domain.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {

    List<Poll> findByPollUserId(@Param("id") Long id);
}