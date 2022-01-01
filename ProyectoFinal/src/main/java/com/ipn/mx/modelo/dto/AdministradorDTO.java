/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Administrador;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author calebbolanos
 */
@Data
@AllArgsConstructor
public class AdministradorDTO implements Serializable{
    private Administrador entidad;
    
    public AdministradorDTO(){
        entidad = new Administrador();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idAdministrador: ").append(getEntidad().getIdAdministrador()).append("\n");
        sb.append("nombre: ").append(getEntidad().getNombre()).append("\n");
        sb.append("usuario: ").append(getEntidad().getUsuario()).append("\n");
        sb.append("contrasena: ").append(getEntidad().getContrasena()).append("\n");
        
        return sb.toString();
    }
}
