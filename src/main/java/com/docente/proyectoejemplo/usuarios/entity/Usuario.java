package com.docente.proyectoejemplo.usuarios.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Usuario {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @Column 
    private Integer numrun_usuario;

    @Column 
    private String nomnbre_usuario;

    @Column 
    private String papellido_usuario;

    @Column 
    private String sapellido_usuario; 

    @Column 
    private String email_usuario; 
    
    @Column 
    private String dvrun_usuario;

    public Usuario() {

    }

    public Usuario(Integer numrun_usuario, String nomnbre_usuario, String papellido_usuario, String sapellido_usuario,
            String email_usuario, String dvrun_usuario) {
        this.numrun_usuario = numrun_usuario;
        this.nomnbre_usuario = nomnbre_usuario;
        this.papellido_usuario = papellido_usuario;
        this.sapellido_usuario = sapellido_usuario;
        this.email_usuario = email_usuario;
        this.dvrun_usuario = dvrun_usuario;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
    public Integer getNumrun_usuario() {
        return numrun_usuario;
    }
    public void setNumrun_usuario(Integer numrun_usuario) {
        this.numrun_usuario = numrun_usuario;
    }
    public String getNomnbre_usuario() {
        return nomnbre_usuario;
    }
    public void setNomnbre_usuario(String nomnbre_usuario) {
        this.nomnbre_usuario = nomnbre_usuario;
    }
    public String getPapellido_usuario() {
        return papellido_usuario;
    }
    public void setPapellido_usuario(String papellido_usuario) {
        this.papellido_usuario = papellido_usuario;
    }
    public String getSapellido_usuario() {
        return sapellido_usuario;
    }
    public void setSapellido_usuario(String sapellido_usuario) {
        this.sapellido_usuario = sapellido_usuario;
    }
    public String getEmail_usuario() {
        return email_usuario;
    }
    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }
    public String getDvrun_usuario() {
        return dvrun_usuario;
    }
    public void setDvrun_usuario(String dvrun_usuario) {
        this.dvrun_usuario = dvrun_usuario;
    }
    
}
