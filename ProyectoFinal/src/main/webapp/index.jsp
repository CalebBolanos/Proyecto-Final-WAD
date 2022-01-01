<%-- 
    Document   : index
    Created on : 17 oct. 2021, 14:50:21
    Author     : calebbolanos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <title>Proyecto Final</title>
    </head>
    <body>
        <div id="app">
            <v-app>
                <v-app-bar app elevation="5" style=" background-color: rgba(255, 255, 255, 0.5) !important; backdrop-filter: saturate(180%) blur(20px) !important;">

                    <v-app-bar-title>ElectrodoShop</v-app-bar-title>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" text href="iniciarSesion.jsp">Iniciar Sesión</v-btn>
                    <v-btn color="secondary" text href="crearCuenta.jsp">Crea una Cuenta</v-btn>
                </v-app-bar>



                <v-main class="">
                    <section>
                        <v-parallax src="https://i.blogs.es/db3d82/store/1366_2000.jpg" height="600">
                            <v-layout column align-center justify-center class="white--text">
                                <h1 class="white--text mb-2 display-1 text-h1 bold " style="text-shadow: 0px 0px 17px rgba(0,0,0,0.46);">ElectrodoShop</h1>
                                <div class="white--text mb-3 text-h5" style="text-shadow: 0px 0px 17px rgba(0,0,0,0.46);">El lugar ideal para comprar tecnología en línea</div>
                            </v-layout>
                        </v-parallax>
                    </section>
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
                    <section>
                        <v-parallax src="https://tecnologiadelfuturo.es/wp-content/uploads/2020/06/gadgets-2020.jpeg" height="500">
                            <v-layout column align-center justify-center class="white--text">
                                <h1 class="text-center white--text mb-2 display-1 text-h1 bold " style="text-shadow: 0px 0px 17px rgba(0,0,0,0.46);">Todo lo que necesitas en un solo lugar</h1>
                            </v-layout>
                        </v-parallax>
                    </section>
                </v-main>
                <v-footer color="primary" padless>
                    <v-row justify="center" no-gutters align="center">
                        <v-col class="py-4 px-5 text-center white--text" cols="4">
                            <strong>Proyecto Final - Web Application Development</strong><br>
                            <strong>3CM12</strong>
                        </v-col>
                        <v-col class="py-4 white--text text-center" cols="4">
                            <strong>Integrantes del equipo:</strong><br>
                            <span>Bolaños Ramos Caleb Salomón</span><br>
                            <span>Hernández Oble Axel</span><br>
                        </v-col>
                        <v-col class="py-4 white--text text-center" cols="4">
                            <strong>Para administradores:</strong><br>
                            <v-btn text color="white">
                                Iniciar Sesión
                            </v-btn><br>
                        </v-col>

                        <v-col class="secondary py-4 text-center white--text" cols="12">
                            {{ new Date().getFullYear() }} — <strong>ESCOM IPN</strong>
                        </v-col>
                    </v-row>
                </v-footer>
            </v-app>

        </div>

        <script>
new Vue({
    el: '#app',
    vuetify: new Vuetify(),
})
        </script>
    </body>
</html>
