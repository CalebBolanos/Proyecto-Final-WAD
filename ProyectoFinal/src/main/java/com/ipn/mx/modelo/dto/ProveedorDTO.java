/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Proveedor;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author calebbolanos
 */
@Data
@AllArgsConstructor
public class ProveedorDTO {
    private Proveedor entidad;
    
    public ProveedorDTO(){
        entidad = new Proveedor();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idProveedor: ").append(getEntidad().getIdProveedor()).append("\n");
        sb.append("nombre: ").append(getEntidad().getNombre()).append("\n");
        sb.append("correo: ").append(getEntidad().getCorreo()).append("\n");
        sb.append("clave: ").append(getEntidad().getClave()).append("\n");
        sb.append("telefono: ").append(getEntidad().getTelefono()).append("\n");
        sb.append("web: ").append(getEntidad().getWeb()).append("\n");
        sb.append("direccion: ").append(getEntidad().getDireccion()).append("\n");
        
        return sb.toString();
    }
}
