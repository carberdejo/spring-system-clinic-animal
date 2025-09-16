DROP DATABASE IF EXISTS ClinicAnimal;
create database ClinicAnimal;
use ClinicAnimal;
 
Create Table Areas(
Codigo_Area BIGINT auto_increment primary key,
Nombre_Area varchar(100),
Descripcion varchar(100)
);
 
Create Table Roles(
Codigo_Rol BIGINT auto_increment primary key,
Nombre_rol varchar(100),
Descripcion varchar(100),
Acceso_Web boolean
);

 Create Table Cliente(
id_cliente BIGINT auto_increment primary key,
nombre varchar(100),
apellido varchar(100),
dni varchar(100),
telefono varchar(100),
direccion varchar(100),
email varchar(100)
);
 
Create Table Servicios(
cod_servicio BIGINT auto_increment primary key,
nombre varchar(100),
descripcion varchar(100),
duracion int,
precio decimal(10,2),
activo boolean,
Codigo_Area BIGINT references Areas(Codigo_Area)
 );
 
 
Create Table Personal(
id_personal BIGINT auto_increment primary key,
nombre_personal varchar(100),
apellido_personal varchar(100),
contraseña varchar(40),
edad int,
dni varchar(8),
email varchar(100),
telefono varchar(100),
fecha_registro date,
Estado varchar(100),
Codigo_Area BIGINT references Areas(Codigo_Area),
Codigo_Rol BIGINT references Roles(Codigo_Rol)
);
Create Table Mascota(
id_mascota BIGINT auto_increment primary key,
nom_mascota varchar(100),
raza varchar(100),
especie varchar(100),
edad int,
sexo varchar(100),
id_cliente BIGINT references Cliente(id_cliente)
);
 
Create Table Cita(
id_cita BIGINT auto_increment primary key,
fecha_hora datetime,
estado varchar(100),
id_mascota BIGINT references Mascota(id_mascota),
id_cliente BIGINT references Cliente(id_cliente),
id_veterinario BIGINT references Personal(id_personal),
id_recepcionista BIGINT references Personal(id_personal),
id_area BIGINT references Areas(Codigo_Area)
);
Create Table Receta(
Codigo_Receta BIGINT auto_increment primary key,
cantidad int,
indicaciones varchar(100),
medicamentos varchar(100),
id_cita BIGINT references Cita(id_cita)
);

Create Table Cita_Servicio(
id_cita_Serv BIGINT auto_increment primary key,
cantidad int,
precio_Base decimal(10,2),
sub_Total decimal(10,2),
id_cita BIGINT references Cita(id_cita),
cod_servicio BIGINT references Servicios(cod_servicio)
);

 Create Table Horario(
id_horario BIGINT auto_increment primary key,
fecha date,
hora_entrada time,
hora_salida time,
estado varchar(50),
id_rol BIGINT references Roles(Codigo_Rol)
);

Create Table Asistencia(
id_asistencia BIGINT auto_increment primary key,
fecha date,
hora_entrada time,
hora_salida time,
estado varchar(50),
id_personal BIGINT references Personal(id_personal)
);

INSERT INTO Areas (Nombre_Area, Descripcion) VALUES
('Higiene&Belleza', 'Baño, corte, estética y limpieza'),
('Medicina General', 'Consultas, controles, vacunación'),
('Laboratorio', 'Análisis clínicos y pruebas');

INSERT INTO Roles (Nombre_rol, Descripcion,Acceso_Web) VALUES
('Admin', 'Gestión del sistema y configuración general',1),
('Recepcionista', 'Atención al cliente y gestión de agenda',1),
('Veterinario General', 'Atiende consultas y procedimientos de Medicina General',1),
('Auxiliar Veterinario', 'Apoyo en consultas/procedimientos de Medicina General',0),
('Técnico de Laboratorio', 'Toma y procesamiento de muestras en Laboratorio',1),
('Groomer/Estilista Canino', 'Servicios de Higiene&Belleza (baño, corte, estética)',1);


