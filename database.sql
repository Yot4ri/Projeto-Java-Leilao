CREATE DATABASE Leilao;

USE Leilao;

CREATE TABLE IF NOT EXISTS Itens (
ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
Nome VARCHAR (255) NOT NULL,
Preco DOUBLE NOT NULL,
Status VARCHAR (50) NOT NULL);

SELECT * FROM Itens
