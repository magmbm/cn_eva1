package com.docente.proyectoejemplo.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id; 
    @Column 
    private Short edad;
    @Column 
    private String nombre;
    @Column 
    private boolean vacunado;
    @Column 
    private Especie FK_especie;

    public Mascota(Short edad, String nombre, boolean vacunado, Especie fK_especie) {
        this.edad = edad;
        this.nombre = nombre;
        this.vacunado = vacunado;
        FK_especie = fK_especie;
    }

    public Short getId() {
        return id;
    }
    public void setId(Short id) {
        this.id = id;
    }
    public Short getEdad() {
        return edad;
    }
    public void setEdad(Short edad) {
        this.edad = edad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public boolean isVacunado() {
        return vacunado;
    }
    public void setVacunado(boolean vacunado) {
        this.vacunado = vacunado;
    }
    public Especie getFK_especie() {
        return FK_especie;
    }
    public void setFK_especie(Especie fK_especie) {
        FK_especie = fK_especie;
    }
    
}