INSERT INTO Cliente (nombre, apellido, dni, telefono, direccion, email) VALUES
('Carlos','Pérez','12345678','987654321','Calle Falsa 123','carlos@gmail.com'),
('Ana','Gómez','87654321','987654322','Av. Siempre Viva 456','ana@gmail.com'),
('Luis','Ramírez','11223344','987654323','Jr. Los Olivos 789','luis@gmail.com'),
('María','Fernández','44332211','987654324','Mz. A Lt. 10','maria@gmail.com'),
('Jorge','Soto','55667788','987654325','Calle Luna 321','jorge@gmail.com'),
('Elena','Vega','99887766','987654326','Av. Sol 654','elena@gmail.com'),
('Pedro','Díaz','66778899','987654327','Jr. Mar 111','pedro@gmail.com'),
('Lucía','Rojas','77889966','987654328','Calle Río 222','lucia@gmail.com'),
('Sofía','Castro','13572468','987654329','Av. Bosque 333','sofia@gmail.com'),
('Andrés','Paredes','24681357','987654330','Jr. Valle 444','andres@gmail.com');

INSERT INTO Servicios (nombre, descripcion, duracion, precio, Codigo_Area, activo) VALUES
('Consulta básica','Evaluación general',30,50.00,1,0),
('Vacunación','Aplicación de vacunas',20,40.00,1,1),
('Chequeo cachorro','Control de desarrollo',25,45.00,2,1),
('Cirugía menor','Procedimiento ambulatorio',60,250.00,2,1),
('Atención de urgencia','Estabilización inmediata',45,120.00,2,0),
('Tratamiento dermatológico','Tratamiento tópico',30,80.00,2,0),
('Limpieza dental','Profilaxis dental',50,150.00,1,1),
('Perfil hematológico','Exámenes de sangre',40,90.00,2,1),
('Radiografía','Placa radiográfica',20,70.00,3,1),
('Rehabilitación básica','Sesión de fisioterapia',45,65.00,2,1);

INSERT INTO Personal (nombre_personal, apellido_personal,contraseña, edad, dni, email, telefono, fecha_registro, Estado, Codigo_Area, Codigo_Rol) VALUES
('Marcos','Ramos','Apassword12',35,'80000001','admin@gmail.com','900111111','2025-01-10','DISPONIBLE',2,1), 
('Ana','Torres','iAmHappy091',32,'80000002','atorres@gmail.com','900111112','2025-01-12','DISPONIBLE',2,2),     
('Rosa','López','RoLopez0001',28,'80000003','rlopez@gmail.com','900111113','2025-01-15','DISPONIBLE',2,3),       
('Luis','García','1NewContraseña',27,'80000004','lgarcia@gmail.com','900111114','2025-01-18','DISPONIBLE',2,3),   
('Pedro','Díaz',null,30,'80000005','pdiaz@gmail.com','900111115','2025-01-20','DISPONIBLE',2,4),      
('Carla','Medina','1ContraseñaNormal',34,'80000006','cmedina@gmail.com','900111116','2025-01-22','DISPONIBLE',2,3),   
('José','Pérez','Contraseña192',33,'80000007','jperez@gmail.com','900111117','2025-01-24','DISPONIBLE',3,5),       
('María','Salas','123456789',29,'80000008','msalas@gmail.com','900111118','2025-01-26','DISPONIBLE',1,6),     
('Jorge','Ruiz','12Hola34xd',31,'80000009','jruiz@gmail.com','900111119','2025-01-28','DISPONIBLE',3,5),      
('Elena','Vega','0000000001',36,'80000010','evega@gmail.com','900111120','2025-01-30','DISPONIBLE',1,6),
('Juan','Flores','YouAreGoingDown1',22,'34567890','IamAFellowMan@gmail.com','999888777','2025-02-19','DESCANSO',2,3),
('Carlos','Puerta','Contraseña123',70,'11221122','Program12@gmail.com','198123576','2025-06-19','DESCANSO',2,3),
('Bea','Ferrnanda','IamDaBest2',40,'11111220','ShineBright@gmail.com','999202020','2025-02-19','OCUPADO',2,3);

