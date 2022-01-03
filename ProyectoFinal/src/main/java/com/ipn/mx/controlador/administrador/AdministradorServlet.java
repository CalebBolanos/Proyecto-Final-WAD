/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador.administrador;

import com.ipn.mx.modelo.dao.AdministradorDAO;
import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.AdministradorDTO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
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
@WebServlet(name = "AdministradorServlet", urlPatterns = {"/administrador/AdministradorServlet"})
public class AdministradorServlet extends HttpServlet {

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

        switch (accion) {
            case "listaDeAdministradores":
                listaDeAdministradores(request, response);
                break;
            case "nuevo":
                agregarAdministrador(request, response);
                break;
            case "guardar":
                almacenarAdministrador(request, response);
                break;
            case "actualizar":
                actualizarAdministrador(request, response);
                break;
            case "eliminar":
                eliminarAdministrador(request, response);
                break;
            case "ver":
                mostrarAdministrador(request, response);
                break;
            case "verReporte":
                //mostrarReporte(request, response);
                break;
            case "graficar":
                //mostrarGrafica(request, response);
                break;
            default:
                listaDeAdministradores(request, response);
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

    private void listaDeAdministradores(HttpServletRequest request, HttpServletResponse response) {
        AdministradorDAO dao = new AdministradorDAO();
        try {
            Collection lista = dao.readAll();
            request.setAttribute("listaDeAdministradores", lista);

            RequestDispatcher rd = request.getRequestDispatcher("listaAdministradores.jsp");
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarAdministrador(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher vista = request.getRequestDispatcher("administradorForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarAdministrador(HttpServletRequest request, HttpServletResponse response) {
        AdministradorDAO dao = new AdministradorDAO();
        AdministradorDTO dto = new AdministradorDTO();
        
        if(!request.getParameter("txtIdAdministrador").equals(""))
            dto.getEntidad().setIdAdministrador(Integer.parseInt(request.getParameter("txtIdAdministrador")));
        
        dto.getEntidad().setNombre(request.getParameter("txtNombre"));
        dto.getEntidad().setUsuario(request.getParameter("txtUsuario"));
        dto.getEntidad().setContrasena(request.getParameter("txtContrasena"));
        
        try {
            
            if(!request.getParameter("txtIdAdministrador").equals("")){//CREAR
                dao.update(dto);            
                request.setAttribute("mensaje", "Administrador actualizado con exito.");
            }else{
                dao.create(dto);
                request.setAttribute("mensaje", "Administrador agregado con exito.");
            }            
            
            listaDeAdministradores(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarAdministrador(HttpServletRequest request, HttpServletResponse response) {
        AdministradorDAO dao = new AdministradorDAO();
        AdministradorDTO dto = new AdministradorDTO();
        dto.getEntidad().setIdAdministrador(Integer.parseInt(request.getParameter("id")));
        
        RequestDispatcher vista = request.getRequestDispatcher("administradorForm.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("administrador", dto);
            vista.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarAdministrador(HttpServletRequest request, HttpServletResponse response) {
        AdministradorDAO dao = new AdministradorDAO();
        AdministradorDTO dto = new AdministradorDTO();
        dto.getEntidad().setIdAdministrador(Integer.parseInt(request.getParameter("id")));
        
        try {
            dao.delete(dto);
            request.setAttribute("mensaje", "Cliente eliminado con exito.");
            listaDeAdministradores(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarAdministrador(HttpServletRequest request, HttpServletResponse response) {
        AdministradorDAO dao = new AdministradorDAO();
        AdministradorDTO dto = new AdministradorDTO();
        dto.getEntidad().setIdAdministrador(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher vista = request.getRequestDispatcher("datosAdministrador.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("administrador", dto);
            vista.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
