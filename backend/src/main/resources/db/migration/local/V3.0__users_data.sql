--
-- persons
--
-- customer
INSERT INTO persons (id, first_name, last_name, address_one, zip_code, city, phone_number)
VALUES (NEXTVAL('seq_persons_id'), 'Customer', 'Testingowy', 'Testowo 1', '00-002', 'Warszawa', 987654321);

--
-- users
--
-- customer, pwd: customerpwd
INSERT INTO users (id, email, password_hash, active, person_id)
VALUES (NEXTVAL('seq_users_id'), 'customer@camperoo.pl',
        '$argon2id$v=19$m=16384,t=2,p=1$RlBLbUYyWTM4VjNna3JiYg$VNomOKqeQTfIgdnfD7xiz9bJtzwPoFC2EqDxJXxmzPw', TRUE, 2);

SELECT SETVAL('seq_users_id', 2);

--
-- users_roles
--
INSERT INTO users_roles (user_id, role_id)
VALUES (2, 2);
