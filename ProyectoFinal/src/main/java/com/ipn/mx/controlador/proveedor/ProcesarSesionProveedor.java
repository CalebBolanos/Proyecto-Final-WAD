/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador.proveedor;

import com.ipn.mx.controlador.administrador.ProcesarSesionAdmin;
import com.ipn.mx.modelo.dao.ProveedorDAO;
import com.ipn.mx.modelo.dto.ProveedorDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ProcesarSesionProveedor", urlPatterns = {"/proveedor/ProcesarSesionProveedor"})
public class ProcesarSesionProveedor extends HttpServlet {

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
        
        request.setCharacterEncoding("UTF-8");

        if (!request.getParameter("usuario").equals("") && !request.getParameter("contrasena").equals("")) {
            try {
                String usuario = new String(request.getParameter("usuario").getBytes(), "UTF-8");
                String contrasena = new String(request.getParameter("contrasena").getBytes(), "UTF-8");

                ProveedorDAO dao = new ProveedorDAO();
                ProveedorDTO dto = new ProveedorDTO();

                dto.getEntidad().setCorreo(usuario);
                dto.getEntidad().setClave(contrasena);

                dto = dao.iniciarSesion(dto);

                if (dto != null) {
                    System.out.println(dto);
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("dtoProveedor", dto);

                    response.sendRedirect("inicio.jsp");
                } else {
                    response.sendRedirect("iniciarSesion.jsp?msg=Credenciales incorrectas");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProcesarSesionAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            response.sendRedirect("iniciarSesion.jsp?msg=Credenciales incorrectas");
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
