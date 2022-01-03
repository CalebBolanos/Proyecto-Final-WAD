/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador.proveedor;

import com.ipn.mx.controlador.administrador.ProveedorServlet;
import com.ipn.mx.modelo.dao.ProveedorDAO;
import com.ipn.mx.modelo.dto.ProveedorDTO;
import com.ipn.mx.utilerias.EnviarMail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author calebbolanos
 */
@WebServlet(name = "GuardarProveedor", urlPatterns = {"/proveedor/GuardarProveedor"})
public class GuardarProveedor extends HttpServlet {

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
        ProveedorDAO dao = new ProveedorDAO();
        ProveedorDTO dto = new ProveedorDTO();
        try {
            dto.getEntidad().setNombre(request.getParameter("txtNombre"));
            dto.getEntidad().setCorreo(request.getParameter("txtCorreo"));
            dto.getEntidad().setClave(request.getParameter("txtClave"));
            dto.getEntidad().setTelefono(request.getParameter("txtTelefono"));
            dto.getEntidad().setWeb(request.getParameter("txtWeb"));
            dto.getEntidad().setDireccion(request.getParameter("txtDireccion"));
            dao.create(dto);

            EnviarMail email = new EnviarMail();
            String destinatario = dto.getEntidad().getCorreo();
            String asunto = "Registro en plataforma exitoso";
            String texto = "Ustede ha sido registrado en plataforma de forma exitosa...";
            email.enviarCorreo(destinatario, asunto, texto);

            response.sendRedirect("iniciarSesion.jsp?msg=Registro completado. Ahora puedes iniciar sesion");
        } catch (SQLException ex) {
            Logger.getLogger(GuardarProveedor.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("crearCuenta.jsp?msg=Llena todo el formulario");
        }
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

}
