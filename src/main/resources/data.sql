INSERT INTO state (name) VALUES ('São Paulo');
INSERT INTO state (name) VALUES ('Minas Gerais');

SET @saoPauloId = (SELECT id FROM state WHERE name = 'São Paulo');
SET @minasGeraisId = (SELECT id FROM state WHERE name = 'Minas Gerais');

INSERT INTO city (name, state_id) VALUES ('São Paulo', @saoPauloId);
INSERT INTO city (name, state_id) VALUES ('Campinas', @saoPauloId);
INSERT INTO city (name, state_id) VALUES ('Uberlândia', @minasGeraisId);
