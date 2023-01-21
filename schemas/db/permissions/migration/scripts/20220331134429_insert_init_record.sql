-- // First migration.
-- Migration SQL that makes the change goes here.
INSERT INTO permissions (id, name) VALUES (1000, 'getShopInfo');
INSERT INTO permissions (id, name) VALUES (1001, 'findOrderHistory');
INSERT INTO role_permissions (role_key, permission_id) VALUES ('guest', 1000);
INSERT INTO role_permissions (role_key, permission_id) VALUES ('guest', 1001);
-- //@UNDO
-- SQL to undo the change goes here.
DELETE FROM permissions WHERE id = 1000;
