/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.ProductoDTO;
import java.sql.CallableStatement;
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
 * @author kimi
 */
public class ProductoDAO {
    
    private static final String SQL_INSERT="call spInsertarProducto(?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update producto set nombreProducto = ?, descripcionProducto = ?, precio = ?, existencia = ?, stockMinimo = ?, imagenProducto = ?, claveCategoria = ? where idProducto = ?";
    private static final String SQL_DELETE="call spEliminarProducto(?)";
    private static final String SQL_READ="select * from seleccionarProducto(?)";
    private static final String SQL_READ_ALL="select * from seleccionaTodoProducto()";
    
    private Connection conexion;
    public Connection conectar(){
        String user = "postgres";
        String pwd = "n0m3l0s3";
        String url="jdbc:postgresql://localhost:5432/Venta";
        String pgDriver = "org.postgresql.Driver";
        
        try{
            Class.forName(pgDriver);
            conexion = DriverManager.getConnection(url, user, pwd);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conexion;
    }
        
    
    public void create(ProductoDTO dto) throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, dto.getEntidad().getNombreProducto());
            ps.setString(2, dto.getEntidad().getDescripcionProducto());
            ps.setFloat(3, dto.getEntidad().getPrecio());
            ps.setInt(4, dto.getEntidad().getExistencia());
            ps.setInt(5, dto.getEntidad().getStockMinimo());
            ps.setString(6, dto.getEntidad().getImagenProducto());
            ps.setInt(7, dto.getEntidad().getClaveCategoria());
            
            ps.executeUpdate();
            
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.INFO, null, "Almacenado correctamente");
        }finally{
            if(ps != null)
                ps.close();
            
            if (conexion != null)
                conexion.close();
        }
    }
    
    public void Update(ProductoDTO dto)throws SQLException{
        conectar();
        PreparedStatement ps = null;
        try{
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, dto.getEntidad().getNombreProducto());
            ps.setString(2, dto.getEntidad().getDescripcionProducto());
            ps.setFloat(3, dto.getEntidad().getPrecio());
            ps.setInt(4, dto.getEntidad().getExistencia());
            ps.setInt(5, dto.getEntidad().getStockMinimo());
            ps.setString(6, dto.getEntidad().getImagenProducto());
            ps.setInt(7, dto.getEntidad().getClaveCategoria());
            ps.setInt(8, dto.getEntidad().getIdProducto());
            ps.executeUpdate();
        }finally{
            if(ps !=null){
                ps.close();
            }
            if(conexion != null){
                conexion.close();
            }
        }
    }
    
    public void delete(ProductoDTO dto) throws SQLException{
        conectar();
        CallableStatement ps = null;
        try{
            ps = conexion.prepareCall(SQL_DELETE);
            ps.setInt(1, dto.getEntidad().getIdProducto());
            ps.executeUpdate();
        }finally{
            if(ps != null)
                ps.close();
            if(conexion != null)
                conexion.close();
        }
    }
    
    public ProductoDTO read(ProductoDTO dto) throws SQLException{
        conectar();
        CallableStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conexion.prepareCall(SQL_READ);
            ps.setInt(1, dto.getEntidad().getIdProducto());
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            
            if(resultados.size()>0)
                return (ProductoDTO)resultados.get(0);
            else
                return null;
        }finally{
            if(rs != null)
                rs.close();
            if(ps != null)
                ps.close();
            if(conexion != null)
                conexion.close();
        }
    }
    
    public List readAll() throws SQLException{
        conectar();
        CallableStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conexion.prepareCall(SQL_READ_ALL);
            rs = ps.executeQuery();
            List resultados = obtenerResultados(rs);
            if(resultados.size() > 0)
                return resultados;
            else
                return null;
        }finally{
            if(rs != null)
                rs.close();
            if(ps != null)
                ps.close();
            if(conexion != null)
                conexion.close();
        }
    }

    private List obtenerResultados(ResultSet rs) throws SQLException{
        List resultados = new ArrayList();
        while(rs.next()){
            ProductoDTO dto =  new ProductoDTO();
            dto.getEntidad().setIdProducto(rs.getInt("idProducto"));
            dto.getEntidad().setNombreProducto(rs.getString("nombreProducto"));
            dto.getEntidad().setDescripcionProducto(rs.getString("descripcionProducto"));            
            dto.getEntidad().setPrecio(rs.getFloat("precio"));
            dto.getEntidad().setExistencia(rs.getInt("existencia"));
            dto.getEntidad().setStockMinimo(rs.getInt("stockMinimo"));
            dto.getEntidad().setImagenProducto(rs.getString("imagenProducto"));
            dto.getEntidad().setClaveCategoria(rs.getInt("claveCategoria"));
            resultados.add(dto);
        }
        return resultados;
    }
    
    public static void main (String [] args){
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        
        dto.getEntidad().setIdProducto(3);
        /*dto.getEntidad().setNombreProducto("cambiazo");
        dto.getEntidad().setDescripcionProducto("jiji");
        dto.getEntidad().setPrecio(542);
        dto.getEntidad().setExistencia(31);
        dto.getEntidad().setStockMinimo(10);
        dto.getEntidad().setImagenProducto("imagen2");
        dto.getEntidad().setClaveCategoria(1);*/
        
        try {
            //dao.create(dto);
            //dao.Update(dto);
            //System.out.println(dao.readAll());
            //System.out.println(dao.read(dto));
            dao.delete(dto);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
