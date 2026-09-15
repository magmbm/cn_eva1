package com.docente.proyectoejemplo.mascotas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table (name="MASCOTAS")
public class Mascota {
    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Short id;

    @Column(name = "edad_mascota")
    private Short edad;

    @Column(name= "nombre_mascota")
    private String nombre;

    @Column(name= "vacunado")
    private boolean vacunado;

    @Column(name= "especie")
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
