<%-- 
    Document   : listaProductos
    Created on : 20 oct. 2021, 17:12:23
    Author     : leoj_
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

        <title>Usuarios</title>
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
            <h1>Usuarios</h1>
            <br/>
            <br/>
            <div class="card border-primary">
                <div class="card-header text-center">
                    Usuarios
                </div>
                <div class="card-body">
                    <h4 class="card-title">
                        <a href="UsuarioServlet?accion=nuevo" class="btn btn-outline-success">Crear Usuario</a>
                    </h4>

                    <c:if test="${mensaje != null}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <strong>${mensaje}</strong>
                            <button class="btn-close" data-bs-dismiss="alert" aria-lbel="Close"></button>
                        </div>
                    </c:if>

                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Paterno</th>
                                <th>Materno</th>
                                <th>Email</th>
                                <th>Tipo Usuario</th>
                                <th>Eliminar</th>
                                <th>Actualizar</th>
                            </tr>
                        </thead>
                        <c:forEach var="dto" items="${listaDeUsuarios}">
                            <tbody>
                                <tr>
                                    <td>
                                        <a href="UsuarioServlet?accion=ver&id=<c:out value="${ dto.entidad.idUsuario }"/>" class="btn btn-outline-warning">
                                            <c:out value="${ dto.entidad.idUsuario }"/>
                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.nombre }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.paterno }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.materno }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.email }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.tipoUsuario }"/>
                                    </td>
                                    <td>
                                        <a href="UsuarioServlet?accion=eliminar&id=<c:out value="${ dto.entidad.idUsuario }"/>" class="btn btn-outline-danger">Eliminar</a>
                                    </td>
                                    <td>
                                        <a href="UsuarioServlet?accion=actualizar&id=<c:out value="${ dto.entidad.idUsuario }"/>" class="btn btn-outline-success">Actualizar</a>
                                    </td>
                                </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
