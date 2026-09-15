package com.docente.proyectoejemplo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.docente.proyectoejemplo.Entidades.Especie;

@Repository 
public interface EspecieRepo extends JpaRepository<Especie, Short> {

    
}
