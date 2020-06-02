--USE master;
--GO
--CREATE DATABASE VotoOnline;
--GO
--USE VotoOnline


--PROCEDIMIENTO QUE SE EJECUTE AUTOMATICAMENTE DESPUÉS DE QUE SE LLEGE A LA FECHA DE FINAL DE LAS ELECCIONES
--Y QUE LE REVOQUE AL LOGIN DE LOS USUARIOS LOS PERMISOS DE HACER UPDATE Y EXECUTE

GO

--Esta es la tabla que nos proporciona el censo de votantes, con la información de la
--identidad de cada persona

CREATE TABLE Votante (

	ID int IDENTITY(1,1) NOT NULL,
	nombre nvarchar(30) NOT NULL,
	apellidos nvarchar(60) NOT NULL,
	fechaNacimiento date NOT NULL,
	edad AS (YEAR(CURRENT_TIMESTAMP - CAST(fechaNacimiento AS smalldatetime)) - 1900) NOT NULL,
	sexo char(1) NOT NULL,
	codigo nvarchar(9) NOT NULL,
	telefono nvarchar(15) NOT NULL,
	provinciaNacimiento nvarchar(20) NOT NULL,
	provinciaResidencia nvarchar(20) NOT NULL

	CONSTRAINT PKVotante Primary Key (ID),
	CONSTRAINT CK_Sexo CHECK (sexo = 'M' OR sexo = 'F'),
	CONSTRAINT CK_Telefono CHECK (telefono LIKE '+%' AND telefono NOT LIKE '%[^0-9]%')

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
	votos int NULL, 
	consigueEscano bit NOT NULL

	CONSTRAINT PKPolitico PRIMARY KEY (ID),
	CONSTRAINT FKPoliticosPartidos FOREIGN KEY (partido) REFERENCES Partido (ID)
	

)
GO

GO
CREATE PROCEDURE 


GO