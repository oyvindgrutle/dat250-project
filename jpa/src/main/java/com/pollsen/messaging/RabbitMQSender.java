package com.pollsen.messaging;

import com.pollsen.domain.Poll;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("exchange")
    private String exchange;

    @Value("routing")
    private String routingkey;

    public void send(Poll poll){
        rabbitTemplate.convertAndSend(exchange, routingkey, poll);
        System.out.println("Send message " + poll);
    }
}
