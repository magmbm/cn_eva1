package com.docente.proyectoejemplo.mascotas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.docente.proyectoejemplo.mascotas.entity.Mascota;

import org.springframework.web.bind.annotation.GetMapping;

@RestController 
@RequestMapping("/api/mascotas")
public class MascotaController {
    private final MascotaService mascotaService; 

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService= mascotaService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Mascota>> getAll() {
        return ResponseEntity.status(200).body( mascotaService.getAll());
    }
}
