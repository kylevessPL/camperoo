INSERT INTO company_branches (id, name, address, latitude, longitude, email, phone_number)
VALUES (1, 'Moto Center', 'Jerozolimska 12A, 91-434 Łódź', 51.781014, 19.461127, 'motocenter@camperoo.pl', 423791265);

SELECT SETVAL('seq_company_branches_id', 1);
