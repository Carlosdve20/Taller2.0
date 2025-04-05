
CREATE DATABASE Taller;



-- Crear la tabla Cliente
CREATE TABLE  Cliente (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(45),
    apellido VARCHAR(45),
    dni VARCHAR(45),
    telefono VARCHAR(45),
    correo VARCHAR(45),
    direccion VARCHAR(75)
);

-- Crear la tabla Vehiculo
CREATE TABLE Vehiculo (
    matricula VARCHAR(15) PRIMARY KEY,
    marca VARCHAR(25),
    modelo VARCHAR(45)
);

-- Crear la tabla Empleado
CREATE TABLE  Empleado (
    id INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(45),
    apellido VARCHAR(45),
    dni VARCHAR(20) UNIQUE,
    puesto VARCHAR(45),
    salario DECIMAL(10,2)
);

-- Crear la tabla Inventario
CREATE TABLE Inventario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(45),
    cantidad INT,
    precio DECIMAL(10,2)
);

-- Crear la tabla Proveedor
CREATE TABLE  Proveedor (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(45),
    cantidad INT,
    precio DECIMAL(10,2)
);

-- Crear la tabla Pedido
CREATE TABLE  Pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    producto_id INT NOT NULL,
    proveedor_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio DOUBLE NOT NULL,
    fecha_pedido DATE NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES Inventario(id),
    FOREIGN KEY (proveedor_id) REFERENCES Proveedor(id)
);

-- Crear la tabla Servicio
CREATE TABLE  Servicio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(45),
    descripcion TEXT,
    precio DECIMAL(10,2)
);

-- Crear la tabla Reparacion
CREATE TABLE  Reparacion (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_cliente INT,
    matricula VARCHAR(15),
    id_empleado INT,
    fechaEntrada DATE,
    fechaSalida DATE,
    precioTotal DECIMAL(10,2),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id),
    FOREIGN KEY (matricula) REFERENCES Vehiculo(matricula),
    FOREIGN KEY (id_empleado) REFERENCES Empleado(id)
);

-- Crear la tabla Reparacion_Servicio
CREATE TABLE  Reparacion_Servicio (
    id_reparacion INT NOT NULL,
    id_servicio INT NOT NULL,
    PRIMARY KEY (id_reparacion, id_servicio),
    FOREIGN KEY (id_reparacion) REFERENCES Reparacion(id),
    FOREIGN KEY (id_servicio) REFERENCES Servicio(id)
);

-- Crear la tabla Cita
CREATE TABLE  Cita (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cliente_id INT ,
    matricula VARCHAR(15),
    fecha DATETIME NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id),
    FOREIGN KEY (matricula) REFERENCES Vehiculo(matricula)
);