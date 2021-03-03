package com.tj.sqsdemo;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.Message;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SQSQueueWriterService {
    private final AtomicLong counter = new AtomicLong();
    CreateQueueResult cqr = SQSLocalClient.getClient().createQueue("TEST_QUEUE1");

    @RequestMapping(value="/message", method= RequestMethod.GET, produces= "application/json")
    public MessageObj sendMessage(@RequestParam(value = "message", defaultValue = "Default Message") String msgString ) {
        System.out.println(msgString); // TODO: SetUp logging
        
        SQSLocalClient.getClient().sendMessage(cqr.getQueueUrl(), msgString);
        List<Message> msgs = SQSLocalClient.getClient().receiveMessage(cqr.getQueueUrl()).getMessages();
        // send message to SQS
        for(Message m : msgs ) {
            System.out.println(m.getBody() + m.getMessageId());
            SQSLocalClient.getClient().deleteMessage(cqr.getQueueUrl(), m.getReceiptHandle());
        }
        return new MessageObj(counter.incrementAndGet(), msgString);
    }

}