
-- Se elimina una FK de la tabla para poder tener para distintos eventos los mismos invitados.
ALTER TABLE evento_asistentes DROP CONSTRAINT UK_RE4YATSY4AHR7LP6AV7GCNQ9J;

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
-- Creamos un evento Publico del primer usuario
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
-- Tabla conectora: CuentaEvento
insert into cuenta_eventos (cuenta_id, eventos_id) values (1, 1);
-- Tabla conectora: EventoAsistentes
insert into evento_asistentes (evento_id, asistentes_id) values (1, 2);
insert into evento_asistentes (evento_id, asistentes_id) values (1, 4);
-- Invitaciones: 2 confirmadas - 1 sin confirmar
insert into invitacion (id, confirmada, email, evento_id) values
(1, 1, 'brian@email.com', 1),(2, 0, 'ale@email.com', 1),(3, 1, 'homero@email.com', 1);

-- Creamos un evento Privado del segundo usuario.
-- Saldo
insert into dinero (id, aux, monto) values (6,0,0);
-- Modalidad Fiesta
insert into fiesta (id, costo_total_id, organizador_id, fecha_limite) values
(2, 6, 2, TO_DATE('20/12/2019', 'DD/MM/YYYY'));
-- Template vacio
insert into e_template (id, descripcion, nombre, visibilidad, modalidad_id, organizador_id) values
(2, 'Segundo template vacio', 'Evento Template', 'PRIVADA', 2, 2);
-- Evento
insert into evento (id, nombre, organizador_id, template_id) values
(2, 'Evento Fiesta Privada', 2, 2);
-- Tabla conectora: CuentaEvento
insert into cuenta_eventos (cuenta_id, eventos_id) values (2, 2);

-- Creamos un evento Publico del primer usuario para Eventos Pasados.
-- Saldo
insert into dinero (id, aux, monto) values (7,0,0);
-- Modalidad Fiesta (ya esta vencida)
insert into fiesta (id, costo_total_id, organizador_id, fecha_limite) values
(3, 7, 1, TO_DATE('10/02/2019', 'DD/MM/YYYY'));
-- Template vacio
insert into e_template (id, descripcion, nombre, visibilidad, modalidad_id, organizador_id) values
(3, 'Primer template vacio', 'Evento Template', 'PUBLICA', 3, 1);
-- Evento
insert into evento (id, nombre, organizador_id, template_id) values
(3, 'Evento Fiesta (ya vencida)', 1, 3);
-- Tabla conectora: CuentaEvento
insert into cuenta_eventos (cuenta_id, eventos_id) values (1, 3);
-- Tabla conectora: EventoAsistentes
insert into evento_asistentes (evento_id, asistentes_id) values (3, 2);
insert into evento_asistentes (evento_id, asistentes_id) values (3, 3);
insert into evento_asistentes (evento_id, asistentes_id) values (3, 4);