package com.docente.proyectoejemplo.mascotas;

import org.springframework.stereotype.Service;

import com.docente.proyectoejemplo.mascotas.entity.Mascota;
import java.util.List;
import java.util.Optional;

@Service 
public class MascotaService {
    private final MascotasRepo mascotasRepo;

    public MascotaService(MascotasRepo mascotasRepo) {
        this.mascotasRepo= mascotasRepo;
    }

    public List<Mascota> getAll() {
        return this.mascotasRepo.findAll();
    }

    public Mascota getById(Short idMascota) {
        Optional<Mascota> mascota= this.mascotasRepo.findById(idMascota);
        if (mascota.isEmpty()) {
            return null;
        }        
        return mascota.get();
    }
    
    public Mascota registrar(Mascota mascota) {
        return this.mascotasRepo.save(mascota);
    }
    
}