INSERT INTO Mascota (nom_mascota, raza, especie, edad, sexo, id_cliente) VALUES
('Firulais','Labrador','Perro',4,'Macho',1),
('Mishi','Siames','Gato',3,'Hembra',2),
('Rocky','Bulldog','Perro',5,'Macho',3),
('Luna','Persa','Gato',2,'Hembra',4),
('Toby','Poodle','Perro',6,'Macho',5),
('Nala','Golden','Perro',1,'Hembra',6),
('Kira','Mestizo','Gato',4,'Hembra',7),
('Max','Beagle','Perro',3,'Macho',8),
('Simba','Bengalí','Gato',2,'Macho',9),
('Coco','Shih Tzu','Perro',7,'Hembra',10),
('Perla','Coker','Perro',7,'Hembra',1);

INSERT INTO Cita (fecha_hora, estado, id_mascota, id_veterinario, id_recepcionista,id_cliente,id_area) VALUES
('2025-08-01','PROGRAMADA',1,6,2,1,1),
('2025-08-02','PROGRAMADA',2,6,2,2,1),
('2025-08-03','TERMINADA',3,10,2,3,1),
('2025-08-04','TERMINADA',4,10,2,4,1),
('2025-08-05','CANCELADO',5,3,2,5,2),
('2025-08-06','PROGRAMADA',6,5,2,10,2),
('2025-08-07','PROGRAMADA',7,3,2,6,2),
('2025-08-08','TERMINADA',8,5,2,7,2),
('2025-08-09','TERMINADA',9,9,2,8,3),
('2025-08-10','PROGRAMADA',10,2,2,3,2),
('2025-07-20','EN_PROGRESO',12,13,2,4,2);

	
INSERT INTO Receta (cantidad, indicaciones, medicamentos, id_cita) VALUES
(2,'Reposo y agua','Amoxicilina 250mg',1),
(3,'Dieta blanda','Antiinflamatorio 100mg',2),
(5,'Limpieza de herida','Antiséptico tópico',3),
(6,'Evitar exposición solar','Pomada dermatológica',4),
(4,'Control en 7 días','Probióticos',5),
(2,'Hidratación','Antiparasitario',6),
(6,'Ejercicio moderado','Analgésico 50mg',7),
(7,'Cepillado dental','Enjuague oral',8),
(5,'Curación diaria','Antibiótico tópico',9),
(5,'Vendaje compresivo','Antiinflamatorio 75mg',10),
(4,'Chequeo constante','Pastilla para dolor',11);


INSERT INTO Cita_Servicio (cantidad, precio_Base, sub_Total, id_cita, cod_servicio) VALUES
(1, 50.00,  50.00, 1, 1), 
(1, 40.00,  40.00, 2, 2),   
(2, 45.00,  90.00, 3, 3),  
(1,250.00, 250.00, 4, 4),   
(1,120.00, 120.00, 5, 5),   
(1, 80.00,  80.00, 6, 6),   
(1,150.00, 150.00, 7, 7),   
(1, 90.00,  90.00, 8, 8),   
(2, 70.00, 140.00, 9, 9),   
(1, 65.00,  65.00,10,10);  

INSERT INTO Horario (fecha, hora_entrada, hora_salida, estado, id_rol) VALUES
('2025-08-11','08:00:00','16:00:00','DESHABILITADO',1),
('2025-08-11','09:00:00','17:00:00','DESHABILITADO',2),
('2025-08-11','08:00:00','14:00:00','DESHABILITADO',3),
('2025-08-12','08:00:00','16:00:00','DISPONIBLE',3),
('2025-08-12','10:00:00','18:00:00','DESHABILITADO',2),
('2025-08-12','09:00:00','17:00:00','DISPONIBLE',4),
('2025-08-13','08:00:00','16:00:00','DISPONIBLE',5),
('2025-08-13','09:00:00','17:00:00','DISPONIBLE',6),
('2025-08-14','08:00:00','16:00:00','DISPONIBLE',2),
('2025-08-14','10:00:00','18:00:00','DISPONIBLE',1);

