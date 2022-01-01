<%-- 
    Document   : datosProducto
    Created on : 20 oct. 2021, 17:16:50
    Author     : calebbolanos
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"></script>

        <title>Datos de Usuario</title>
    </head>
    <body>


        <div class="container">


           <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">
                        <img src="/imagenes/bootstrap-logo.svg" alt="" width="30" height="24" class="d-inline-block align-text-top">
                        Practica Final
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" aria-current="page" href="../index.jsp">Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="CategoriaServlet?accion=listaDeCategorias">Listado De Categorias</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="ProductoServlet?accion=listaDeProductos">Listado de Productos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="UsuarioServlet?accion=listaDeUsuarios">Listado de Usuarios</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="CerrarSesion">Cerra Sesión</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>

            <br/>

            <div class="col-12">

                <div class="card bg-light">
                    <div class="card-header">
                        <h3 class="text-center">Datos de Usuario</h3>
                    </div>
                    <div class="card-body">
                    <center>
                        <img src="./imagenes/bootstrap-logo.svg" alt="" width="50" height="50" class="d-inline-block align-text-top">
                        <ul class="list-group">
                            <li class="list-group-item">
                                
                                <c:out value="${usuario.entidad.idUsuario}" />
                            </li>
                            <li class="list-group-item">
                                <c:out value="${usuario.entidad.nombre}" />
                            </li>
                            <li class="list-group-item">
                                <c:out value="${usuario.entidad.paterno}" />
                            </li>
                            <li class="list-group-item">
                                <c:out value="${usuario.entidad.materno}" />
                            </li>
                            <li class="list-group-item">
                                <c:out value="${usuario.entidad.email}" />
                            </li>
                        </ul>
                    </center>
                </div>
                </div>
            </div>

        </div>
    </body>
</html>
