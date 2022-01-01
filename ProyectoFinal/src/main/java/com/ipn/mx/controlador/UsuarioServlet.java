/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.utilerias.EnviarMail;
import java.io.IOException;
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
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

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
        if (sesion.getAttribute("dtoUsuario") == null) {
            response.sendRedirect("iniciarSesion.jsp");
            return;
        }

        String accion = request.getParameter("accion");

        if (accion.equals("listaDeUsuarios")) {
            listaDeUsuarios(request, response);
        } else {
            if (accion.equals("nuevo")) {
                agregarUsuario(request, response);
            } else {
                if (accion.equals("eliminar")) {
                    eliminarUsuario(request, response);
                } else {
                    if (accion.equals("actualizar")) {
                        actualizarUsuario(request, response);
                    } else {
                        if (accion.equals("guardar")) {
                            almacenarUsuario(request, response);
                        } else {
                            if (accion.equals("ver")) {
                                mostrarUsuario(request, response);
                            }
                        }
                    }
                }
            }
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

    private void listaDeUsuarios(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            Collection lista = dao.readAll();
            request.setAttribute("listaDeUsuarios", lista);

            RequestDispatcher rd = request.getRequestDispatcher("/usuarios/listaUsuarios.jsp");
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher vista = request.getRequestDispatcher("/usuarios/usuariosForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        try {
            dto=dao.read(dto);
            EnviarMail email = new EnviarMail();
            String destinatario = dto.getEntidad().getEmail();
            String asunto = "Eliminación de cuenta";
            String texto = "Usted ha eliminado su cuenta en plataforma de forma exitosa...";
            email.enviarCorreo(destinatario, asunto, texto);
            request.setAttribute("mensaje", "Usuario creado con exito.");
            dao.delete(dto);
            listaDeUsuarios(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("/usuarios/usuariosForm.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("usuario", dto);
            vista.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("/usuarios/datosUsuario.jsp");

        try {
            dto = dao.read(dto);
            EnviarMail email = new EnviarMail();
            String destinatario = dto.getEntidad().getEmail();
            String asunto = "Consulta de datos";
            String texto = "Ustede ha consultado sus datos en plataforma de forma exitosa...";
            email.enviarCorreo(destinatario, asunto, texto);
            request.setAttribute("mensaje", "Usuario creado con exito.");
            request.setAttribute("usuario", dto);
            vista.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();

        if (!request.getParameter("txtIdUsuario").equals("")) {
            dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        }

        dto.getEntidad().setNombre(request.getParameter("txtNombre"));
        dto.getEntidad().setPaterno(request.getParameter("txtPaterno"));
        dto.getEntidad().setMaterno(request.getParameter("txtMaterno"));
        dto.getEntidad().setNombreUsuario(request.getParameter("txtNombreUsuario"));
        dto.getEntidad().setClaveUsuario(request.getParameter("txtClaveUsuario"));
        dto.getEntidad().setEmail(request.getParameter("txtEmail"));

        try {

            if (!request.getParameter("txtIdUsuario").equals("")) {
                dao.update(dto);
                EnviarMail email = new EnviarMail();
                String destinatario = dto.getEntidad().getEmail();
                String asunto = "Actuaalizacion de datos en plataforma";
                String texto = "Ustede ha actualizado su datos en plataforma de forma exitosa...";
                email.enviarCorreo(destinatario, asunto, texto);
                request.setAttribute("mensaje", "Usuario creado con exito.");
                request.setAttribute("mensaje", "Usuario actualizado con exito.");
            } else {
                dao.create(dto);
                EnviarMail email = new EnviarMail();
                String destinatario = dto.getEntidad().getEmail();
                String asunto = "Registro en plataforma exitoso";
                String texto = "Ustede ha sido registrado en plataforma de forma exitosa...";
                email.enviarCorreo(destinatario, asunto, texto);
                request.setAttribute("mensaje", "Usuario creado con exito.");
            }

            listaDeUsuarios(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
