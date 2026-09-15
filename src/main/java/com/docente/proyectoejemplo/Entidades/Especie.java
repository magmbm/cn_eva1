package com.docente.proyectoejemplo.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity 
public class Especie {
    @Column 
    private Short id;

    @Column 
    private String especie;

    @Column 
    private String subespecie;

    public Especie(String especie, String subespecie) {
        this.especie = especie;
        this.subespecie = subespecie;
    }

    public Especie() {}

    public Short getId() {
        return id;
    }
    public void setId(Short id) {
        this.id = id;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public String getSubespecie() {
        return subespecie;
    }
    public void setSubespecie(String subespecie) {
        this.subespecie = subespecie;
    }
}
