/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.AdministradorDTO;
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
 * @author Kimi
 */
public class AdministradorDAO {
    private static final String SQL_INSERT = "insert into Administrador (nombre, usuario, contrasena) values (?, ?, ?)";
    private static final String SQL_UPDATE = "update Administrador set nombre = ?, usuario = ?, contrasena = ? where idAdministrador = ?;";
    private static final String SQL_DELETE = "delete from Administrador where idAdministrador = ?";
    private static final String SQL_READ = "select idAdministrador, nombre, usuario, contrasena from Administrador where idAdministrador = ?";
    private static final String SQL_READ_ALL = "select idAdministrador, nombre, usuario, contrasena from Administrador";
    private static final String SQL_INICIAR_SESION = "select SesionAdmin(?, ?);";

    private Connection conexion;
    
    public Connection conectar() {
        String user = "postgres";
        String pwd = "Bowser:7";
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
    
    public void create(AdministradorDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombre());
            ps.setString(2, dto.getEntidad().getUsuario());
            ps.setString(3, dto.getEntidad().getContrasena());
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
    
    public void update(AdministradorDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombre());
            ps.setString(2, dto.getEntidad().getUsuario());
            ps.setString(3, dto.getEntidad().getContrasena());
            ps.setInt(4, dto.getEntidad().getIdAdministrador());
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
    
    public void delete(AdministradorDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdAdministrador());
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
    
    public AdministradorDTO read(AdministradorDTO dto)throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conexion.prepareStatement(SQL_READ);
            ps.setInt(1, dto.getEntidad().getIdAdministrador());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size() > 0){
                return (AdministradorDTO)resultados.get(0);
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
            AdministradorDTO dto = new AdministradorDTO();
            dto.getEntidad().setIdAdministrador(rs.getInt("idadministrador"));
            dto.getEntidad().setNombre(rs.getString("nombre"));
            dto.getEntidad().setUsuario(rs.getString("usuario"));
            dto.getEntidad().setContrasena(rs.getString("contrasena"));
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
    
    public AdministradorDTO iniciarSesion(AdministradorDTO dto)throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conexion.prepareStatement(SQL_INICIAR_SESION);
            ps.setString(1, dto.getEntidad().getUsuario());
            ps.setString(2, dto.getEntidad().getContrasena());
            rs = ps.executeQuery();
            if (rs.next()) {
                if(rs.getInt(1) > 0){
                dto.getEntidad().setIdAdministrador(rs.getInt(1));
                return read(dto);
                }
                else{
                    return null;
                }
            }
            else{
                return null;
            }
            
        }finally{
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conexion != null) conexion.close();
        }
    }
    
    public static void main(String[] args){
        AdministradorDAO dao = new AdministradorDAO();
        
        AdministradorDTO dto = new AdministradorDTO();
        //dto.getEntidad().setIdAdministrador(2);
        //dto.getEntidad().setNombre("cambio");
        dto.getEntidad().setUsuario("usa");
        dto.getEntidad().setContrasena("con");
        
        
        try{
            System.out.println(dao.iniciarSesion(dto));
            //dao.create(dto);
            //dao.update(dto);
            //System.out.println(dao.readAll());
            //System.out.println(dao.read(dto));
            //dao.delete(dto);
        }catch(SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
}
