<%-- 
    Document   : graficaProductos
    Created on : 18 oct. 2021, 08:08:34
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

        <title>Administrador - Clientes</title>
    </head>
    <body>
        <div id="app">
            <v-app>
                <v-main class="grey lighten-2">
                    <v-container>
                        <v-row>
                            <v-col cols="12" class="">
                                <h2 class="mt-2">Grafica de Productos</h2>
                            </v-col>
                            <v-col cols="12" class="">
                                <v-card class="pa-3">
                                    <v-card-title class="justify-center">
                                        <v-img
                                                width="500"
                                                src="../graficaProductos.png"
                                                ></v-img>

                                    </v-card-title>
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

                    }),
                vuetify: new Vuetify(),
            })
        </script>
    </body>
</html>
