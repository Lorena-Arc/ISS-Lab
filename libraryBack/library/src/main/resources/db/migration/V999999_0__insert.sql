INSERT INTO BOOK_STATUS(TITLE) VALUES ('DISPONIBIL'), ('INDISPONIBIL');

INSERT INTO BOOKS(ID, TITLE, AUTHOR, STATUS) VALUES
('1', 'Alegerea Sofiei', 'William Styron', 'DISPONIBIL'),
('2', 'Alegerea Sofiei', 'William Styron', 'INDISPONIBIL'),
('3', 'Alegerea Sofiei', 'William Styron', 'INDISPONIBIL'),
('4', 'Maestrul si Margareta', 'Mihail Bulgakov', 'INDISPONIBIL'),
('5', 'Maestrul si Margareta', 'Mihail Bulgakov', 'DISPONIBIL'),
('6', 'Maestrul si Margareta', 'Mihail Bulgakov', 'DISPONIBIL');

INSERT INTO ROLE(TITLE) VALUES ('ABONAT'), ('BIBLIOTECAR');

INSERT INTO LOAN_STATUS(TITLE) VALUES ('ACTIV'), ('INACTIV');

INSERT INTO SUBSCRIBERS(ID, CNP, NAME, USERNAME, PASSWORD, ROLE) VALUES
('1', '6010922060010', 'Arcalean Lorena', 'lorena', '$2a$12$XAec2Xq1jrH87CJpaFkAzue48TGaEsSIkLFn7J/LE7EADitbIPqru', 'ABONAT'),
('2', '5010319060010', 'Moldovan Cristian', 'cristi', '$2a$12$XAec2Xq1jrH87CJpaFkAzue48TGaEsSIkLFn7J/LE7EADitbIPqru', 'ABONAT');

INSERT INTO LIBRARIANS(ID, USERNAME, PASSWORD, ROLE) VALUES
('1', 'lorena', '$2a$12$XAec2Xq1jrH87CJpaFkAzue48TGaEsSIkLFn7J/LE7EADitbIPqru', 'BIBLIOTECAR');

INSERT INTO LOANS(ID, ID_BOOK, ID_SUBSCRIBER, LOAN_STATUS) VALUES
('1', '1', '1', 'INACTIV'),
('2', '2', '1', 'ACTIV'),
('3', '3', '1', 'ACTIV'),
('4', '1', '2', 'INACTIV'),
('5', '6', '2', 'INACTIV'),
('6', '4', '2', 'ACTIV');

