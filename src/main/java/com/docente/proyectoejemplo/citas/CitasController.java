package com.docente.proyectoejemplo.citas;

import com.docente.proyectoejemplo.aws.SnsPublisherService;

import org.apache.catalina.connector.Response;
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

    public CitasController(SnsPublisherService snSPublisherService) {
        this.snSPublisherService = snSPublisherService;
    }

    @PreAuthorize ("hasAnyRole('Admin', 'Vet')")
    @PostMapping ("/listar")
    public ResponseEntity<String> listarCitas() {
        return ResponseEntity.status(200).body(null);
    }

    @PreAuthorize("hasAnyRole('Admin', 'Vet', 'Owner')")
    @PostMapping("/registrar")
    public ResponseEntity<String> registrarCita(@RequestBody Map<String, String> request) {
        String mensaje = request.get("mensaje");
        String asunto = request.get("asunto");
        String messageId = snSPublisherService.publicarMensaje(mensaje, asunto);
        return ResponseEntity.ok(messageId);
    }

}
