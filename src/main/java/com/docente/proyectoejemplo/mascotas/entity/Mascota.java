package com.docente.proyectoejemplo.mascotas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Mascota {
    @Id 
    private Short id;

    @Column
    private Short edad;

    @Column
    private String nombre;

    @Column
    private boolean vacunado;

    @Column
    private String especie;

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

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

}
