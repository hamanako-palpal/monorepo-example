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
  permission_id        VARCHAR(255)           NOT NULL
);