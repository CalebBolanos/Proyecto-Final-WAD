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
  claveCategoria INT NOT NULL,
  PRIMARY KEY (idProducto),
  FOREIGN KEY (claveCategoria) REFERENCES Categoria(idCategoria)
);

CREATE FUNCTION public.seleccionarcategoria(id integer) RETURNS TABLE(idcategoria integer, nombrecategoria character varying, descripcioncategoria character varying)
    LANGUAGE sql
    AS $$
    SELECT * FROM Categoria where idCategoria = id;
$$;

CREATE FUNCTION public.seleccionatodocategoria() RETURNS TABLE(idcategoria integer, nombrecategoria character varying, descripcioncategoria character varying)
    LANGUAGE sql
    AS $$
    SELECT * FROM Categoria;
$$;

CREATE PROCEDURE public.spactualizarcategoria(nombre character varying, descripcion character varying, id integer)
    LANGUAGE sql
    AS $$
    update  Categoria set nombreCategoria = nombre, descripcionCategoria = descripcion where idCategoria = id;
$$;


CREATE PROCEDURE public.speliminarcategoria(id integer)
    LANGUAGE sql
    AS $$
    delete from Categoria where idCategoria= id;
$$;

CREATE PROCEDURE public.spinsertarcategoria(nombre character varying, descripcion character varying)
    LANGUAGE sql
    AS $$
    insert into Categoria (nombreCategoria, descripcionCategoria) values (nombre, descripcion);
$$;

create or replace function seleccionaTodoProducto() returns Table(
            idproducto int,
            nombreproducto varchar,
            descripcionproducto varchar,
            precio numeric, 
            existencia int, 
            stockminimo int,
            imagenProducto varchar,
            clavecategoria int
        ) as $$
                select * from producto;
        $$ language sql
        


        
create or replace procedure spInsertarProducto(
            nombre varchar,
            descripcion varchar,
            precio float, 
            existencia int, 
            stockMinimo int,
            imagen varchar,
            claveCategoria int
    )
    language sql
    as $$
            insert into producto (nombreproducto, descripcionproducto, precio, existencia, stockminimo, imagenproducto, clavecategoria) 
                values (nombre, descripcion, precio, existencia, stockMinimo,imagen, claveCategoria);
    $$
    
create or replace procedure spActualizarProducto(
            in nombre varchar,
            in descripcion varchar,
            in precio numeric, 
            in existencia int, 
            in stock int,
            in imagen varchar,
            in categoria int,
            in id int
    )
    language sql
    as $$
            update Producto set nombreproducto=nombre, descripcionproducto=descripcion, precio=precio, existencia=existencia, stockminimo=stock, imagenproducto = imagen ,clavecategoria=categoria
                where idProducto=id;
    $$
    
    
    create or replace procedure spEliminarProducto(in id int)
    language sql
    as $$
            delete from producto where idproducto=id;
    $$
    
    
    create or replace function seleccionarProducto(in id int) 
    returns Table(
            idproducto int,
            nombreproducto varchar,
            descripcionproducto varchar,
            precio numeric, 
            existencia int, 
            stockMinimo int,
            imagenProducto varchar,
            claveCategoria int
    )
    language sql
    as $function$
                select * from producto where idproducto=id;
    $function$
    
    create or replace function public.iniciarSesion(nombre_usuario varchar, contrasena varchar) 
returns int 
language plpgsql
as $$
declare
	existe int;
begin
	existe := (select count(*) from public.usuario where nombreusuario = nombre_usuario and claveusuario = contrasena); 
	if(existe = 1) then
		return (select idusuario from public.usuario where nombreusuario = nombre_usuario and claveusuario = contrasena);
	else
		return 0;
	end if;
end;
$$;