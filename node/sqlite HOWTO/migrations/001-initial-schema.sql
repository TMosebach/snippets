-- Up 
CREATE TABLE Todo (id INTEGER PRIMARY KEY, priority INTEGER, text TEXT, expiration DATE);
INSERT INTO Todo (id, priority, text, expiration) VALUES (1, 2, 'Todo 1', '2019-02-15');
INSERT INTO Todo (id, priority, text) VALUES (2, 1, 'Todo 2');
 
-- Down 
DROP TABLE Todo;