
-- Se elimina una FK de la tabla para poder tener para distintos eventos los mismos invitados.
ALTER TABLE evento_asistentes DROP CONSTRAINT UK_RE4YATSY4AHR7LP6AV7GCNQ9J;

-- Creacion de usuarios: (usuario, saldo, cuenta)
-- Usuarios
insert into usuario (id, nombre, apellido, email, contrasenia, fecha_nac) values
(1, 'Emmanuel', 'Pericon','emma@email.com','$2a$04$CjttILdAPP0k.yduBaoM6erI/sf/w2H1fXjXUDbsO3MTkP2nvtST.',TO_DATE('27/11/1992', 'DD/MM/YYYY')),
(2, 'Brian', 'Pericon','brian@email.com','$2a$04$CjttILdAPP0k.yduBaoM6erI/sf/w2H1fXjXUDbsO3MTkP2nvtST.',TO_DATE('27/11/1992', 'DD/MM/YYYY')),
(3, 'Alejandro', 'Rossi','ale@email.com','$2a$04$CjttILdAPP0k.yduBaoM6erI/sf/w2H1fXjXUDbsO3MTkP2nvtST.',TO_DATE('10/08/1992', 'DD/MM/YYYY')),
(4, 'Homero', 'Simpson','homero@email.com','$2a$04$CjttILdAPP0k.yduBaoM6erI/sf/w2H1fXjXUDbsO3MTkP2nvtST.',TO_DATE('12/05/1956', 'DD/MM/YYYY'));
-- Saldos
insert into dinero (id, aux, monto) values
(1,0,3250),(2,0,0),(3,0,0),(4,0,0);
-- Cuentas
insert into cuenta (situacion_deuda, saldo_id, usuario_id) values
('NORMAL', 1, 1),('NORMAL', 2, 2),('NORMAL', 3, 3),('NORMAL', 4, 4);

-- Creacion de eventos: (saldo, modalidad, template, evento)
-- Creamos un evento Publico del primer usuario
-- Saldo
insert into dinero (id, aux, monto) values (5,0,0);
insert into dinero (id, aux, monto) values (6,0,0);
-- Modalidad Fiesta
insert into modalidad (DTYPE, id, costo_total_id, costo_usuario_id, organizador_id, fecha_limite) values
('Fiesta', 1, 5, 6, 1, TO_DATE('20/12/2019', 'DD/MM/YYYY'));
-- Template vacio
insert into e_template (id, descripcion, nombre, visibilidad, modalidad_id, organizador_id) values
(1, 'Primer template vacio', 'Evento Template', 'PUBLICA', 1, 1);
-- Evento
insert into evento (id, nombre, organizador_id, template_id) values
(1, 'Evento Fiesta', 1, 1);
-- Tabla conectora: CuentaEvento
insert into cuenta_eventos (cuenta_id, eventos_id) values (1, 1);
-- Tabla conectora: CuentaTemplate
insert into cuenta_templates (cuenta_id, templates_id) values (1, 1)
-- Tabla conectora: EventoAsistentes
insert into evento_asistentes (evento_id, asistentes_id) values (1, 2);
insert into evento_asistentes (evento_id, asistentes_id) values (1, 4);
-- Invitaciones: 2 confirmadas - 1 sin confirmar
insert into invitacion (id, email, estado_invitacion, evento_id) values
(1,'brian@email.com', 'ACEPTADA', 1),(2, 'ale@email.com', 'PENDIENTE', 1),(3, 'homero@email.com', 'PENDIENTE', 1);
-- Tabla conectora: CuentaInvitaciones
insert into cuenta_invitaciones (cuenta_id, invitaciones_id) values
(2, 1),(3, 2),(4, 3);

-- Creamos un evento Privado del segundo usuario.
-- Saldo
insert into dinero (id, aux, monto) values (7,0,0);
insert into dinero (id, aux, monto) values (8,0,0);
-- Modalidad Fiesta
insert into modalidad (DTYPE, id, costo_total_id, costo_usuario_id, organizador_id, fecha_limite) values
('Fiesta', 2, 7, 8, 2, TO_DATE('20/12/2019', 'DD/MM/YYYY'));
-- Template vacio
insert into e_template (id, descripcion, nombre, visibilidad, modalidad_id, organizador_id) values
(2, 'Segundo template vacio', 'Evento Template', 'PRIVADA', 2, 2);
-- Evento
insert into evento (id, nombre, organizador_id, template_id) values
(2, 'Evento Fiesta Privada', 2, 2);
-- Tabla conectora: CuentaEvento
insert into cuenta_eventos (cuenta_id, eventos_id) values (2, 2);
-- Tabla conectora: CuentaTemplate
insert into cuenta_templates (cuenta_id, templates_id) values (2, 2)

