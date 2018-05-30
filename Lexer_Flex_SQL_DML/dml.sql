SELECT CODIGO, NOME FROM CLIENTES;

SELECT * FROM CLIENTES;

SELECT CODIGO, NOME FROM CLIENTES
WHERE CODIGO = 10;

SELECT CODIGO, NOME FROM CLIENTES
WHERE UF = "RJ";

SELECT CODIGO, NOME FROM CLIENTES
WHERE CODIGO >= 100 AND CODIGO <= 500;

SELECT CODIGO, NOME FROM CLIENTES
WHERE UF = "MG" OR UF = "SP";

SELECT CODIGO, NOME FROM CLIENTES
ORDER BY UF, NOME;

INSERT INTO phone_book (name, number) VALUES ("John Doe", "555-1212");

INSERT INTO EMPREGADOS(CODIGO, NOME, SALARIO, SECAO)
VALUES(1, "HELBERT CARVALHO", 1.500, 1);

UPDATE DEPARTAMENTO
SET SALARIO = 1000
WHERE CODIGODEP = 1;

DELETE FROM EMPREGADOS
WHERE CODIGO = 125;