<%-- 
    Document   : crearCuenta
    Created on : 1 ene. 2022, 19:10:50
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
                                <form method="POST" action="GuardarProveedor">
                                    <v-card class="elevation-0" rounded="lg">
                                        <v-card-title class="d-flex align-center justify-center py-7">
                                            <h2 class="text-2xl font-weight-semibold">
                                                ElectrodoShop
                                            </h2>
                                        </v-card-title>
                                        <v-card-text>
                                            <p class="title font-weight-semibold text--primary mb-2">
                                                Crear cuenta de proveedor
                                            </p>
                                            <p class="mb-2">
                                                Llena el siguiente formulario para ingresar a la plataforma
                                            </p>
                                        </v-card-text>
                                        <v-card-text>
                                            <v-text-field v-model="nombre" name="txtNombre" label="Nombre" type="text" outlined  required :rules="reglas"></v-text-field>
                                            <v-text-field v-model="correoTxt" name="txtCorreo" label="Correo" type="email" outlined  required :rules="reglas"></v-text-field>
                                            <v-text-field v-model="clave" name="txtClave" label="Escribe tu contraseña" outlined :append-icon="ver ? 'mdi-eye' : 'mdi-eye-off'" :type="ver ? 'text' : 'password'" @click:append="ver = !ver"  required :rules="reglas"
                                                          /></v-text-field>
                                            <v-text-field v-model="contrasena2" name="contrasena2" label="Vuelve a escribir tu contraseña" outlined :append-icon="ver2 ? 'mdi-eye' : 'mdi-eye-off'" :type="ver ? 'text' : 'password'" @click:append="ver = !ver" required
                                                          :rules="[clave == contrasena2 || 'Las contraseñas deben ser iguales']" /></v-text-field>
                                            <v-text-field v-model="telefono" name="txtTelefono" label="Telefono" type="text" outlined  required :rules="reglas"></v-text-field>
                                            <v-text-field v-model="web" name="txtWeb" label="Página web" type="text" outlined  required :rules="reglas"></v-text-field>
                                            <v-text-field v-model="direccion" name="txtDireccion" label="Direccion" type="text" outlined  required :rules="reglas"></v-text-field>
                                            <v-card-actions>
                                                <v-spacer></v-spacer>
                                                <v-btn type="submit" block color="primary">Crear cuenta</v-btn>
                                                <v-spacer></v-spacer>
                                            </v-card-actions>
                                            <span class="d-flex align-center justify-center flex-wrap">
                                                <span class="me-2">
                                                    ¿Ya tienes una cuenta?
                                                </span>
                                                <a href="iniciarSesion.jsp" >Iniciar Sesión</a>
                                            </span>
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
                        ver: false,
                        ver2: false,
                        nombre: '',
                        correoTxt: '',
                        clave: '',
                        contrasena2: '',
                        telefono: '',
                        web: '',
                        direccion: '',
                        reglas: [
                            v => !!v || "Este campo es requerido",
                        ],

                    }),
                vuetify: new Vuetify({
                }),
            })
        </script>
    </body>

</html>
