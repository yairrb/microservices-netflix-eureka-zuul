

insert into usuarios (username, password, enabled, nombre, apellido, email) values ('yair',  '$2a$10$Y5w4aPOkKud/KQ5PbTTfCe8Zh8eOYs1Ol0S71hbOtIYwC6V9lWP8G', 1, 'yair','ruiz','yair.ruizbarbas@gmail.com')

insert into usuarios (username, password, enabled, nombre, apellido, email) values ('admin',  '$2a$10$A/JRN8fkBuLpXJAmrPjhZeyWrDjfRljfw2iJBK0nEVYCaD0Wy0lmC', 1, 'john','doe','admin.admin@gmail.com')

insert into roles (nombre) values ('ROLE_USER')
insert into roles (nombre) values ('ROLE_ADMIN')

insert into usuarios_to_roles (user_id, rol_id) values (1,1)
insert into usuarios_to_roles (user_id, rol_id) values (2,2)
insert into usuarios_to_roles (user_id, rol_id) values (2,1)