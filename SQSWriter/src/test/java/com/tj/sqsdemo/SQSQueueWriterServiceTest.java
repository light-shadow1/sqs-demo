package com.tj.sqsdemo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;

import com.amazonaws.services.sqs.AmazonSQS;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import cloud.localstack.TestUtils;
import cloud.localstack.docker.LocalstackDockerExtension;
import cloud.localstack.docker.annotation.LocalstackDockerProperties;

@ExtendWith(LocalstackDockerExtension.class)
@LocalstackDockerProperties(services = { "sqs" })
public class SQSQueueWriterServiceTest {

    @Test
    public void testSQSAPI() {
        AmazonSQS sqs = TestUtils.getClientSQS();
        List<String> queue = sqs.listQueues().getQueueUrls();
        assertTrue( queue.size() > 0 , "The Queue Exists in SQS");
      }

    
}
