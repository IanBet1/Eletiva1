create database eletiva1;
use eletiva1;

create table categoria (
	idcategoria INT NOT NULL,
    nome VARCHAR (45) NOT NULL,
    PRIMARY KEY (idcategoria)
);

create table usuario (
	login VARCHAR(12) NOT NULL,
    senha VARCHAR(35) NOT NULL,
    nome VARCHAR (50) NOT NULL,
    email VARCHAR (50) NOT NULL,
    endereco VARCHAR (100) NOT NULL,
   	numero VARCHAR (45) NOT NULL,
    	bairro VARCHAR (45) NOT NULL,
    	cidade VARCHAR (45) NOT NULL,
    	uf VARCHAR (3) NOT NULL,
    	telefone VARCHAR(45) NOT NULL,
	status BOOLEAN NOT NULL,
    categoria_idcategoria INT NOT NULL,
    PRIMARY KEY (login),
    FOREIGN KEY (categoria_idcategoria) REFERENCES categoria(idcategoria)
);

create table classe (
	idclasse VARCHAR(20) NOT NULL,
	anoclasse INT NOT NULL,
	periodo VARCHAR(20) NOT NULL,
	turma VARCHAR(5) NOT NULL,
	professor VARCHAR(12) NOT NULL,
	status BOOLEAN NOT NULL,
	PRIMARY KEY (idclasse),
	FOREIGN KEY (professor) REFERENCES usuario(login)
);

create table aluno (
	matricula VARCHAR(10) NOT NULL,
    	nomealuno VARCHAR (50) NOT NULL,
    	nascimento DATE NOT NULL,
	mae VARCHAR (50) NOT NULL,
    	pai VARCHAR (50) NOT NULL,
    	endereco VARCHAR (100) NOT NULL,
	numero VARCHAR (45) NOT NULL,
    	bairro VARCHAR (45) NOT NULL,
    	cidade VARCHAR (45) NOT NULL,
    	uf VARCHAR (3) NOT NULL,
	complemento VARCHAR (50),
    	telefone1 VARCHAR(45) NOT NULL,
	telefone2 VARCHAR(45),
	telefone3 VARCHAR(45),
	status BOOLEAN NOT NULL,
    PRIMARY KEY (matricula)
);

create table classe_has_aluno (
	classe_idclasse VARCHAR(20) NOT NULL,
	aluno_matricula VARCHAR(10) NOT NULL,
	status BOOLEAN NOT NULL,
	PRIMARY KEY (classe_idclasse, aluno_matricula),
	FOREIGN KEY (classe_idclasse) REFERENCES classe(idclasse),
	FOREIGN KEY (aluno_matricula) REFERENCES aluno(matricula)
);

create table areaconhecimento (
	idconhecimento INT NOT NULL  AUTO_INCREMENT,
	areaconhecimento VARCHAR(30) NOT NULL,
	PRIMARY KEY (idconhecimento)
);

create table avaliacao(
	idavaliacao INT AUTO_INCREMENT,
	tipo VARCHAR(20),
	arquivo VARCHAR(1000),
	areaconhecimento_idareaconhecimento INT,
	status VARCHAR(20),
	observacao VARCHAR(500),
	usuario_login VARCHAR(12),
	PRIMARY KEY (idavaliacao),
	FOREIGN KEY (areaconhecimento_idareaconhecimento) REFERENCES areaconhecimento(idconhecimento),
	FOREIGN KEY (usuario_login) REFERENCES usuario(login)
);

create table estrategia (
	idestrategia INT NOT NULL AUTO_INCREMENT,
	estrategia VARCHAR(500) NOT NULL,
	areaconhecimento_idconhecimento INT NOT NULL,
	PRIMARY KEY (idestrategia),
	FOREIGN KEY (areaconhecimento_idconhecimento) REFERENCES areaconhecimento(idconhecimento)
);

create table planoaula (
	idplanoaula INT NOT NULL AUTO_INCREMENT,
	datainicio DATE NOT NULL,
	datafim DATE NOT NULL,
	classe_idclasse VARCHAR(20) NOT NULL,
	usuario_login VARCHAR(12) NOT NULL,
	status VARCHAR(50) NOT NULL,
        observacao VARCHAR(300),
	PRIMARY KEY (idplanoaula),
	FOREIGN KEY (classe_idclasse) REFERENCES classe(idclasse),
	FOREIGN KEY (usuario_login) REFERENCES usuario(login)
);

create table diasemana (
	iddiasemana INT NOT NULL AUTO_INCREMENT,
	dia VARCHAR(100) NOT NULL,
	principal_obj VARCHAR(100) NOT NULL,
	acolhida VARCHAR(100) NOT NULL,
	anexos VARCHAR(1000),
	licaodecasa VARCHAR(1000),
	observacoes VARCHAR(500),
	datadiasemana DATE NOT NULL,
	PRIMARY KEY (iddiasemana)
);

create table diasemana_has_estrategia (
	diasemana_iddiasemana INT NOT NULL,
	estrategia_idestrategia INT NOT NULL,
	planoaula_idplanoaula INT NOT NULL,
	PRIMARY KEY(diasemana_iddiasemana, estrategia_idestrategia, planoaula_idplanoaula),
	FOREIGN KEY(diasemana_iddiasemana) REFERENCES diasemana(iddiasemana),
	FOREIGN KEY(estrategia_idestrategia) REFERENCES estrategia(idestrategia),
	FOREIGN KEY(planoaula_idplanoaula) REFERENCES planoaula(idplanoaula)
);


INSERT INTO categoria VALUES(1, "Professor");
INSERT INTO categoria VALUES(2, "Coordenador");
INSERT INTO categoria VALUES(3, "Diretor");
INSERT INTO usuario VALUES(1, "c4ca4238a0b923820dcc509a6f75849b", "Usuário Teste - Diretor", 1, 1, 1, 1, 1, "AC", 1, true, 3);

INSERT INTO areaconhecimento VALUES(1, 'A.c.p.'); 
INSERT INTO areaconhecimento VALUES(2, 'Alfabetização'); 
INSERT INTO areaconhecimento VALUES(3, 'Artes'); 
INSERT INTO areaconhecimento VALUES(4, 'Artes visuais'); 
INSERT INTO areaconhecimento VALUES(5, 'Científicos'); 
INSERT INTO areaconhecimento VALUES(6, 'Educação física'); 
INSERT INTO areaconhecimento VALUES(7, 'Históricos geográficos'); 
INSERT INTO areaconhecimento VALUES(8, 'Informática'); 
INSERT INTO areaconhecimento VALUES(9, 'Matemática'); 
INSERT INTO areaconhecimento VALUES(10, 'Movimento'); 
INSERT INTO areaconhecimento VALUES(11, 'Música'); 

DELIMITER $$
CREATE PROCEDURE deletaTudo()
BEGIN
	DELETE FROM diasemana_has_estrategia;
	DELETE FROM diasemana;
	DELETE FROM planoaula;
	DELETE FROM estrategia;
END $$
DELIMITER ;
