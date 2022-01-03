<%-- 
    Document   : iniciarSesion
    Created on : 1 ene. 2022, 19:09:59
    Author     : calebbolanos
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/@mdi/font@6.x/css/materialdesignicons.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/vue@2.x/dist/vue.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js"></script>
    </head>

    <body>
        <div id="app">
            <v-app>
                <v-main class="grey lighten-2">
                    <v-container fluid fill-height>
                        <v-layout align-center justify-center>
                            <v-flex xs12 sm8 md4>
                                <form method="POST" action="ProcesarSesionAdmin">
                                    <v-card class="elevation-0" rounded="lg">
                                        <v-card-title class="d-flex align-center justify-center py-7">

                                            <h2 class="text-2xl font-weight-semibold">
                                                ElectrodoShop
                                            </h2>
                                        </v-card-title>
                                        <v-card-text>
                                            <p class="title font-weight-semibold text--primary mb-2">
                                                Inicio de sesión de administrador
                                            </p>
                                            <p class="mb-2">
                                                Inicia sesión en tu cuenta para poder acceder al sistema
                                            </p>
                                        </v-card-text>
                                        <v-card-text>
                                            <v-text-field prepend-inner-icon="mdi-account-circle" name="usuario" label="Usuario" type="text" outlined ></v-text-field> 
                                            <v-text-field id="password" prepend-inner-icon="mdi-lock" name="contrasena" label="Contraseña" outlined :append-icon="ver ? 'mdi-eye' : 'mdi-eye-off'" :type="ver ? 'text' : 'password'" @click:append="ver = !ver"></v-text-field>
                                            <v-card-actions>
                                                <v-spacer></v-spacer>
                                                <v-btn type="submit" block color="primary">Iniciar Sesion</v-btn>
                                                <v-spacer></v-spacer>
                                            </v-card-actions>
                                        </v-card-text>
                                    </v-card>
                                </form>
                            </v-flex>
                        </v-layout>
                        <c:if test="${param.msg != null}">
                            <v-snackbar :value="true" :timeout="4000">
                                ${param.msg}

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
                        ver: false
                    }),
                vuetify: new Vuetify({

                }),
            })
        </script>
    </body>

</html>
