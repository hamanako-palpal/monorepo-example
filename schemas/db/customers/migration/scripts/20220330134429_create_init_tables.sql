-- // First migration.
-- Migration SQL that makes the change goes here.
CREATE TABLE users (
  id                   SERIAL                 NOT NULL,
  name                 VARCHAR(255)           NOT NULL UNIQUE,
  PRIMARY KEY (id)
);
CREATE TABLE user_authentications (
  user_id              INT                    NOT NULL,
  email                VARCHAR(255)           NOT NULL,
  password             VARCHAR(255)           NOT NULL,
);
CREATE TABLE shops (
  id                   SERIAL                 NOT NULL,
  name                 VARCHAR(255)           NOT NULL UNIQUE,
  PRIMARY KEY (id)
);
CREATE TABLE roles (
  id                   SERIAL                 NOT NULL,
  role_key             VARCHAR(255)           NOT NULL UNIQUE,
  PRIMARY KEY (id)
);
CREATE TABLE staffs (
  id                   SERIAL                 NOT NULL,
  user_id              INT                    NOT NULL,
  shop_id              INT                    NOT NULL,
  role_id              INT                    NOT NULL,
  PRIMARY KEY (id)
);
-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_authentications;
DROP TABLE IF EXISTS shops;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS staffs;
