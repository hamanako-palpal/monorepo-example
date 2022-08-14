-- // First migration.
-- Migration SQL that makes the change goes here.
CREATE TABLE users (
  id                   SERIAL NOT NULL,
  name                 VARCHAR(255)           NOT NULL UNIQUE,
  PRIMARY KEY (id)
);
CREATE TABLE shops (
  id                   SERIAL NOT NULL,
  name                 VARCHAR(255)           NOT NULL UNIQUE,
  PRIMARY KEY (id)
);

-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS shops;
