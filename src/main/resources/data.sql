INSERT INTO state (name) VALUES ('São Paulo');
INSERT INTO state (name) VALUES ('Minas Gerais');

SET @saoPauloId = (SELECT id FROM state WHERE name = 'São Paulo');
SET @minasGeraisId = (SELECT id FROM state WHERE name = 'Minas Gerais');

INSERT INTO city (name, state_id) VALUES ('São Paulo', @saoPauloId);
INSERT INTO city (name, state_id) VALUES ('Campinas', @saoPauloId);
INSERT INTO city (name, state_id) VALUES ('Uberlândia', @minasGeraisId);


INSERT INTO customer (EMAIL, NAME, IDENTIFICATION, TYPE, PASSWORD)
VALUES ('vini.genka@tgid.com.br', 'Vinicius Genka', '66033608000120', 1, '$2a$10$NKKaPp8OU3LMNdpFtzu4T.P/vBR7VhgbKnJKB1Tp7KPIVQT9yuf.O');

INSERT INTO CATEGORY (NAME)
VALUES ('Informática');

INSERT INTO PRODUCT (NAME, PRICE)
VALUES ('Computador', 100);

INSERT INTO PRODUCT_CATEGORY (PRODUCT_ID, CATEGORY_ID)
VALUES (1, 1);

INSERT INTO ADDRESS (COMPLEMENT, NEIGHBORHOOD, NUMBER, STREET, ZIP_CODE, CITY_ID, CUSTOMER_ID)
VALUES ('Apt. 6', 'Carnegie', '369', 'Rua Rockefeller', '08574928', 1, 1);

INSERT INTO phones (CUSTOMER_ID, PHONES)
VALUES (1, '123456789');

INSERT INTO roles (CUSTOMER_ID, ROLES)
VALUES (1, 1);