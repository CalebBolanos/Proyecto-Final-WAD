/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ProveedorDTO;
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
public class ProveedorDAO {
    private static final String SQL_INSERT = "insert into Proveedor (nombre, correo, clave, telefono, web, direccion) values (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update Proveedor set nombre = ?, correo = ?, clave = ?, telefono = ?, web = ?, direccion = ? where idProveedor = ?;";
    private static final String SQL_DELETE = "delete from Proveedor where idProveedor = ?";
    private static final String SQL_READ = "select idProveedor, nombre, correo, clave, telefono, web, direccion from Proveedor where idProveedor = ?";
    private static final String SQL_READ_ALL = "select idProveedor, nombre, correo, clave, telefono, web, direccion from Proveedor";
    private static final String SQL_INICIAR_SESION = "select SesionPro(?, ?);";

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
    
    public void create(ProveedorDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombre());
            ps.setString(2, dto.getEntidad().getCorreo());
            ps.setString(3, dto.getEntidad().getClave());
            ps.setString(4, dto.getEntidad().getTelefono());
            ps.setString(5, dto.getEntidad().getWeb());
            ps.setString(6, dto.getEntidad().getDireccion());
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
    
    public void update(ProveedorDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombre());
            ps.setString(2, dto.getEntidad().getCorreo());
            ps.setString(3, dto.getEntidad().getClave());
            ps.setString(4, dto.getEntidad().getTelefono());
            ps.setString(5, dto.getEntidad().getWeb());
            ps.setString(6, dto.getEntidad().getDireccion());
            ps.setInt(7, dto.getEntidad().getIdProveedor());
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
    
    public void delete(ProveedorDTO dto) throws SQLException {
        conectar();
        PreparedStatement ps = null;
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdProveedor());
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
    
    public ProveedorDTO read(ProveedorDTO dto)throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conexion.prepareStatement(SQL_READ);
            ps.setInt(1, dto.getEntidad().getIdProveedor());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size() > 0){
                return (ProveedorDTO)resultados.get(0);
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
            ProveedorDTO dto = new ProveedorDTO();
            dto.getEntidad().setIdProveedor(rs.getInt("idproveedor"));
            dto.getEntidad().setNombre(rs.getString("nombre"));
            dto.getEntidad().setCorreo(rs.getString("correo"));
            dto.getEntidad().setClave(rs.getString("clave"));
            dto.getEntidad().setTelefono(rs.getString("telefono"));
            dto.getEntidad().setWeb(rs.getString("web"));
            dto.getEntidad().setDireccion(rs.getString("direccion"));
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
    
    public ProveedorDTO iniciarSesion(ProveedorDTO dto)throws SQLException{
        conectar();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conexion.prepareStatement(SQL_INICIAR_SESION);
            ps.setString(1, dto.getEntidad().getCorreo());
            ps.setString(2, dto.getEntidad().getClave());
            rs = ps.executeQuery();
            if (rs.next()) {
                if(rs.getInt(1) > 0){
                dto.getEntidad().setIdProveedor(rs.getInt(1));
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
        ProveedorDAO dao = new ProveedorDAO();
        
        ProveedorDTO dto = new ProveedorDTO();
        //dto.getEntidad().setIdProveedor(2);
        //dto.getEntidad().setNombre("cambio");
        dto.getEntidad().setCorreo("usa");
        dto.getEntidad().setClave("con");
        //dto.getEntidad().setTelefono("1235");
        //dto.getEntidad().setWeb("web");
        //dto.getEntidad().setDireccion("dire");*/
        
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
