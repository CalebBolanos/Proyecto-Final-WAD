/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador.usuario;

import com.ipn.mx.controlador.administrador.ClienteServlet;
import com.ipn.mx.controlador.administrador.ProductoServlet;
import com.ipn.mx.controlador.administrador.ProveedorServlet;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dao.VentaDAO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.modelo.dto.VentaDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author calebbolanos
 */
@WebServlet(name = "ControladorUsuario", urlPatterns = {"/usuario/ControladorUsuario"})
public class ControladorUsuario extends HttpServlet {

    HttpSession sesion;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        sesion = request.getSession();
        if (sesion.getAttribute("dtoUsuario") == null) {
            response.sendRedirect("../iniciarSesion.jsp");
            return;
        }
        
        String accion = request.getParameter("accion") == null ? "" : request.getParameter("accion");
        
        switch(accion){
            case "inicio":
                inicio(request, response);
                break;
            case "verProducto":
                verProducto(request, response);
                break;
            case "comprar":
                comprarProducto(request, response);
                break;
            case "historial":
                historial(request, response);
                break;
            case "cuenta":
                cuenta(request, response);
                break;
            case "guardarCliente":
                guardarCliente(request, response);
                break;
            case "verReporte":
                //mostrarReporte(request, response);
                break;
            case "graficar":
                //mostrarGrafica(request, response);
                break;
            default:
                inicio(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void inicio(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        RequestDispatcher vista = request.getRequestDispatcher("inicio.jsp");
        try {
            Collection lista = dao.readAll();
            request.setAttribute("listaDeProductos", lista);
            vista.forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("datosProducto.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("producto", dto);
            vista.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void comprarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        
        VentaDAO ventaDao = new VentaDAO();
        VentaDTO ventaDto = new VentaDTO();
        
        
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            ventaDto.getEntidad().setMontoFinal(dto.getEntidad().getPrecio());
            ventaDto.getEntidad().setFecha(new Date(System.currentTimeMillis()));
            
            ventaDao.create(ventaDto);
            request.setAttribute("mensaje", "Compra hecha con exito.");
            inicio(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void historial(HttpServletRequest request, HttpServletResponse response) {
        VentaDAO dao = new VentaDAO();
        RequestDispatcher vista = request.getRequestDispatcher("historial.jsp");
        try {
            Collection lista = dao.readAll();
            request.setAttribute("listaDeCompras", lista);
            vista.forward(request, response);
        } catch (ServletException | IOException | SQLException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cuenta(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = (UsuarioDTO) sesion.getAttribute("dtoUsuario");

        RequestDispatcher vista = request.getRequestDispatcher("clientesForm.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("cliente", dto);
            sesion.setAttribute("dtoUsuario", dto);
            vista.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void guardarCliente(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();

        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        dto.getEntidad().setNombre(request.getParameter("txtNombre"));
        dto.getEntidad().setPaterno(request.getParameter("txtPaterno"));
        dto.getEntidad().setMaterno(request.getParameter("txtMaterno"));
        dto.getEntidad().setEmail(request.getParameter("txtEmail"));
        dto.getEntidad().setNombreUsuario(request.getParameter("txtNombreUsuario"));
        dto.getEntidad().setClaveUsuario(request.getParameter("txtClaveUsuario"));
        dto.getEntidad().setDireccion(request.getParameter("txtDireccion"));
        
        try {
            
            dao.update(dto);            
            request.setAttribute("mensaje", "Cliente actualizado con exito.");          
            
            cuenta(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
