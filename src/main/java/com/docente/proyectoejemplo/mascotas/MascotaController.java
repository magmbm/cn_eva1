package com.docente.proyectoejemplo.mascotas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import com.docente.proyectoejemplo.mascotas.entity.Mascota;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController 
@RequestMapping("/api/mascotas")
public class MascotaController {
    private final MascotaService mascotaService; 

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService= mascotaService;
    }

    @PreAuthorize ("hasAnyRole('Admin', 'Vet')")
    @GetMapping("/listar")
    public ResponseEntity<List<Mascota>> getAll() {
        return ResponseEntity.status(200).body( mascotaService.getAll());
    }

    @PreAuthorize ("hasAnyRole('Admin', 'Vet', 'Owner')")
    @GetMapping("/ver/{id}")
    public ResponseEntity<Mascota> getMascota(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(mascotaService.getById(id));
    }

    @PreAuthorize ("hasAnyRole('Admin', 'Vet')")
    @PostMapping("/registrar")
    public ResponseEntity<Mascota> registrarMascota(@RequestBody  Mascota mascota) {
        Mascota nuevo= new Mascota(mascota.getEdad(), mascota.getNombre(), mascota.isVacunado(), mascota.getEspecie());
        mascotaService.registrar(nuevo);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PreAuthorize ("hasAnyRole('Admin')")
    @DeleteMapping("/eliminar")
    public ResponseEntity<Mascota> eliminarMascota(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(mascotaService.eliminar(id));
    }
}
