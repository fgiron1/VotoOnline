USE master;
GO
CREATE DATABASE VotoOnline;
GO
USE VotoOnline

SET DATEFORMAT dmy;


--PROCEDIMIENTO QUE SE EJECUTE AUTOMATICAMENTE DESPUÉS DE QUE SE LLEGE A LA FECHA DE FINAL DE LAS ELECCIONES
--Y QUE LE REVOQUE AL LOGIN DE LOS USUARIOS LOS PERMISOS DE HACER UPDATE Y EXECUTE

--Hoy voy a hacer los siguientes procedimientos:
-- 1.- Insertar político
-- 2.- Insertar 


GO

--Esta es la tabla que nos proporciona el censo de votantes, con la información de la
--identidad de cada persona

CREATE TABLE Votante (

	ID int IDENTITY(1,1) NOT NULL,
	nombre nvarchar(30) NOT NULL,
	apellidos nvarchar(60) NOT NULL,
	fechaNacimiento date NOT NULL,
	edad AS (YEAR(CURRENT_TIMESTAMP - CAST(fechaNacimiento AS smalldatetime)) - 1900),
	sexo char(1) NOT NULL,
	DNI nvarchar(9) NOT NULL,
	prefijo nvarchar(4) NOT NULL,
	telefono nvarchar(15) NOT NULL,
	provinciaNacimiento nvarchar(20) NOT NULL,
	provinciaResidencia nvarchar(20) NOT NULL

	CONSTRAINT PKVotante Primary Key (ID),
	CONSTRAINT CK_Sexo CHECK (sexo = 'H' OR sexo = 'M'),
	CONSTRAINT CK_Prefijo CHECK (prefijo LIKE '+%'),
	CONSTRAINT CK_Telefono CHECK (telefono NOT LIKE '%[^0-9]%')

)

CREATE TABLE Partido (

	ID int IDENTITY(1,1) NOT NULL,
	nombre nvarchar(30) NOT NULL,
	lema nvarchar(100) NOT NULL,
	votosTotales int NOT NULL,
	escanos int NOT NULL

	CONSTRAINT PKPartido Primary Key (ID)

)

--La columna votos representa los votos que recibe el politico SOLO en las elecciones autonomicas,
--puesto que las nacionales son de listas cerradas
GO
CREATE TABLE Politico (

	ID int IDENTITY(1,1) NOT NULL,
	nombre nvarchar(30) NOT NULL,
	apellidos nvarchar(50) NOT NULL,
	partido int NOT NULL,
	consigueEscano bit NOT NULL

	CONSTRAINT PKPolitico PRIMARY KEY (ID),
	CONSTRAINT FKPoliticosPartidos FOREIGN KEY (partido) REFERENCES Partido (ID)

)
GO

INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Jesús', N'Zhou González', CAST(N'08-08-1946' AS date), N'H', N'26969033S', N'+39',  N'03681253021', N'Álava', N'Valencia')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Luis', N'Carmona Guirado', CAST(N'22-11-1955' AS date), N'H', N'05814522F', N'+34', N'656823205', N'Ciudad Real', N'Salamanca')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Ana', N'Naranjo Pelayo', CAST(N'24-03-1969' AS date), N'M', N'42728708K', N'+34', N'678430803', N'Las Palmas', N'Murcia')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Pedro', N'Goñi Luque', CAST(N'03-12-1988' AS date), N'H', N'68930227D', N'+34', N'775744735', N'Cantabria', N'Cantabria')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'María', N'Busto Sánchez', CAST(N'20-11-1957' AS date), N'M', N'07883545L', N'+34', N'770632729', N'Granada', N'Sevilla')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'José Antonio', N'Martínez Martínez', CAST(N'09-06-1964' AS date), N'H', N'80882993C', N'+34', N'692128207', N'Granada', N'Granada')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Javier', N'Teixeira Fernández', CAST(N'02-01-1961' AS date), N'H', N'39743600Z', N'+34', N'724202992', N'Albacete', N'Lérida')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Juan Manuel', N'Gibert Vicente', CAST(N'20-05-1981' AS date), N'H', N'49406836F', N'+34', N'724166396', N'Valencia', N'Barcelona')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Alejandro', N'Camarena Maroto', CAST(N'15-07-1952' AS date), N'H', N'90601404B', N'+34', N'604841003', N'Valencia', N'Asturias')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Ana', N'Leiva Muñoz', CAST(N'06-09-1955' AS date), N'M', N'52120586X', N'+34', N'630238053', N'Castellón', N'Huelva')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Francisco', N'Díaz Blanco', CAST(N'18-12-1972' AS date), N'H',  N'50777630R', N'+49', N'0353256753', N'Toledo', N'Jaén')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Miguel', N'Laborda Martínez', CAST(N'14-06-1942' AS date), N'H', N'97414369P', N'+1', N'7406307237', N'Barcelona', N'Alicante')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Santiago', N'Córdoba Palomar', CAST(N'08-04-1962' AS date), N'H', N'38128073F', N'+1', N'8179281090', N'Burgos', N'Cantabria')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'José', N'Ruiz Martínez', CAST(N'22-08-1952' AS date), N'H', N'31136136R', N'+34', N'734052327', N'Barcelona', N'Madrid')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Juan Manuel', N'González Picazo', CAST(N'13-04-1999' AS date), N'H', N'21266760V', N'+34', N'687069393', N'S.C. De Tenerife', N'Madrid')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'José Antonio', N'Rodríguez Morcillo', CAST(N'31-08-1956' AS date), N'H', N'25226967S', N'+34', N'768213782', N'Cádiz', N'Alicante')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Francisco Javier', N'Medina Quintana', CAST(N'09-12-1982' AS date), N'H', N'23646678X', N'+45', N'21475692', N'Cantabria', N'Burgos')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Leopoldo', N'Acal García', CAST(N'10-06-2000' AS date), N'H', N'46450536N', N'+34', N'755318016', N'Vizcaya', N'La Coruña')
INSERT INTO [dbo].[Votante] (nombre, apellidos, fechaNacimiento, sexo, DNI, prefijo, telefono, provinciaNacimiento, provinciaResidencia) VALUES (N'Ignacio', N'Rubio Garrido', CAST(N'21-03-1998' AS date), N'H', N'42813192A', N'+45', N'29180919', N'Islas Baleares', N'Álava')


--Este procedimiento se ejecuta tras finalizar las elecciones
GO
CREATE OR ALTER PROCEDURE AsignarEscanos AS
BEGIN

	DECLARE @i int = 0
	DECLARE @PartidoConsigue int

	BEGIN TRANSACTION

	WHILE(@i <= 7)
		BEGIN
			--Se calcula los escaños que le corresponden a cada partido segun el sistema d'hondt
			SELECT TOP 1 @PartidoConsigue = Sub1.ID FROM
			(SELECT P.ID, (P.votosTotales/(@i+P.escanos)) AS Cociente 
			FROM Partido AS P) AS Sub1
			ORDER BY Sub1.Cociente DESC

			--Se aumenta en 1 los escanos del partido correspondiente
			UPDATE Partido
			SET escanos = escanos + 1
			WHERE ID = @PartidoConsigue

			SET @i += 1
		END
END

GO

--TEST
SELECT TOP 1 P.ID, MAX(P.votosTotales/P.escanos)
FROM Partido AS P
GROUP BY P.ID

INSERT INTO 
