-- // First migration.
-- Migration SQL that makes the change goes here.
CREATE TABLE client_ids (
  client_id            VARCHAR(255)           NOT NULL,
  client_secret        VARCHAR(255)           NOT NULL
);
CREATE TABLE permissions (
  id                   SERIAL                 NOT NULL,
  name                 VARCHAR(255)           NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE role_permissions (
  role_key             VARCHAR(255)           NOT NULL,
  permission_id        INT                    NOT NULL,
  FOREIGN KEY (permission_id) references db_permissions.permissions(id) ON DELETE CASCADE
);
CREATE TABLE refresh_tokens (
  jti                  VARCHAR(255)           NOT NULL,
  user_id              INT                    NOT NULL,
  client_id            VARCHAR(255)           NOT NULL,
  expired              TIMESTAMP              NOT NULL DEFAULT '1000-01-01 00:00:00'
);
-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE IF EXISTS client_ids;
DROP TABLE IF EXISTS permissions;
DROP TABLE IF EXISTS role_permissions;
DROP TABLE IF EXISTS refresh_tokens;
