<%-- 
    Document   : productosForm
    Created on : 20 oct. 2021, 17:20:02
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

        <title>Formulario Usuario</title>
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
            <br/>

            <div class="card border-primary">
                <div class="card header">
                    <h1 class="text-center">Datos Usuario</h1>
                </div>
                <div class="card card-body">
                    <form method="post" action="UsuarioServlet?accion=guardar">
                        <div class="mb-3">
                            <label class="form-label">ID </label>
                            <input type="text" 
                                   name="txtIdUsuario" 
                                   id="txtIdUsuario" 
                                   placeholder="Id del Usuario"
                                   readonly value="<c:out value="${usuario.entidad.idUsuario}"/>"
                                   class="form-control" />
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Nombre: </label>
                            <input type="text" 
                                   name="txtNombre" 
                                   id="txtNombre" 
                                   placeholder="Nombre del Usuario"
                                   required
                                   maxlenght="50"
                                   value="<c:out value="${usuario.entidad.nombre}"/>"
                                   class="form-control" />
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Paterno: </label>
                            <input type="text" 
                                   name="txtPaterno" 
                                   id="txtPaterno" 
                                   placeholder="Apellido paterno"
                                   required
                                   maxlenght="100"
                                   value="<c:out value="${usuario.entidad.paterno}"/>"
                                   class="form-control" />
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Materno:  </label>
                            <input type="text" 
                                   name="txtMaterno"  
                                   id="txtMaterno" 
                                   placeholder="Apellido materno" 
                                   required
                                   maxlenght="100"
                                   value="<c:out value="${usuario.entidad.materno}"/>"
                                   class="form-control"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Nombre Usuario: </label>                               
                            <input type="text" 
                                   name="txtNombreUsuario" 
                                   id="txtNombreUsuario"
                                   placeholder="Nombre del Usuario" 
                                   required
                                   maxlenght="100"
                                   value="<c:out value="${usuario.entidad.nombreUsuario}"/>"
                                   class="form-control" />
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Clave: </label>                               
                            <input type="password" 
                                   id="txtClaveUsuario" 
                                   name="txtClaveUsuario" 
                                   placeholder="Clave" 
                                   required
                                   maxlenght="100"
                                   value="<c:out value="${usuario.entidad.claveUsuario}"/>"
                                   class="form-control"/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email:  </label>
                            <input type="text"
                                   name="txtEmail"                                 
                                   id="txtEmail"
                                   placeholder="Correo" 
                                   required
                                   maxlenght="100"
                                   value="<c:out value="${usuario.entidad.email}"/>"
                                   class="form-control" />
                        </div>

                        <button type="submit" class="btn btn-outline-primary">Guadar</button>     
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
