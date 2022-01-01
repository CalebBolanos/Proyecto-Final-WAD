/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.GraficaDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author calebbolanos
 */
public class GraficaDAO {

    private Connection conexion;
    
    private static final String SQL_GRAFICAR = 
            "select nombreCategoria, count(*) as cantidad from Categoria inner join Producto on Categoria.idCategoria = Producto.claveCategoria "
            + "group by Categoria.nombreCategoria";
    
    private void conectar(){
        String user = "xmmeyaitffznjj";
        String pwd = "91571a46f6c84003a03f0fa15acb521629fce3fc4677fbc0a1962051e0b14671";
        String url = "jdbc:postgresql://ec2-54-158-247-97.compute-1.amazonaws.com:5432/ddcuf2h47ql3u5";
        String pgDriver = "org.postgresql.Driver";
        
        try{
            Class.forName(pgDriver);
            conexion = DriverManager.getConnection(url, user, pwd);
        }catch(Exception e){
            e.printStackTrace();
        }
    };
    
    public List graficarProductosPorCategoria() throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();
        try{
            ps = conexion.prepareStatement(SQL_GRAFICAR);
            rs = ps.executeQuery();
            while(rs.next()){
                GraficaDTO dto = new GraficaDTO();
                dto.setNombreCategoria(rs.getString("nombreCategoria"));
                dto.setCantidad(rs.getInt("cantidad"));
                lista.add(dto);
            }
        }finally{
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
            if(conexion!=null) conexion.close();
        } 
        return lista;
    }
    
    
    public static void main (String[] args){
        GraficaDAO dao = new GraficaDAO();
        
        try {
            System.out.println(dao.graficarProductosPorCategoria());
        } catch (SQLException ex) {
            Logger.getLogger(GraficaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
