package com.docente.proyectoejemplo.usuarios;

public class UsuarioRegistradoResponse {
   private boolean registrado;

   public UsuarioRegistradoResponse(boolean registrado) {
      this.registrado= registrado;
   }

   public boolean getRegistrado() {
      return registrado;
   }

   public void setRegistrado(boolean registrado) {
      this.registrado = registrado;
   }


   
}
