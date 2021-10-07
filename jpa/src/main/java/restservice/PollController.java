package restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PollController {

    @Autowired
    PollUserRepository pollUserRepository;

    @GetMapping("/user")
    public ResponseEntity<List<PollUser>> getAllUsers(@RequestParam(required = false) String username) {
        try {
            List<PollUser> pollUsers = new ArrayList<>();

            if (username == null)
                pollUserRepository.findAll().forEach(pollUsers::add);
            else
                pollUserRepository.findByUsername(username).forEach(pollUsers::add);

            if (pollUsers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pollUsers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}