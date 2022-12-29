CREATE TABLE users (
  id                   SERIAL                 NOT NULL,
  name                 VARCHAR(255)           NOT NULL UNIQUE,
  PRIMARY KEY (id)
);
CREATE TABLE user_authentications (
  user_id              INT                    NOT NULL,
  email                VARCHAR(255)           NOT NULL,
  password             VARCHAR(255)           NOT NULL
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