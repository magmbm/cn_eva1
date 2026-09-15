package com.docente.proyectoejemplo.servicios;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docente.proyectoejemplo.Entidades.Mascota;
import java.util.List;
import com.docente.proyectoejemplo.repositorios.MascotasRepo;
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

    
}