-- Creamos un evento Publico del primer usuario para Eventos Pasados.
-- Saldo
insert into dinero (id, aux, monto) values (9,0,0);
insert into dinero (id, aux, monto) values (10,0,0);
-- Modalidad Fiesta (ya esta vencida)
insert into modalidad (DTYPE, id, costo_total_id, costo_usuario_id, organizador_id, fecha_limite) values
('Fiesta', 3, 9, 10, 1, TO_DATE('10/02/2019', 'DD/MM/YYYY'));
-- Template vacio
insert into e_template (id, descripcion, nombre, visibilidad, modalidad_id, organizador_id) values
(3, 'Primer template vacio', 'Evento Template', 'PUBLICA', 3, 1);
-- Evento
insert into evento (id, nombre, organizador_id, template_id) values
(3, 'Evento Fiesta (ya vencida)', 1, 3);
-- Tabla conectora: CuentaEvento
insert into cuenta_eventos (cuenta_id, eventos_id) values (1, 3);
-- Tabla conectora: CuentaTemplate
insert into cuenta_templates (cuenta_id, templates_id) values (1, 3)
-- Tabla conectora: EventoAsistentes
insert into evento_asistentes (evento_id, asistentes_id) values (3, 2);
insert into evento_asistentes (evento_id, asistentes_id) values (3, 3);
insert into evento_asistentes (evento_id, asistentes_id) values (3, 4);

-- Montos para movimientos
insert into dinero (id, aux, monto) values 
(11,0,1000),(12,0,1000),(13,0,1000),(14,0,1000),(15,0,1000),(16,0,1000),(17,0,1000),
(18,0,250),(19,0,500), ---<<--- Primer usuario.
(20,0,1000),(21,0,1000),(22,0,1000),(23,0,1000),(24,0,1000),
(25,0,500),(26,0,500),(27,0,500),(28,0,500),(29,0,500),(30,0,500),(31,0,500);

-- NOTA: modificar el saldo de la cuenta mas arriba!
insert into movimiento (id, fecha, tipo_movimiento, monto_id) values 
(1, TO_DATE('01/02/2019', 'DD/MM/YYYY'), 'DEPOSITAR', 11),
(2, TO_DATE('01/02/2019', 'DD/MM/YYYY'), 'DEPOSITAR', 12),
(3, TO_DATE('01/02/2019', 'DD/MM/YYYY'), 'DEPOSITAR', 13),
(4, TO_DATE('01/02/2019', 'DD/MM/YYYY'), 'RETIRAR', 14),
(5, TO_DATE('01/02/2019', 'DD/MM/YYYY'), 'RETIRAR', 15),
(6, TO_DATE('01/02/2019', 'DD/MM/YYYY'), 'DEPOSITAR', 16),
(7, TO_DATE('01/02/2019', 'DD/MM/YYYY'), 'DEPOSITAR', 17),
(8, TO_DATE('01/02/2019', 'DD/MM/YYYY'), 'RETIRAR', 18),
(9, TO_DATE('01/02/2019', 'DD/MM/YYYY'), 'DEPOSITAR', 19);

insert into cuenta_movimientos (cuenta_id, movimientos_id) values
(1, 1),(1, 2),(1, 3),(1, 4),(1, 5),(1, 6),(1, 7),(1, 8),(1, 9);

--Precios de los items
insert into dinero (id, aux, monto) values
(32,0,80),(33,0,100),(34,0,70),(35,0,200);
-- Algunos items
insert into item (id,  nombre_item, personas_por_unidad, costo_id) values
(1, 'Cocacola', 3, 32),
(2, 'Asado', 2, 33),
(3, 'Birra', 1, 34),
(4, 'Docena sanguchitos', 4, 35);

