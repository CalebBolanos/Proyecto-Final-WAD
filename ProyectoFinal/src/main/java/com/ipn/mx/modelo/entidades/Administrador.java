/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author calebbolanos
 */
@Data
@NoArgsConstructor
public class Administrador implements Serializable{
    private int idAdministrador;
    private String nombre, usuario, contrasena;
}
