CREATE TABLE orders (
  id                   SERIAL NOT NULL,
  user_id              INT                    NOT NULL,
  shop_id              INT                    NOT NULL,
  ordered              TIMESTAMP              NOT NULL DEFAULT '1000-01-01 00:00:00',
  PRIMARY KEY (id)
);