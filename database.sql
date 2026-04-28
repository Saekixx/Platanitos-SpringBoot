drop database if exists platanitos;
create database platanitos;
use platanitos;

create table Seccion(
	id int auto_increment primary key,
    nombre char(50) not null
);

create table Categoria(
	id int auto_increment primary key,
    nombre char(50) not null,
    descripcion char(255) not null,
    idSeccion int references Seccion(id)
);

create table Marca(
	id int auto_increment primary key,
	nombre char(50) not null,
    ruc char(20) not null,
    direccion char(100) not null
);

create table Talla(
	id int auto_increment primary key,
    nombre char(10) not null
);

create table Producto(
	id int auto_increment primary key,
    nombre char(80) not null,
    descripcion char(255) not null,
    precio decimal(5,2) not null,
    stock int default 0,
    isFavorite bool default false,
    isActive bool default true,
    idCategoria int references Categoria(id),
    idMarca int references Marca(id),
    idTalla int references Talla(id),
    imgURL varchar(255)
);

insert Seccion values (null, 'Damas'),(null, 'Caballeros'),(null, 'Niños'),(null, 'Accesorios');

insert Categoria values (null, 'Zapatillas', 'Calzado deportivo y urbano', 1),(null, 'Zapatos', 'Calzado formal y casual', 2)
						,(null, 'Sandalias', 'Calzado abierto para verano', 1),(null, 'Mochilas', 'Accesorios de transporte', 4);
                        
insert Marca values (null, 'Nike', '20100045671', 'Av. Larco 123, Miraflores'),(null, 'Adidas', '20556677882', 'Calle Begonias 456, San Isidro'),
					(null, 'Platanitos', '20123456789', 'Jr. de la Unión 800, Lima');

insert Talla values (null, '36'),(null, '38'),(null, '40'),(null, '42');

insert Producto values (null, 'Air Max Pulse', 'Zapatilla con amortiguación premium', 450.00, 25, true, true, 1, 1, 2, 'https://img.platanitos.com/p/1.jpg')
						,(null, 'Mocasín Cuero', 'Zapato de vestir elegante negro', 199.90, 15, false, true, 2, 3, 3, 'https://img.platanitos.com/p/2.jpg')
						,(null, 'Sandalia Casual', 'Sandalia ligera con correa ajustable', 79.00, 50, true, true, 3, 3, 1, 'https://img.platanitos.com/p/3.jpg')
						,(null, 'Mochila Urbana', 'Mochila resistente al agua', 120.00, 10, false, true, 4, 2, 1, 'https://img.platanitos.com/p/4.jpg');
                        

                        
