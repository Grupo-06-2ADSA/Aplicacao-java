create database MindCore;
use MindCore;

create table Empresa (
cnpj char(14) primary key unique,
nome varchar(45),
telefone char(11));

create table Componentes (
idComponente int primary key auto_increment,
nomeComponente varchar(45),
quantidade int,
preco decimal(5,2),
fkEmpresa char(14),
foreign key (fkEmpresa) references Empresa(cnpj)
);

create table Sala (
idSala int primary key auto_increment,
nome varchar(45),
andar int,
fkEmpresa char(14),
foreign key (fkEmpresa) references Empresa(cnpj)
);

create table Funcionario (
idFunc int primary key auto_increment,
nome varchar(45),
email varchar(45),
senha varchar(45),
telefone char(11),
tipo varchar(45),
check (tipo in('Empresa','Gestor','Técnico')),	
turno varchar(20),
check (turno in('manha', 'tarde', 'noite')),
estado varchar(20),
check (estado in('ativo', 'inativo')),
fkEmpresa char(14),
foreign key (fkEmpresa) references Empresa(cnpj)
);

create table Maquina(
hostname varchar(45) primary key,
ip varchar(45),
imagem date,
fkSala int,
fkEmpresa char(14),
foreign key (fkSala) references Sala(idSala),
foreign key (fkEmpresa) references Empresa(cnpj));

create table Metricas(
idMetrica int primary key auto_increment,
CompCpu int,
CompDisco double,
CompRam double,
fkEmpresa char(14),
foreign key (fkEmpresa) references Empresa(cnpj));

-- CRIAR TABELAS PARA CADA HARDWARE, COM SUAS RESPECTIVAS INFORMAÇÕES!!!

create table HistoricoManutencao(
idHistorico int primary key auto_increment,
Dia date,
descricao varchar(45),
tipo varchar(45),
fkMaquina varchar(45),
fkSala int,
responsavel int,
foreign key (fkMaquina) references Maquina(hostname),
foreign key (fkSala) references Sala(idSala),
foreign key (responsavel) references Funcionario(idFunc));

create table LeituraSO(
idSO int primary key auto_increment,
nome varchar(45),
tempoAtividade long,
dataLeitura datetime default current_timestamp,
fkMaquina varchar(45),
foreign key (fkMaquina) references Maquina(hostname)
);

create table LeituraDisco(
idDisco int primary key auto_increment,
disponivel long,
emUso long,
total long,
dataLeitura datetime default current_timestamp,
fkMaquina varchar(45),
foreign key (fkMaquina) references Maquina(hostname)
);

create table LeituraJanelas(
idJanela int primary key auto_increment,
identificador long,
pid int,
titulo varchar(120),
totalJanelas int,
dataLeitura datetime default current_timestamp,
fkMaquina varchar(45),
foreign key (fkMaquina) references Maquina(hostname)
);

create table LeituraCPU(
idCPU int primary key auto_increment,
nome varchar(100),
emUso double,
temp double,
dataLeitura datetime default current_timestamp,
fkMaquina varchar(45),
foreign key (fkMaquina) references Maquina(hostname)
);

create table LeituraMemoriaRam(
idRam int primary key auto_increment,
emUso double,
total double,
disponivel double,
dataLeitura datetime default current_timestamp,
fkMaquina varchar(45),
foreign key (fkMaquina) references Maquina(hostname)
);

insert into Empresa (cnpj, nome, telefone) values ('12547896547852', 'SPTech', '11958247856');
insert into Funcionario (nome, email, senha, telefone, tipo, turno,  estado, fkEmpresa) 
	values ('Luan Gomes', 'luan@gmail.com', '654321', '11958741526', 'Gestor', 'noite', 'ativo', '12547896547852');
insert into Funcionario (nome, email, senha, telefone, tipo, turno,  estado, fkEmpresa)
    values ('Heloisa Salgado', 'heloisa@gmail.com', '123456', '11982410672', 'Gestor', 'tarde', 'ativo', '12547896547852');
