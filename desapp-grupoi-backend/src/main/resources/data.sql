
-- Creacion de usuarios: (usuario, saldo, cuenta)
-- Usuarios
insert into usuario (id, nombre, apellido, email, contrasenia, fecha_nac) values
(1, 'Emmanuel', 'Pericon','emma@email.com','12345678',TO_DATE('27/11/1992', 'DD/MM/YYYY')),
(2, 'Brian', 'Pericon','brian@email.com','12345678',TO_DATE('27/11/1992', 'DD/MM/YYYY')),
(3, 'Alejandro', 'Rossi','ale@email.com','12345678',TO_DATE('10/08/1992', 'DD/MM/YYYY')),
(4, 'Homero', 'Simpson','homero@email.com','12345678',TO_DATE('12/05/1956', 'DD/MM/YYYY'));
-- Saldos
insert into dinero (id, aux, monto) values
(1,0,0),(2,0,0),(3,0,0),(4,0,0);
-- Cuentas
insert into cuenta (situacion_deuda, saldo_id, usuario_id) values
('NORMAL', 1, 1),('NORMAL', 2, 2),('NORMAL', 3, 3),('NORMAL', 4, 4);

-- Creacion de eventos: (saldo, modalidad, template, evento)
-- Saldo
insert into dinero (id, aux, monto) values (5,0,0);
-- Modalidad Fiesta
insert into fiesta (id, costo_total_id, organizador_id, fecha_limite) values
(1, 5, 1, TO_DATE('20/12/2019', 'DD/MM/YYYY'));
-- Template vacio
insert into e_template (id, descripcion, nombre, visibilidad, modalidad_id, organizador_id) values
(1, 'Primer template vacio', 'Evento Template', 'PUBLICA', 1, 1);
-- Evento
insert into evento (id, nombre, organizador_id, template_id) values
(1, 'Evento Fiesta', 1, 1);