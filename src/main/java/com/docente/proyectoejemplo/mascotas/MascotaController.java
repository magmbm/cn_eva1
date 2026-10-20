package com.docente.proyectoejemplo.mascotas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import com.docente.proyectoejemplo.mascotas.entity.Mascota;
import com.docente.proyectoejemplo.lambda.MascotaLambdaService; // 1. Importar el servicio Lambda

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController 
@RequestMapping("/api/mascotas")
public class MascotaController {


    private final MascotaService mascotaService; 
    private final MascotaLambdaService mascotaLambdaService;

    public MascotaController(MascotaService mascotaService, MascotaLambdaService mascotaLambdaService) {
        this.mascotaService= mascotaService;
        this.mascotaLambdaService= mascotaLambdaService;
    }

    /*
    @PreAuthorize ("hasAnyRole('Admin', 'Vet')")
    @GetMapping("/listar")
    public ResponseEntity<List<Mascota>> getAll() {
        return ResponseEntity.status(200).body( mascotaService.getAll());
    }
    */

    @PreAuthorize ("hasAnyRole('Admin', 'Vet', 'Owner')")
    @GetMapping("/listar/{id}")
    public ResponseEntity<Mascota> getMascota(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(mascotaService.getById(id));
    }

    @PreAuthorize ("hasAnyRole('Admin', 'Vet')")
    @PostMapping("/registrar")
    public ResponseEntity<Mascota> registrarMascota(@RequestBody Mascota mascota) {
        Mascota nuevo= new Mascota(mascota.getEdad(), mascota.getNombre(), mascota.isVacunado(), mascota.getEspecie(), mascota.getSexo(), mascota.getPeso());
        mascotaService.registrar(nuevo);
        return ResponseEntity.status(201).body(nuevo);
    }

    @PreAuthorize ("hasAnyRole('Admin')")
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mascota> eliminarMascota(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(mascotaService.eliminar(id));
    }

    @PreAuthorize ("hasAnyRole('Admin', 'Vet')")
    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> recuperarMascotaDesdeLambda() {
        
        String respuestaJson = mascotaLambdaService.obtenerMascotasDesdeLambda("");
        return ResponseEntity.ok(respuestaJson);
    }

    // 4. Opción alternativa si prefieres pasar el ID por URL (PathVariable)
    @PreAuthorize ("hasAnyRole('Admin', 'Vet', 'Owner')")
    @GetMapping(value = "/lambda/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> recuperarPorIdDesdeLambda(@PathVariable String id) {
        String respuestaJson = mascotaLambdaService.obtenerMascotasDesdeLambda(id);
        return ResponseEntity.ok(respuestaJson);
    }

    /*
    @GetMapping ("/lambda")
    public ResponseEntity<Mascota> recuperarMascota() {
    }
    */

    //
    /*
    @PreAuthorize ("hasAnyRole('Admin', 'Vet')")
    @GetMapping("/gordito")
    public ResponseEntity<LambdaResponse> masGordito() {
        RestClient restClient= RestClient.create();
        LambdaResponse result=restClient.get()
        .uri(lambdaBaseURL)
        .retrieve()
        .body(LambdaResponse.class);
        
        return ResponseEntity.status(200).body(result);
    }
    */
}