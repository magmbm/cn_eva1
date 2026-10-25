package com.docente.proyectoejemplo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AwsConfig {

    @Value("${aws.region:us-east-1}")
    private String region;

    @Value("${aws.accessKeyId:}")
    private String accessKey;

    @Value("${aws.secretAccessKey:}")
    private String secretKey;

    @Value("${aws.sessionToken:}")
    private String sessionToken;

    @Bean 
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    /**
     * Método auxiliar para resolver las credenciales de forma dinámica
     */
    private AwsCredentialsProvider getCredentialsProvider() {
        if (!accessKey.isEmpty() && !secretKey.isEmpty()) {
            if (!sessionToken.isEmpty()) {
                return StaticCredentialsProvider.create(
                        AwsSessionCredentials.create(accessKey, secretKey, sessionToken)
                );
            }
            return StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(accessKey, secretKey)
            );
        }
        return DefaultCredentialsProvider.create();
    }

    @Bean
    public LambdaClient lambdaClient() {
        return LambdaClient.builder()
                .region(Region.of(region))
                .credentialsProvider(getCredentialsProvider())
                .build();
    }

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(Region.of(region))
                .credentialsProvider(getCredentialsProvider())
                .build();
    }
}