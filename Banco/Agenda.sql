/**
* Agenda De Contatos
*@Author Bruno Henrique 
*/

-- Comando para Verificar os Bancos criados 
show databases;

-- Comando para criar um Novo banco de dados 
create database agenda;

-- Comando para selecionar um bando de dados 
use agenda;

-- Comando usado para excluir um banco de dados
drop database nome_do_banco;

-- Comando usado para criar uma tabela
-- int (tipos de dados: números inteiros)
-- primary key	(chave primária - identificador)
-- auto_increment (numeração automática)
-- varchar(50) (tipo de dados String - 50- caracteres)
-- not null ( campo obrigatorio
create table contatos(
id int primary key auto_increment,
nome varchar(50) not null,
fone varchar(15) not null,
email varchar (50) 
);

-- verificar tableas do banco de dados
show tables;

-- descrever a tabela
describe contatos;