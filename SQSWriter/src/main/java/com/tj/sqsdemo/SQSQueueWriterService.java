package com.tj.sqsdemo;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SQSQueueWriterService {
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value="/message", method= RequestMethod.GET, produces= "application/json")
    public MessageObj sendMessage(@RequestParam(value = "message", defaultValue = "Default Message") String msgString ) {
        System.out.println(msgString); // TODO: SetUp logging
        return new MessageObj(counter.incrementAndGet(), msgString);
    }

}