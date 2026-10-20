package com.docente.proyectoejemplo.lambda;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.nio.charset.StandardCharsets;

@Service
public class MascotaLambdaService {

    private final LambdaClient lambdaClient;

    @Value("${aws.lambda.function-name}")
    private String functionName;

    public MascotaLambdaService(LambdaClient lambdaClient) {
        this.lambdaClient = lambdaClient;
    }

    public String obtenerMascotasDesdeLambda(String idMascota) {
        // Construye el objeto JSON 'event' que tu manipulador TypeScript (handler) espera recibir
        String payloadJson;
        if (idMascota != null && !idMascota.trim().isEmpty()) {
            payloadJson = String.format("{\"method\": \"GET\", \"headers\": {\"id_mascota\": \"%s\"}}", idMascota);
        } else {
            payloadJson = "{\"method\": \"GET\", \"headers\": {}}";
        }

        InvokeRequest request = InvokeRequest.builder()
                .functionName(functionName)
                .payload(SdkBytes.fromUtf8String(payloadJson))
                .build();

        InvokeResponse response = lambdaClient.invoke(request);

        // Retorna la cadena de texto JSON generada como respuesta por la Lambda
        return response.payload().asString(StandardCharsets.UTF_8);
    }
}