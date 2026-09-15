package com.docente.proyectoejemplo.controladores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docente.proyectoejemplo.Entidades.Mascota;
import java.util.List;
import com.docente.proyectoejemplo.servicios.MascotaService;

@RequestMapping("/api/mascotas")
public class Mascotas {
    private final MascotaService mascotaService;

    public Mascotas(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<Mascota>> getMascotas() {
        return ResponseEntity.status(200).body(mascotaService.getAll());
    }
}
