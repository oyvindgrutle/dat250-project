package com.pollsen.repository;

import com.pollsen.domain.PollUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollUserRepository extends CrudRepository<PollUser, Long> {

    List<PollUser> findByUsername(@Param("name") String username);
}