INSERT INTO Asistencia (fecha, hora_entrada, hora_salida, estado, id_personal) VALUES
('2025-08-11','08:02:00','16:01:00','PRESENTE',1),
('2025-08-11','09:00:00','17:05:00','PRESENTE',2),
('2025-08-11','08:10:00','14:02:00','PRESENTE',3),
('2025-08-12','08:00:00','16:00:00','PRESENTE',4),
('2025-08-12','10:05:00','18:00:00','PRESENTE',5),
('2025-08-12','09:03:00','17:01:00','PRESENTE',6),
('2025-08-13','08:00:00','16:10:00','PRESENTE',7),
('2025-08-13','09:00:00','17:00:00','PRESENTE',8),
('2025-08-14','08:00:00','16:00:00','PRESENTE',9),
('2025-08-14','10:00:00','18:00:00','PRESENTE',10);


$$ DELIMITER $$

DROP PROCEDURE IF EXISTS Reporte01$$

CREATE PROCEDURE Reporte01(IN clienteId INT)
BEGIN
    IF clienteId IS NULL THEN
        -- Mostrar todos los clientes con sus mascotas
        SELECT 
            c.id_cliente,
            c.nombre,
            c.telefono,
            c.email,
            m.nom_mascota,
            m.especie,
            m.raza,
            m.edad,
            m.sexo
        FROM Cliente c
        INNER JOIN Mascota m ON c.id_cliente = m.id_cliente
        ORDER BY c.nombre;
    ELSE
        -- Mostrar solo el cliente indicado
        SELECT 
            c.id_cliente,
            c.nombre,
            c.telefono,
            c.email,
            m.nom_mascota,
            m.especie,
            m.raza,
            m.edad,
            m.sexo
        FROM Cliente c
        INNER JOIN Mascota m ON c.id_cliente = m.id_cliente
        WHERE c.id_cliente = clienteId
        ORDER BY c.nombre;
    END IF;
END$$

DELIMITER ;

CALL Reporte01(null);


DELIMITER $$

DROP PROCEDURE IF EXISTS ReporteServicios$$

CREATE PROCEDURE ReporteServicios(IN clienteId BIGINT)
BEGIN
    IF clienteId IS NULL THEN
        -- Reporte de TODOS los servicios brindados
        SELECT 
            c.id_cita,
            c.fecha_hora AS Fecha,
            cl.id_cliente,
            cl.nombre AS Cliente,
            cl.apellido AS Apellido_Cliente,
            m.nom_mascota AS Mascota,
            s.nombre AS Servicio,
            cs.cantidad,
            cs.precio_Base,
            cs.sub_Total,
            a.Nombre_Area AS Area,
            CONCAT(p.nombre_personal, ' ', p.apellido_personal) AS Veterinario,
            c.estado AS Estado_Cita
        FROM Cita c
        INNER JOIN Mascota m ON c.id_mascota = m.id_mascota
        INNER JOIN Cliente cl ON m.id_cliente = cl.id_cliente
        INNER JOIN Cita_Servicio cs ON c.id_cita = cs.id_cita
        INNER JOIN Servicios s ON cs.cod_servicio = s.cod_servicio
        INNER JOIN Areas a ON s.Codigo_Area = a.Codigo_Area
        INNER JOIN Personal p ON c.id_veterinario = p.id_personal
        ORDER BY c.fecha_hora;
    ELSE
        -- Reporte SOLO de un cliente específico
        SELECT 
            c.id_cita,
            c.fecha_hora AS Fecha,
            cl.id_cliente,
            cl.nombre AS Cliente,
            cl.apellido AS Apellido_Cliente,
            m.nom_mascota AS Mascota,
            s.nombre AS Servicio,
            cs.cantidad,
            cs.precio_Base,
            cs.sub_Total,
            a.Nombre_Area AS Area,
            CONCAT(p.nombre_personal, ' ', p.apellido_personal) AS Veterinario,
            c.estado AS Estado_Cita
        FROM Cita c
        INNER JOIN Mascota m ON c.id_mascota = m.id_mascota
        INNER JOIN Cliente cl ON m.id_cliente = cl.id_cliente
        INNER JOIN Cita_Servicio cs ON c.id_cita = cs.id_cita
        INNER JOIN Servicios s ON cs.cod_servicio = s.cod_servicio
        INNER JOIN Areas a ON s.Codigo_Area = a.Codigo_Area
        INNER JOIN Personal p ON c.id_veterinario = p.id_personal
        WHERE cl.id_cliente = clienteId
        ORDER BY c.fecha_hora;
    END IF;
