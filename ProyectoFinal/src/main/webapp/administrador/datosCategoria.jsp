<%-- 
    Document   : datosCategoria
    Created on : 2 ene. 2022, 13:57:54
    Author     : calebbolanos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ipn.mx.modelo.dto.AdministradorDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession();
    if (sesion.getAttribute("dtoAdministrador") == null) {
        response.sendRedirect("iniciarSesion.jsp");
        return;
    }
    AdministradorDTO dtoAdmin = (AdministradorDTO) sesion.getAttribute("dtoAdministrador");
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

        <title>Administrador - Categorias</title>
    </head>
    <body>
        <div id="app">
            <v-app>
                <v-app-bar app style=" background-color: rgba(255, 255, 255, 0.5) !important; backdrop-filter: saturate(180%) blur(20px) !important;">

                    <v-app-bar-title>ElectrodoShop</v-app-bar-title>
                    <v-spacer></v-spacer>
                    <v-btn color="secondary" text rounded href="inicio.jsp">Inicio</v-btn>
                    <v-btn color="primary" text rounded href="CategoriaServlet?accion=listaDeCategorias">Categorías</v-btn>
                    <v-btn color="secondary" text rounded href="ProveedorServlet?accion=listaDeProveedores">Proveedores</v-btn>
                    <v-btn color="secondary" text rounded href="ProductoServlet?accion=listaDeProductos">Productos</v-btn>
                    <v-btn color="secondary" text rounded href="ClienteServlet?accion=listaDeClientes">Clientes</v-btn>
                    <v-btn color="secondary" text rounded href="AdministradorServlet?accion=listaDeAdministradores">Administradores</v-btn>
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
                            <v-list-item href="AdministradorServlet?accion=listaDeAdministradores">
                                <v-list-item-title>Configuración de Administradores</v-list-item-title>
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
                                <h2 class="mt-2">Datos de Categoría</h2>
                            </v-col>
                            <v-col cols="12" class="">
                                <v-card class="pa-3 ">
                                    <v-card-text>
                                            <v-row>
                                                <v-col cols="12" class="mt-2">
                                                    <v-text-field readonly v-model="idCategoria" name="txtIdCategoria" label="ID de categoría" type="number" outlined ></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field readonly v-model="nombreCategoria" name="txtNombreCategoria" label="Nombre" type="text" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field readonly v-model="descripcionCategoria" name="txtDescripcionCategoria" label="Descripción" type="text" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                            </v-row>
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
            nombreUsuario: '<%=dtoAdmin.getEntidad().getNombre()%>',
            correo: '<%=dtoAdmin.getEntidad().getUsuario()%>',
            imagenUsuario: 'https://themeselection.com/demo/materio-vuetify-vuejs-admin-template-free/demo/img/1.e2938115.png',
            
            idCategoria: '<c:out value="${categoria.entidad.idCategoria}"/>',
            nombreCategoria: '<c:out value="${categoria.entidad.nombreCategoria}"/>',
            descripcionCategoria: '<c:out value="${categoria.entidad.descripcionCategoria}"/>',
            reglas: [
                v => !!v || "Este campo es requerido",
            ],
            reglasEdad: [
                v => !!v || "Este campo es requerido",
                v => (v && v >= 18) || "Debes de tener al menos 18 años",
            ],
            genero: '<c:out value="${categoria.entidad.idCategoria}"/>',
        }),
    vuetify: new Vuetify(),
})
        </script>
    </body>
</html>
