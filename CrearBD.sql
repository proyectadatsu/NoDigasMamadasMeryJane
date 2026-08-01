IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'FERRETERIA')
BEGIN
    CREATE DATABASE FERRETERIA;
END
GO

USE FERRETERIA;
GO

IF OBJECT_ID('dbo.PRODUCTO', 'U') IS NOT NULL
DROP TABLE dbo.PRODUCTO;
GO

CREATE TABLE dbo.PRODUCTO (
                              id_producto       BIGINT IDENTITY(1,1) PRIMARY KEY,
                              nombre            VARCHAR(120)   NOT NULL,
                              marca             VARCHAR(80)    NOT NULL,
                              categoria         VARCHAR(80)    NOT NULL,
                              precio            DECIMAL(10, 2) NOT NULL CHECK (precio >= 0),
                              stock             INT            NOT NULL CHECK (stock >= 0),
                              descripcion       VARCHAR(500)   NULL,
);
GO

INSERT INTO dbo.PRODUCTO (nombre, marca, categoria, precio, stock, descripcion)
VALUES
    ('Martillo de Uña 16 oz', 'Stanley', 'Herramientas Manuales', 35.50, 25, 'Martillo de acero forjado con mango ergonómico antideslizante.'),
    ('Taladro Percutor 1/2" 650W', 'DeWalt', 'Herramientas Eléctricas', 289.90, 10, 'Taladro percutor de velocidad variable con empuñadura lateral.'),
    ('Juego de Llaves Allen 9 Pzas', 'Truper', 'Herramientas Manuales', 22.00, 40, 'Juego de llaves hexagonales milimétricas en acero cromo vanadio.'),
    ('Disco de Corte 4-1/2" Metal', 'Bosch', 'Consumibles y Accesorios', 6.50, 150, 'Disco abrasivo fino para corte rápido de acero e inox.');
GO

SELECT * FROM dbo.PRODUCTO;
GO