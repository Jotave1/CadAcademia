CREATE DATABASE sistema_academia;

USE sistema_academia;

CREATE TABLE alunos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    idade INT,
    telefone VARCHAR(20),
    peso DOUBLE,
    objetivo VARCHAR(255),
    tipo_treino VARCHAR(255)
);

CREATE TABLE treinos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ordem VARCHAR(255),
    descricao VARCHAR(255)
);