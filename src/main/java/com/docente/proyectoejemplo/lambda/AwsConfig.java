package com.docente.proyectoejemplo.lambda;

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
import tools.jackson.databind.ObjectMapper;



@Configuration
public class AwsConfig {

    @Bean 
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    };

    @Value("${aws.region:us-east-1}")
    private String region;

    @Value("${aws.accessKeyId:}")
    private String accessKey;

    @Value("${aws.secretAccessKey:}")
    private String secretKey;

    @Value("${aws.sessionToken:}")
    private String sessionToken;

    @Bean
    public LambdaClient lambdaClient() {
        AwsCredentialsProvider credentialsProvider;


        // Si existen claves explícitas (por ejemplo en tu máquina local)
        if (!accessKey.isEmpty() && !secretKey.isEmpty()) {
            if (!sessionToken.isEmpty()) {
                credentialsProvider = StaticCredentialsProvider.create(
                        AwsSessionCredentials.create(accessKey, secretKey, sessionToken)
                );
            } else {
                credentialsProvider = StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)
                );
            }
        } else {
            // Busca automáticamente en IMDS de la EC2 (LabRole) o en ~/.aws/credentials
            credentialsProvider = DefaultCredentialsProvider.create();
        }

        return LambdaClient.builder()
                .region(Region.of(region))
                .credentialsProvider(credentialsProvider)
                .build();
    }

}