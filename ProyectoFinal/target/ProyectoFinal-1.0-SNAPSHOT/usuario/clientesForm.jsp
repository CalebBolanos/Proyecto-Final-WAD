<%-- 
    Document   : clientesForm
    Created on : 3 ene. 2022, 13:45:23
    Author     : calebbolanos
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ipn.mx.modelo.dto.UsuarioDTO"%>
<%@page import="com.ipn.mx.modelo.dto.ProveedorDTO"%>
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

        <title>Usuario - Historial</title>
    </head>
    <body>
        <div id="app">
            <v-app>
                <v-app-bar app style=" background-color: rgba(255, 255, 255, 0.5) !important; backdrop-filter: saturate(180%) blur(20px) !important;">

                    <v-app-bar-title>ElectrodoShop</v-app-bar-title>
                    <v-spacer></v-spacer>
                    <v-btn color="secondary" text rounded href="ControladorUsuario">Inicio</v-btn>
                    <v-btn color="secondary" text rounded href="ControladorUsuario?accion=historial">Historial de Compra</v-btn>
                    <v-btn color="primary" text rounded href="ControladorUsuario?accion=cuenta">Configuracion de cuenta</v-btn>
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
                                <h2 class="mt-2">Datos del Cliente</h2>
                            </v-col>
                            <v-col cols="12" class="">
                                <v-card class="pa-3 ">
                                    <v-card-text>
                                        <form method="POST" action="ControladorUsuario?accion=guardarCliente">
                                            <v-row>
                                                <v-col cols="12" class="mt-2">
                                                    <v-text-field readonly v-model="idUsuario" name="txtIdUsuario" label="ID de usuario" type="number" outlined ></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field v-model="nombre" name="txtNombre" label="Nombre" type="text" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field v-model="paterno" name="txtPaterno" label="Appelido Paterno" type="text" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field v-model="materno" name="txtMaterno" label="Apellido Materno" type="text" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field v-model="email" name="txtEmail" label="Correo electrónico" type="email" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field v-model="nombreUsr" name="txtNombreUsuario" label="Nombre de usuario" type="text"  outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field v-model="claveUsuario" name="txtClaveUsuario" label="Contraseña" type="password" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>
                                                <v-col cols="12">
                                                    <v-text-field v-model="direccion" name="txtDireccion" label="Dirección" type="text" outlined  required :rules="reglas"></v-text-field>
                                                </v-col>

                                                <v-col cols="12">
                                                    <v-btn type="submit" color="primary" class="me-3 mt-4">
                                                        Guardar
                                                    </v-btn>
                                                </v-col>
                                            </v-row>
                                        </form>
                                    </v-card-text>
                                </v-card>
                            </v-col>

                        </v-row>
                        <c:if test="${mensaje != null}">
                            <v-snackbar :value="true" :timeout="4000">
                                ${mensaje}

                                <template v-slot:action="{ attrs }">
                                    <v-btn
                                        color="primary"
                                        text
                                        v-bind="attrs"
                                        @click="snackbar = false"
                                        >
                                        Cerrar
                                    </v-btn>
                                </template>
                            </v-snackbar>
                        </c:if>
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
            idUsuario: '<c:out value="${cliente.entidad.idUsuario}"/>',
            nombre: '<c:out value="${cliente.entidad.nombre}"/>',
            paterno: '<c:out value="${cliente.entidad.paterno}"/>',
            materno: '<c:out value="${cliente.entidad.materno}"/>',
            email: '<c:out value="${cliente.entidad.email}"/>',
            nombreUsr: '<c:out value="${cliente.entidad.nombreUsuario}"/>',
            claveUsuario: '<c:out value="${cliente.entidad.claveUsuario}"/>',
            direccion: '<c:out value="${cliente.entidad.direccion}"/>',
            imagenUsuario: 'https://themeselection.com/demo/materio-vuetify-vuejs-admin-template-free/demo/img/1.e2938115.png',
        }),
    vuetify: new Vuetify(),
})
        </script>
    </body>
</html>