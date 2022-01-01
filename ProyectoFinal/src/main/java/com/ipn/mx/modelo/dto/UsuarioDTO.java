/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;


import com.ipn.mx.modelo.entidades.Usuario;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author calebbolanos
 */
@Data
@AllArgsConstructor
public class UsuarioDTO implements Serializable{
    private Usuario entidad;
    
    public UsuarioDTO() {
        entidad = new Usuario();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("clave: ").append(getEntidad().getIdUsuario()).append("\n");
        sb.append("nombre: ").append(getEntidad().getNombre()).append("\n");
        sb.append("paterno: ").append(getEntidad().getPaterno()).append("\n");
        sb.append("materno: ").append(getEntidad().getMaterno()).append("\n");
        sb.append("email: ").append(getEntidad().getEmail()).append("\n");
        sb.append("nombreUsuario: ").append(getEntidad().getNombreUsuario()).append("\n");
        sb.append("claveUsuario: ").append(getEntidad().getClaveUsuario()).append("\n");
        
        return sb.toString();
    }
}
