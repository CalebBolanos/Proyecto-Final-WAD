/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.VentaDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Kimi
 */
public class VentaDAO {
    private static final String SQL_INSERT = "insert into Venta (montoFinal, fecha) values (?, ?)";
    private static final String SQL_UPDATE = "update Venta set montoFinal = ?, fecha = ? where idVenta = ?;";
    private static final String SQL_DELETE = "delete from Venta where idVenta = ?";
    private static final String SQL_READ = "select idVenta, montoFinal, fecha from Venta where idVenta = ?";
    private static final String SQL_READ_ALL = "select idVenta, montoFinal, fecha from Venta";

    private Connection conexion;
    
    public Connection conectar() {
        String user = "postgres";
        String pwd = "n0m3l0s3";
        String url="jdbc:postgresql://localhost:5432/Venta";
        String pgDriver = "org.postgresql.Driver";
        try {
            Class.forName(pgDriver);
            conexion = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }
    
    public void create(VentaDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setFloat(1, dto.getEntidad().getMontoFinal());
            ps.setDate(2, dto.getEntidad().getFecha());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }

        }
    }
    
    public void update(VentaDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setFloat(1, dto.getEntidad().getMontoFinal());
            ps.setDate(2, dto.getEntidad().getFecha());
            ps.setInt(3, dto.getEntidad().getIdVenta());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    
    public void delete(VentaDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdVenta());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
    
    public VentaDTO read(VentaDTO dto)throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conexion.prepareStatement(SQL_READ);
            ps.setInt(1, dto.getEntidad().getIdVenta());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size() > 0){
                return (VentaDTO)resultados.get(0);
            }else{
                return null;
            }
            
        }finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conexion != null) conexion.close();
        }
    }
    
    private List obtenerResultados(ResultSet rs) throws SQLException {
        List resultados = new ArrayList();
        while(rs.next()){
            VentaDTO dto = new VentaDTO();
            dto.getEntidad().setIdVenta(rs.getInt("idventa"));
            dto.getEntidad().setMontoFinal(rs.getFloat("montoFinal"));
            dto.getEntidad().setFecha(rs.getDate("fecha"));
            resultados.add(dto);
        }
        return resultados;
    }
    
    public List readAll()throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conexion.prepareStatement(SQL_READ_ALL);
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size() > 0){
                return resultados;
            }else{
                return null;
            }
            
        }finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conexion != null) conexion.close();
        }
    }
    
    public static void main(String[] args){
        VentaDAO dao = new VentaDAO();
        
        VentaDTO dto = new VentaDTO();
        String str="2015-03-31";
        Date date=Date.valueOf(str);
        
        dto.getEntidad().setIdVenta(1);
        //dto.getEntidad().setMontoFinal(148);
        //dto.getEntidad().setFecha(date);
        
        
        try{
            //dao.create(dto);
            //dao.update(dto);
            //System.out.println(dao.readAll());
            //System.out.println(dao.read(dto));
            dao.delete(dto);
        }catch(SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
}