END$$

DELIMITER 

CALL ReporteServicios(NULL);

CALL ReporteServicios(3);

DELIMITER $$

DROP PROCEDURE IF EXISTS ReporteServiciosMasSolicitados$$

CREATE PROCEDURE ReporteServiciosMasSolicitados(IN limite INT)
BEGIN
    IF limite IS NULL OR limite <= 0 THEN
        -- Mostrar todos los servicios ordenados por cantidad total (más a menos)
        SELECT 
            s.cod_servicio,
            s.nombre AS Servicio,
            a.Nombre_Area AS Area,
            SUM(cs.cantidad) AS Total_Solicitudes,
            SUM(cs.sub_Total) AS Total_Facturado
        FROM Cita_Servicio cs
        INNER JOIN Servicios s ON cs.cod_servicio = s.cod_servicio
        INNER JOIN Areas a ON s.Codigo_Area = a.Codigo_Area
        GROUP BY s.cod_servicio, s.nombre, a.Nombre_Area
        ORDER BY Total_Solicitudes DESC;
    ELSE
        -- Mostrar solo el TOP N (ejemplo: top 5 servicios más solicitados)
        SELECT 
            s.cod_servicio,
            s.nombre AS Servicio,
            a.Nombre_Area AS Area,
            SUM(cs.cantidad) AS Total_Solicitudes,
            SUM(cs.sub_Total) AS Total_Facturado
        FROM Cita_Servicio cs
        INNER JOIN Servicios s ON cs.cod_servicio = s.cod_servicio
        INNER JOIN Areas a ON s.Codigo_Area = a.Codigo_Area
        GROUP BY s.cod_servicio, s.nombre, a.Nombre_Area
        ORDER BY Total_Solicitudes DESC
        LIMIT limite;
    END IF;
END$$

DELIMITER ;

CALL ReporteServiciosMasSolicitados(NULL);

CALL ReporteServiciosMasSolicitados(3);


DROP PROCEDURE IF EXISTS Historial$$
DELIMITER $$

CREATE PROCEDURE Historial(IN p_id_mascota BIGINT)
BEGIN
    SELECT
        m.id_mascota,
        m.nom_mascota,
        m.especie,
        m.raza,
        m.edad,
        m.sexo,
        cl.id_cliente,
        cl.nombre,
        cl.dni,
        COUNT(c.id_cita) AS total_citas,
        MAX(c.fecha_hora) AS ultima_cita,
        MIN(c.fecha_hora) AS primera_cita
    FROM Mascota m
    INNER JOIN Cita c ON m.id_mascota = c.id_mascota
    INNER JOIN Cliente cl ON cl.id_cliente = m.id_cliente
    WHERE m.id_mascota = p_id_mascota
    GROUP BY m.id_mascota, m.nom_mascota, m.especie, m.raza, m.edad, m.sexo, cl.nombre, cl.dni;
END$$

DELIMITER ;

CALL Historial(2);

Select * from Roles
