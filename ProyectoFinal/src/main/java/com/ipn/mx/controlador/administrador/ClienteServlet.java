/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador.administrador;

import com.ipn.mx.modelo.dao.UsuarioDAO;
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
@WebServlet(name = "ClienteServlet", urlPatterns = {"/administrador/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

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
            case "listaDeClientes":
                listaDeClientes(request, response);
                break;
            case "nuevo":
                agregarCliente(request, response);
                break;
            case "guardar":
                almacenarCliente(request, response);
                break;
            case "actualizar":
                actualizarCliente(request, response);
                break;
            case "eliminar":
                eliminarCliente(request, response);
                break;
            case "ver":
                mostrarCliente(request, response);
                break;
            case "verReporte":
                //mostrarReporte(request, response);
                break;
            case "graficar":
                //mostrarGrafica(request, response);
                break;
            default:
                listaDeClientes(request, response);
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

    private void listaDeClientes(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            Collection lista = dao.readAll();
            request.setAttribute("listaDeClientes", lista);

            RequestDispatcher rd = request.getRequestDispatcher("listaClientes.jsp");
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarCliente(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher vista = request.getRequestDispatcher("clientesForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarCliente(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        
        if(!request.getParameter("txtIdUsuario").equals(""))
            dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        
        dto.getEntidad().setNombre(request.getParameter("txtNombre"));
        dto.getEntidad().setPaterno(request.getParameter("txtPaterno"));
        dto.getEntidad().setMaterno(request.getParameter("txtMaterno"));
        dto.getEntidad().setEmail(request.getParameter("txtEmail"));
        dto.getEntidad().setNombreUsuario(request.getParameter("txtNombreUsuario"));
        dto.getEntidad().setClaveUsuario(request.getParameter("txtClaveUsuario"));
        dto.getEntidad().setDireccion(request.getParameter("txtDireccion"));
        
        try {
            
            if(!request.getParameter("txtIdUsuario").equals("")){//CREAR
                dao.update(dto);            
                request.setAttribute("mensaje", "Cliente actualizado con exito.");
            }else{
                dao.create(dto);            
                request.setAttribute("mensaje", "Cliente agregado con exito.");
            }            
            
            listaDeClientes(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("clientesForm.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("cliente", dto);
            vista.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        try {
            dao.delete(dto);
            request.setAttribute("mensaje", "Cliente eliminado con exito.");
            listaDeClientes(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarCliente(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("datosCliente.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("cliente", dto);
            vista.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProveedorServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
