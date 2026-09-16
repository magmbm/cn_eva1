package com.docente.proyectoejemplo.mascotas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import com.docente.proyectoejemplo.mascotas.entity.Mascota;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PreAuthorize ("hasAnyRole('Admin', 'Vet', 'Owner')")
    @PostMapping("/registrar")
    public ResponseEntity<Mascota> registrarMascota(@RequestBody  Mascota mascota) {
        Mascota nuevo= new Mascota(mascota.getEdad(), mascota.getNombre(), mascota.isVacunado(), mascota.getEspecie());
        mascotaService.registrar(nuevo);
        return ResponseEntity.status(201).body(nuevo);
    }
}
