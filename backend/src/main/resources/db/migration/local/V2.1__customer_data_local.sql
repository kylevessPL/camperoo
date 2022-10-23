--
-- users
--
-- customer, pwd: adminpwd
INSERT INTO users (id, email, password_hash, active)
VALUES (2, 'customer@camperoo.pl', '$argon2id$v=19$m=16,t=2,p=1$WFRZZklvSWMwMmt2V3N2aQ$GJyrazp1IdfTY0AAEs0dCg', TRUE);

SELECT SETVAL('seq_users_id', 2);

--
-- persons
--
-- customer
INSERT INTO persons (id, first_name, last_name, address_1, zip_code, city, phone_number, user_id)
VALUES (2, 'Customer', 'Testingowy', 'Testowo 1', '00-002', 'Warszawa', 987654321, 2);

SELECT SETVAL('seq_persons_id', 2);
