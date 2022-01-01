/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author calebbolanos
 */
@WebServlet(name = "ProcesarInicioSesion", urlPatterns = {"/ProcesarInicioSesion"})
public class ProcesarInicioSesion extends HttpServlet {

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
        response.sendRedirect("iniciarSesion.jsp");
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
                
                UsuarioDAO dao = new UsuarioDAO();
                UsuarioDTO dto = new UsuarioDTO();
                
                dto.getEntidad().setNombreUsuario(usuario);
                dto.getEntidad().setClaveUsuario(contrasena);
                
                dto = dao.iniciarSesion(dto);
                
                if(dto != null){
                    HttpSession sesion = request.getSession();
                    sesion.setAttribute("dtoUsuario", dto);
                    
                    response.sendRedirect("index.jsp");
                }else{
                    response.sendRedirect("iniciarSesion.jsp?msg=Usuario o contraseña incorrecta");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProcesarInicioSesion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }else {
            response.sendRedirect("iniciarSesion.jsp?msg=Usuario o contraseña incorrecta");
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
