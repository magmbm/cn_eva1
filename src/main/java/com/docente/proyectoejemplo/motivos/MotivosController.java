package com.docente.proyectoejemplo.motivos;

import com.docente.proyectoejemplo.aws.MotivosLambdaService;
import com.docente.proyectoejemplo.aws.SnsPublisherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/motivos")
public class MotivosController {

    private final SnsPublisherService snSPublisherService;
    private final ObjectMapper objectMapper;
    private MotivosLambdaService motivosLambdaService;

    public MotivosController(SnsPublisherService snSPublisherService, ObjectMapper objectMapper, MotivosLambdaService motivosLambdaService) {
        this.snSPublisherService = snSPublisherService;
        this.objectMapper = objectMapper;
        this.motivosLambdaService= motivosLambdaService;
    }

    @PreAuthorize ("hasAnyRole('Admin', 'Vet')")
    @GetMapping (value="/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> listarMotivos() {
        String respuestaJson= motivosLambdaService.invokeCrud("GET", null, null);
        return ResponseEntity.status(200).body(respuestaJson);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Vet', 'Owner')")
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarMotivo(@RequestBody Map<String, Object> request) throws JsonProcessingException {
        
        String asunto = request.containsKey("asunto") 
                ? request.get("asunto").toString() 
                : "Nuevo Motivo";

        Object datosMotivo = request.containsKey("motivo") ? request.get("motivo") : request;

        String mensajeJson = objectMapper.writeValueAsString(datosMotivo);

        String messageId = snSPublisherService.publicarMensaje(mensajeJson, asunto);
        
        return ResponseEntity.ok(messageId);
    }


}
