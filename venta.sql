create database Venta;
use Venta;

CREATE TABLE Venta
(
  idVenta serial primary key,
  montoFinal float(50) NOT NULL,
  fecha date NOT NULL
);

CREATE TABLE Usuario
(
  idUsuario serial,
  nombre varchar(50) NOT NULL,
  paterno varchar(50) NOT NULL,
  materno varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  nombreUsuario varchar(50) NOT NULL,
  claveUsuario varchar(50) NOT NULL,
  direccion varchar(200) NOT NULL,
  PRIMARY KEY (idUsuario)
);

CREATE TABLE Proveedor
(
  idProveedor serial,
  nombre varchar(50) NOT NULL,
  correo varchar(50) NOT NULL,
  clave varchar(50) NOT NULL,
  telefono varchar(50) NOT NULL,
  web varchar(500) NOT NULL,
  direccion varchar(200) NOT NULL,
  PRIMARY KEY (idProveedor)
);

CREATE TABLE Categoria
(
  idCategoria serial,
  nombreCategoria varchar(50) NOT NULL,
  descripcionCategoria varchar(100) NOT NULL,
  PRIMARY KEY (idCategoria)
);

CREATE TABLE Administrador
(
  idAdministrador serial,
  nombre varchar(50) NOT NULL,
  usuario varchar(50) NOT NULL,
  contrasena varchar(50) NOT NULL,
  PRIMARY KEY (idAdministrador)
);

CREATE TABLE Producto
(
  idProducto serial,
  nombreProducto varchar(50) NOT NULL,
  descripcionProducto varchar(100) NOT NULL,
  precio float(50) NOT NULL,
  existencia INT NOT NULL,
  stockMinimo INT NOT NULL,
  imagenProducto varchar(300) NOT NULL,
  idCategoria INT NOT NULL,
  PRIMARY KEY (idProducto),
  FOREIGN KEY (idCategoria) REFERENCES Categoria(idCategoria)
);