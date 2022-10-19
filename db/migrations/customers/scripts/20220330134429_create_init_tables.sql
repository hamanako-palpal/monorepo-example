-- // First migration.
-- Migration SQL that makes the change goes here.
CREATE TABLE users (
  id                   SERIAL NOT NULL,
  name                 VARCHAR(255)           NOT NULL UNIQUE,
  password             VARCHAR(255)           NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE shops (
  id                   SERIAL NOT NULL,
  name                 VARCHAR(255)           NOT NULL UNIQUE,
  PRIMARY KEY (id)
);
CREATE TABLE auth_tokens (
  user_id              INT                    NOT NULL,
  jwt_id               VARCHAR(255)           NOT NULL,
  ordered              TIMESTAMP              NOT NULL DEFAULT '1000-01-01 00:00:00',
);
-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS shops;
DROP TABLE IF EXISTS auth_tokens;
