/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador.administrador;

import com.ipn.mx.modelo.dao.ProveedorDAO;
import com.ipn.mx.modelo.dto.ProveedorDTO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ProveedorServlet", urlPatterns = {"/administrador/ProveedorServlet"})
public class ProveedorServlet extends HttpServlet {

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
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("dtoAdministrador") == null) {
            response.sendRedirect("iniciarSesion.jsp");
            return;
        }
        
        String accion = request.getParameter("accion") == null ? "" : request.getParameter("accion");
        
        switch(accion){
            case "listaDeProveedores":
                listaDeProveedores(request, response);
                break;
            case "nuevo":
                agregarProveedor(request, response);
                break;
            case "guardar":
                almacenarProveedor(request, response);
                break;
            case "actualizar":
                actualizarProveedor(request, response);
                break;
            case "eliminar":
                eliminarProveedor(request, response);
                break;
            case "ver":
                mostrarProveedor(request, response);
                break;
            case "verReporte":
                //mostrarReporte(request, response);
                break;
            case "graficar":
                //mostrarGrafica(request, response);
                break;
            default:
                listaDeProveedores(request, response);
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

    private void listaDeProveedores(HttpServletRequest request, HttpServletResponse response) {
        ProveedorDAO dao = new ProveedorDAO();
        try {
            Collection lista = dao.readAll();
            request.setAttribute("listaDeProveedores", lista);

            RequestDispatcher rd = request.getRequestDispatcher("listaProveedores.jsp");
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarProveedor(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher vista = request.getRequestDispatcher("proveedoresForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarProveedor(HttpServletRequest request, HttpServletResponse response) {
        ProveedorDAO dao = new ProveedorDAO();
        ProveedorDTO dto = new ProveedorDTO();
        
        if(!request.getParameter("txtIdProveedor").equals(""))
            dto.getEntidad().setIdProveedor(Integer.parseInt(request.getParameter("txtIdProveedor")));
        
        dto.getEntidad().setNombre(request.getParameter("txtNombre"));
        dto.getEntidad().setCorreo(request.getParameter("txtCorreo"));
        dto.getEntidad().setClave(request.getParameter("txtClave"));
        dto.getEntidad().setTelefono(request.getParameter("txtTelefono"));
        dto.getEntidad().setWeb(request.getParameter("txtWeb"));
        dto.getEntidad().setDireccion(request.getParameter("txtDireccion"));
        
        try {
            
            if(!request.getParameter("txtIdProveedor").equals("")){//CREAR
                dao.update(dto);            
                request.setAttribute("mensaje", "Proveedor actualizado con exito.");
            }else{
                dao.create(dto);            
                request.setAttribute("mensaje", "Proveedor agregado con exito.");
            }            
            
            listaDeProveedores(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarProveedor(HttpServletRequest request, HttpServletResponse response) {
        ProveedorDAO dao = new ProveedorDAO();
        ProveedorDTO dto = new ProveedorDTO();
        dto.getEntidad().setIdProveedor(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("proveedoresForm.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("proveedor", dto);
            vista.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarProveedor(HttpServletRequest request, HttpServletResponse response) {
        ProveedorDAO dao = new ProveedorDAO();
        ProveedorDTO dto = new ProveedorDTO();
        dto.getEntidad().setIdProveedor(Integer.parseInt(request.getParameter("id")));

        try {
            dao.delete(dto);
            listaDeProveedores(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarProveedor(HttpServletRequest request, HttpServletResponse response) {
        ProveedorDAO dao = new ProveedorDAO();
        ProveedorDTO dto = new ProveedorDTO();
        dto.getEntidad().setIdProveedor(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("datosProveedor.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("proveedor", dto);
            vista.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
