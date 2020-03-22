package com.hibicode.marketplace.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import io.github.normandesjr.decorator.PrefixKeyDynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint:http://localhost:8000}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.region:us-east-1}")
    private String amazonRegion;

    @Value("${amazon.aws.accesskey:local}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey:local}")
    private String amazonAWSSecretKey;

    @Bean
    public PrefixKeyDynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB) {
        return new PrefixKeyDynamoDBMapper(amazonDynamoDB, new DynamoDBMapper(amazonDynamoDB));
    }

    @Bean("amazonDynamoDB")
    public AmazonDynamoDB amazonDynamoDBLocal() {
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(credentialsProvider())
                .withEndpointConfiguration(endpointConfiguration())
                .build();
    }

    private AWSStaticCredentialsProvider credentialsProvider() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
    }

    private AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, amazonRegion);
    }

}
