--Tabela EXAME
CREATE TABLE exame (rowid bigint auto_increment, nm_exame VARCHAR(255),
PRIMARY KEY (rowid)
);

--Inserção de 6 exames
INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue'), ('ECG'), ('EEG');

--Tabela FUNCIONARIOS
CREATE TABLE funcionario (rowid bigint auto_increment, nm_funcionario VARCHAR(255),
PRIMARY KEY (rowid)
);

--Inserção de 2 funcionários
INSERT INTO funcionario (nm_funcionario) VALUES ('Alejandro'), ('Joaquina'), ('Ricardo'), ('Marta');

--Tabela FICHA, relacionando-se com a tabela EXAMES e a tabela FUNCIONARIO
CREATE TABLE ficha (id_ficha bigint auto_increment, id_funcionario bigint, id_exame bigint, data_exame date,
PRIMARY KEY (id_exame, id_funcionario, data_exame),
CONSTRAINT fk_ficha_exame FOREIGN KEY (id_exame) REFERENCES exame(rowid),
CONSTRAINT fk_ficha_funcionario FOREIGN KEY (id_funcionario) REFERENCES funcionario(rowid) ON DELETE CASCADE
);

--Inserção de 15 fichas
INSERT INTO ficha (id_funcionario, id_exame, data_exame) VALUES 
	(1, 1, '2023-03-15'), (1, 2, '2023-03-01'), (1, 3, '2023-02-27'),
 	(2, 5, '2023-02-27'), (2, 3, '2023-03-30'), (2, 4, '2023-01-15'),
 	(2, 3, '2023-02-07'), (1, 1, '2023-02-11'), (1, 2, '2023-04-09'),
 	(1, 4, '2022-02-27'), (2, 5, '2022-02-07'), (1, 1, '2023-04-11'),
 	(1, 2, '2023-01-09'), (1, 3, '2022-02-07'), (1, 3, '2023-01-01'),
 	(1, 6, '2023-01-01');
 	
 --Tabela USER
CREATE TABLE usuario (id_user bigint auto_increment, login varchar(50), password varchar(20),
PRIMARY KEY (id_user));

--Inserção de 2 usuários
INSERT INTO usuario (login, password) VALUES ('admin', '123');
INSERT INTO usuario (login, password) VALUES ('felipe', 'teste');