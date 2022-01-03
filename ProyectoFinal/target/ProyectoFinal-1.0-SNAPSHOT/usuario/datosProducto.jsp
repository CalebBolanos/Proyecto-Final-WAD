<%-- 
    Document   : datosProducto
    Created on : 3 ene. 2022, 12:59:13
    Author     : calebbolanos
--%>
<%@page import="com.ipn.mx.modelo.dto.UsuarioDTO"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("dtoUsuario") == null) {
        response.sendRedirect("../iniciarSesion.jsp");
        return;
    }
    UsuarioDTO dtoUsuario = (UsuarioDTO) sesion.getAttribute("dtoUsuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/@mdi/font@6.x/css/materialdesignicons.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/vue@2.x/dist/vue.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js" ></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">

        <title>Administrador - Productos</title>
    </head>
    <body>
        <div id="app">
            <v-app>
                <v-app-bar app style=" background-color: rgba(255, 255, 255, 0.5) !important; backdrop-filter: saturate(180%) blur(20px) !important;">

                    <v-app-bar-title>ElectrodoShop</v-app-bar-title>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" text rounded href="ControladorUsuario">Inicio</v-btn>
                    <v-btn color="secondary" text rounded href="ControladorUsuario?accion=historial">Historial de Compra</v-btn>
                    <v-btn color="secondary" text rounded href="ControladorUsuario?accion=cuenta">Configuracion de cuenta</v-btn>
                    <v-spacer></v-spacer>
                    <v-menu left bottom offset-y rounded="lg">
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn icon v-bind="attrs" v-on="on">
                                <v-avatar color="accent darken-1 shrink" size="32">
                                    <v-img
                                        alt=""
                                        :src="imagenUsuario"
                                        ></v-img>
                                </v-avatar>
                            </v-btn>
                        </template>

                        <v-list>
                            <v-list-item>
                                <v-list-item-content>
                                    <v-list-item-title class="text-h6">
                                        {{nombreUsuario}}
                                    </v-list-item-title>
                                    <v-list-item-subtitle>
                                        {{correo}}
                                    </v-list-item-subtitle>
                                </v-list-item-content>
                            </v-list-item>

                            <v-divider></v-divider>
                            <v-list-item href="ControladorUsuario?accion=cuenta">
                                <v-list-item-title>Configuración de cuenta</v-list-item-title>
                            </v-list-item>
                            <v-list-item href="../CerrarSesion">
                                <v-list-item-title>Cerrar Sesión</v-list-item-title>
                            </v-list-item>
                        </v-list>
                    </v-menu>

                </v-app-bar>

                <v-main class="grey lighten-2">
                    <v-container>
                        <v-row>
                            <v-col cols="12" class="">
                                <h2 class="mt-2">Datos del Producto</h2>
                            </v-col>
                            <v-col cols="12" class="">
                                <v-card class="pa-3 ">
                                    <v-card-title class="justify-center">
                                        <v-avatar rounded size="250" class="me-6">
                                            <v-img :src="imagenProducto"></v-img>
                                        </v-avatar>
                                    </v-card-title>
                                    <v-card-text>
                                        <form method="POST" action="ControladorUsuario?accion=comprar">
                                            <v-row>
                                                <v-col cols="12" class="mt-2">
                                                    <v-text-field readonly  v-model="idProducto" name="id" label="ID de producto" type="number" outlined ></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field readonly v-model="nombreProducto" name="txtNombre" label="Nombre del producto" type="text" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field readonly v-model="descripcionProducto" name="txtDescripcion" label="Descripcion del producto" type="text" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field readonly v-model="precio" name="txtPrecio" label="Precio del producto" type="number" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field readonly v-model="existencia" name="txtExistencia" label="Cantidad disponible del producto" type="number" step="1" min="1" max="100" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field readonly v-model="stockMinimo" name="txtStock" label="Cantidad minima en stock del producto" type="number" step="1" min="10" max="100" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field readonly v-model="claveCategoria" name="txtClaveCategoria" label="Clave de la categoria" type="number" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-btn type="submit" color="primary" class="me-3 mt-4">
                                                        Comprar
                                                    </v-btn>
                                                    <v-btn color="primary" outlined class="mt-4" href="ControladorUsuario">
                                                        Regresar
                                                    </v-btn>
                                                </v-col>
                                            </v-row>
                                        </form>
                                    </v-card-text>
                                </v-card>
                            </v-col>
                        </v-row>

                    </v-container>
                </v-main>

            </v-app>

        </div>

        <script>
new Vue({
    el: '#app',
    data: () => ({
            drawer: false,
            group: 0,
            nombreUsuario: '<%=dtoUsuario.getEntidad().getNombre()%>',
            correo: '<%=dtoUsuario.getEntidad().getEmail()%>',
            imagenUsuario: 'https://themeselection.com/demo/materio-vuetify-vuejs-admin-template-free/demo/img/1.e2938115.png',

            idProducto: '<c:out value="${producto.entidad.idProducto}"/>',
            nombreProducto: '<c:out value="${producto.entidad.nombreProducto}"/>',
            descripcionProducto: '<c:out value="${producto.entidad.descripcionProducto}"/>',
            precio: '<c:out value="${producto.entidad.precio}"/>',
            existencia: '<c:out value="${producto.entidad.existencia}"/>',
            stockMinimo: '<c:out value="${producto.entidad.stockMinimo}"/>',
            imagenProducto: '../<c:out value="${producto.entidad.imagenProducto}"/>',
            claveCategoria: '<c:out value="${producto.entidad.claveCategoria}"/>',
            reglas: [
                v => !!v || "Este campo es requerido",
            ],
            reglasEdad: [
                v => !!v || "Este campo es requerido",
                v => (v && v >= 18) || "Debes de tener al menos 18 años",
            ],
        }),
    vuetify: new Vuetify(),
})
        </script>
    </body>
</html>
