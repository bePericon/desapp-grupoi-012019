
CREATE TABLE Usario(
    IDUSUARIO INTEGER not null,
    NOMBRE VARCHAR(50),
    APELLIDO VARCHAR(50),
    EMAIL VARCHAR(20),
    FECHANAC DATE,
	CONTRASENIA VARCHAR(8),
	PRIMARY KEY (IDUSUARIO)
);

INSERT INTO Usuario
VALUES ('Emmanuel','Pericon','epericon@email.com','1992-11-27','12345678');

INSERT INTO Usuario
VALUES ('Alejandro','Rossi','arossi@email.com','1992-01-20','12345678');