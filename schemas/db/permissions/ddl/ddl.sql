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
  permission_id        INT                    NOT NULL
);
CREATE TABLE refresh_tokens (
  jti                  VARCHAR(255)           NOT NULL,
  user_id              INT                    NOT NULL,
  client_id            VARCHAR(255)           NOT NULL,
  expired              TIMESTAMP              NOT NULL DEFAULT '1000-01-01 00:00:00'
);
