package com.docente.proyectoejemplo.lambda;

import com.docente.proyectoejemplo.mascotas.entity.Mascota;

public class LambdaPayload {
    private String method;
    private Mascota mascota;
    
    public String formatPayload(LambdaPayload payload) {
        String payloadFormat= String.format("{\"method\": %s, \"headers\" : }", payload.method) ;
        return payloadFormat; 
    }
}
