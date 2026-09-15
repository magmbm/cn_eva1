package com.docente.proyectoejemplo.mascotas;

import org.springframework.stereotype.Repository;

import com.docente.proyectoejemplo.mascotas.entity.Mascota;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository 
public interface MascotasRepo extends JpaRepository<Mascota, Integer>{
    
}
