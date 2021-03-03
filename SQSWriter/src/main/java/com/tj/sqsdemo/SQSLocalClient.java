package com.tj.sqsdemo;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

public class SQSLocalClient {
    private static AmazonSQS client ;
    private static Regions awsRegion = Regions.US_WEST_2;
    private static String endpointOverride = "http://localhost:4566";

    private static AmazonSQS createSqsClient() {
        AmazonSQSClientBuilder clientBuilder = AmazonSQSClientBuilder.standard();
        clientBuilder.withEndpointConfiguration(getEndpointConfiguration(endpointOverride, awsRegion));
        return (AmazonSQSClient)clientBuilder.build();
    }

    private static AwsClientBuilder.EndpointConfiguration getEndpointConfiguration(String endpoint, Regions awsRegion) {
        return new AwsClientBuilder.EndpointConfiguration(endpoint, awsRegion.getName());
    }

    public static AmazonSQS getClient() {
        if (client == null) {
           client = createSqsClient(); 
        }
        return client;
    }
}