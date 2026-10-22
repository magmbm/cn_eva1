package com.docente.proyectoejemplo.lambda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class MascotaLambdaService {

    private final LambdaClient lambdaClient;
    private final ObjectMapper objectMapper;

    @Value("${aws.lambda.function-name}")
    private String functionName;

    public MascotaLambdaService(LambdaClient lambdaClient, ObjectMapper objectMapper) {
        this.lambdaClient = lambdaClient;
        this.objectMapper = objectMapper;
    }

    public String obtenerMascotasDesdeLambda(String idMascota) {
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

        return response.payload().asString(StandardCharsets.UTF_8);
    }

    public String crudMascotasLambda(String idMascota) {
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

        return response.payload().asString(StandardCharsets.UTF_8);
    }

    /**
     * Invoca la función Lambda enviando toda la información de la petición HTTP.
     * Puedes adaptar las claves (method/httpMethod, body, pathParameters, headers, queryStringParameters)
     * según lo que espera tu Lambda.
     */
    public String invokeCrud(String httpMethod,
                            Map<String, String> pathParameters,
                            Map<String, String> headers,
                            Map<String, String> queryStringParameters,
                            Object body) {
        Map<String, Object> payload = new HashMap<>();
        if (httpMethod != null) {
            payload.put("method", httpMethod);
        }
        payload.put("headers", headers != null ? headers : Collections.emptyMap());
        payload.put("pathParameters", pathParameters != null ? pathParameters : Collections.emptyMap());
        payload.put("queryStringParameters", queryStringParameters != null ? queryStringParameters : Collections.emptyMap());

        if (body != null) {
            // Si ya es String, enviarlo tal cual; si es objeto, serializarlo
            if (body instanceof String) {
                payload.put("body", body);
            } else {
                try {
                    payload.put("body", objectMapper.writeValueAsString(body));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Error serializando body para Lambda", e);
                }
            }
        }

        String payloadJson;
        try {
            payloadJson = objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializando payload para Lambda", e);
        }

        InvokeRequest request = InvokeRequest.builder()
                .functionName(functionName)
                .payload(SdkBytes.fromUtf8String(payloadJson))
                .build();

        InvokeResponse response = lambdaClient.invoke(request);
        return response.payload().asString(StandardCharsets.UTF_8);
    }

    public String invokeCrud(String httpMethod, Map<String, String> headers, Object body) {
        return invokeCrud(httpMethod, Collections.emptyMap(), headers, Collections.emptyMap(), body);
    }
}