DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (id,user_id,date_time,description,calories) VALUES
(100010,100000, '2020-05-30 10:00:00','Завтрак',500),
(100011,100000, '2020-05-30 13:00:00','Обед',1000),
(100012,100000, '2020-05-30 20:00:00','Ужин',500),
-- (100000, '2020-06-31 00:00:00','Еда на граничное значение',100),
(100013,100000, '2020-05-31 10:00:00','Завтрак',1000),
(100014,100000, '2020-05-31 13:00:00','Обед',500),
(100015,100000, '2020-05-31 20:00:00','Ужин',410),
(100016,100001, '2020-06-01 14:00:00','Админ ланч',510),
(100017,100001, '2020-06-01 21:00:00','Админ ужин',1500);

