package com.docente.proyectoejemplo.repositorios;

import org.springframework.stereotype.Repository;

import com.docente.proyectoejemplo.Entidades.Mascota;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository 
public interface MascotasRepo extends JpaRepository<Mascota, Short>{
    
}
