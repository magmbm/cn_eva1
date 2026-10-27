package com.docente.proyectoejemplo.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Service
public class SnsPublisherService {

    private final SnsClient snsClient;

    @Value("${aws.sns.topic.arn}")
    private String topicArn;

    public SnsPublisherService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    /**
     * Publica un mensaje de texto o JSON en un tópico de SNS
     */
    public String publicarMensaje(String mensaje, String asunto) {
        PublishRequest request = PublishRequest.builder()
                .topicArn(topicArn)
                .message(mensaje)
                .subject(asunto)
                .build();

        PublishResponse response = snsClient.publish(request);

        // Devuelve el MessageId generado por AWS
        return response.messageId();
    }
}