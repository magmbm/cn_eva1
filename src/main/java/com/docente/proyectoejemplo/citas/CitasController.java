package com.docente.proyectoejemplo.citas;

import com.docente.proyectoejemplo.aws.SnsPublisherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/citas")
public class CitasController {

    private final SnsPublisherService snSPublisherService;
    private final ObjectMapper objectMapper;

    public CitasController(SnsPublisherService snSPublisherService, ObjectMapper objectMapper) {
        this.snSPublisherService = snSPublisherService;
        this.objectMapper = objectMapper;
    }

    @PreAuthorize ("hasAnyRole('Admin', 'Vet')")
    @PostMapping ("/listar")
    public ResponseEntity<String> listarCitas() {
        return ResponseEntity.status(200).body(null);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Vet', 'Owner')")
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarCita(@RequestBody Map<String, Object> request) throws JsonProcessingException {
        
        // 1. Extraer el asunto (o asignar un valor por defecto)
        String asunto = request.containsKey("asunto") 
                ? request.get("asunto").toString() 
                : "Nueva Cita";

        // 2. Si la petición incluye un objeto 'cita' anidado, lo usamos; si no, usamos el body directo
        Object datosCita = request.containsKey("cita") ? request.get("cita") : request;

        // 3. Serializar el objeto a JSON String preservando números y cadenas
        String mensajeJson = objectMapper.writeValueAsString(datosCita);

        // 4. Publicar en SNS
        String messageId = snSPublisherService.publicarMensaje(mensajeJson, asunto);
        
        return ResponseEntity.ok(messageId);
    }


}
