/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Venta;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author calebbolanos
 */
@Data
@AllArgsConstructor
public class VentaDTO {
    private Venta entidad;
    
    public VentaDTO(){
        entidad = new Venta();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("idVenta: ").append(getEntidad().getIdVenta()).append("\n");
        sb.append("montoFinal: ").append(getEntidad().getMontoFinal()).append("\n");
        sb.append("fecha: ").append(getEntidad().getFecha()).append("\n");
        return sb.toString();
    }
}
