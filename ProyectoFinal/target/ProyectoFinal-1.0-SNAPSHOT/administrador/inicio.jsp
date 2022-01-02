<%-- 
    Document   : inicio
    Created on : 2 ene. 2022, 10:23:41
    Author     : calebbolanos
--%>

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

        <title>Administrador - Inicio</title>
    </head>
    <body>
        <div id="app">
            <v-app>
                <v-app-bar app elevation="5" style=" background-color: rgba(255, 255, 255, 0.5) !important; backdrop-filter: saturate(180%) blur(20px) !important;">

                    <v-app-bar-title>ElectrodoShop</v-app-bar-title>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" text rounded href="#">Inicio</v-btn>
                    <v-btn color="secondary" text rounded href="crearCuenta.jsp">Categorías</v-btn>
                    <v-btn color="secondary" text rounded href="crearCuenta.jsp">Proveedores</v-btn>
                    <v-btn color="secondary" text rounded href="crearCuenta.jsp">Productos</v-btn>
                    <v-btn color="secondary" text rounded href="crearCuenta.jsp">Clientes</v-btn>
                    <v-btn color="secondary" text rounded href="crearCuenta.jsp">Administradores</v-btn>
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
                            <v-list-item href="configuracionCuenta">
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
                            <v-col cols="12" class="py-10 ">
                                <h1 class="text-center">¿Qué es ElectrodoShop?</h1>
                                <p class="text-center"> ElectrodoShop es una tienda en línea de electrodomesticos en la cual 
                                    los clientes pueden disponer de cientos de articulos distribuidos por distintos proveedores dentro de nuestra plataforma.</p>
                            </v-col>
                            <v-col cols="12" class="">
                                <h1 class="text-center">ElectrodoShop cuenta con:</h1>
                            </v-col>
                            <v-col cols="12" sm="6">
                                <v-card >
                                    <v-card-title class="justify-center">
                                        <v-avatar color="primary" size="92">
                                            <v-icon dark size="48">
                                                mdi-cart-plus
                                            </v-icon>
                                        </v-avatar>
                                    </v-card-title>

                                    <v-card-title class="text-h5 justify-center pt-0">
                                        Proveedores
                                    </v-card-title>

                                    <v-card-subtitle>Vende tus productos a través de la plataforma.</v-card-subtitle>

                                    <v-card-actions class="justify-center">
                                        <v-btn text color="primary">
                                            Crear Cuenta
                                        </v-btn>
                                        <v-btn text>
                                            Iniciar Sesión
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-col>
                            <v-col cols="12" sm="6">
                                <v-card >
                                    <v-card-title class="justify-center">
                                        <v-avatar color="primary" size="92">
                                            <v-icon dark size="48">
                                                mdi-account-group
                                            </v-icon>
                                        </v-avatar>
                                    </v-card-title>

                                    <v-card-title class="text-h5 justify-center pt-0">
                                        Clientes
                                    </v-card-title>

                                    <v-card-subtitle>Compra y elige entre cientos de productos electrónicos disponibles.</v-card-subtitle>

                                    <v-card-actions class="justify-center">
                                        <v-btn text color="primary">
                                            Crear Cuenta
                                        </v-btn>
                                        <v-btn text>
                                            Iniciar Sesión
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-col>
                            <v-col cols="12" class="">
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
        }),
    vuetify: new Vuetify(),
})
        </script>
    </body>
</html>
