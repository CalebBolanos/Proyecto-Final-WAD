/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.ipn.mx.controlador.administrador;

import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author calebbolanos
 */
@WebServlet(name = "ProductoServletAdmin", urlPatterns = {"/administrador/ProductoServlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
        
)
public class ProductoServlet extends HttpServlet {

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
            case "listaDeProductos":
                listaDeProductos(request, response);
                break;
            case "nuevo":
                agregarProducto(request, response);
                break;
            case "guardar":
                almacenarProducto(request, response);
                break;
            case "actualizar":
                actualizarProducto(request, response);
                break;
            case "actualizarImagen":
                actualizarImagen(request, response);
                break;
            case "eliminar":
                eliminarProducto(request, response);
                break;
            case "ver":
                mostrarProducto(request, response);
                break;
            case "verReporte":
                //mostrarReporte(request, response);
                break;
            case "graficar":
                //mostrarGrafica(request, response);
                break;
            default:
                listaDeProductos(request, response);
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

    private void listaDeProductos(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        try {
            Collection lista = dao.readAll();
            request.setAttribute("listaDeProductos", lista);

            RequestDispatcher rd = request.getRequestDispatcher("listaProductos.jsp");
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher vista = request.getRequestDispatcher("productosForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    ///imagenes/default.png
    private void almacenarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();

        if (!request.getParameter("txtIdProducto").equals("")) {
            dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("txtIdProducto")));
        }

        dto.getEntidad().setNombreProducto(request.getParameter("txtNombre"));
        dto.getEntidad().setDescripcionProducto(request.getParameter("txtDescripcion"));
        dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("txtPrecio")));
        dto.getEntidad().setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
        dto.getEntidad().setStockMinimo(Integer.parseInt(request.getParameter("txtStock")));
        dto.getEntidad().setClaveCategoria(Integer.parseInt(request.getParameter("txtClaveCategoria")));

        try {

            if (!request.getParameter("txtIdProducto").equals("")) {//CREAR
                dto.getEntidad().setImagenProducto(dao.read(dto).getEntidad().getImagenProducto());
                dao.Update(dto);
                request.setAttribute("mensaje", "Producto actualizado con exito.");
            } else {
                dto.getEntidad().setImagenProducto("imagenes/default.png");
                dao.create(dto);
                request.setAttribute("mensaje", "Producto almacenado con exito.");
            }

            listaDeProductos(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("productosForm.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("producto", dto);
            vista.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarImagen(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        System.out.println("Hola");
        System.out.println(request.getParameter("txtIdProducto"));
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("txtIdProducto")));
        try {
            dto = dao.read(dto);

            /* Receive file uploaded to the Servlet from the HTML5 form */
            Part foto = request.getPart("imagen");
            String nombreArchivoCompleto = foto.getSubmittedFileName();
            String extension = nombreArchivoCompleto.substring(nombreArchivoCompleto.lastIndexOf("."));
            String nombreArchivo = dto.getEntidad().getIdProducto() + extension;
            for (Part part : request.getParts()) {
                part.write("/Users/calebbolanos/Documents/GitHub/Proyecto-Final-WAD/ProyectoFinal/src/main/webapp/imagenes/productos/"+ nombreArchivo);
            }
            dto.getEntidad().setImagenProducto("imagenes/productos/"+ nombreArchivo);
            dao.Update(dto);
            request.setAttribute("mensaje", "Imagen actualizada con exito.");
            listaDeProductos(request, response);
        } catch (SQLException | IOException | ServletException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));

        try {
            dao.delete(dto);
            request.setAttribute("mensaje", "Producto borrado con exito.");
            listaDeProductos(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void mostrarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("datosProducto.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("producto", dto);
            vista.forward(request, response);

        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
