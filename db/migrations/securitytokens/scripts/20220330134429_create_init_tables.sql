-- // First migration.
-- Migration SQL that makes the change goes here.
CREATE TABLE client_ids (
  client_id            VARCHAR(255)           NOT NULL,
  client_secret        VARCHAR(255)           NOT NULL,
);
CREATE TABLE permissions (
  id                   VARCHAR(255)           NOT NULL,
  name                 VARCHAR(255)           NOT NULL,
);
CREATE TABLE role_permissions (
  role_key             VARCHAR(255)           NOT NULL,
  permission_id        VARCHAR(255)           NOT NULL,
);
-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE IF EXISTS client_ids;
DROP TABLE IF EXISTS permissions;
DROP TABLE IF EXISTS role_permissions;
