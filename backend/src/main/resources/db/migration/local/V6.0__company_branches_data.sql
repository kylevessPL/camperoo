--
-- company_branches
--
INSERT INTO company_branches (id, name, address, latitude, longitude, email, phone_number)
VALUES (1, 'Moto Center', 'Jerozolimska 12A, 91-434 Łódź', 51.781014, 19.461127, 'motocenter@camperoo.pl', 423791265),
       (2, 'Camperoo Global', 'al. Jerozolimskie 162, 02-236 Warszawa', 52.207825, 20.945282,
        'camperoo.global@camperoo.pl', 225671253),
       (3, 'Kawasaki RX', 'Kolejowa 17/19, 26-606 Radom', 51.389180, 21.157533, 'kawasaki-rx@camperoo.pl', 612378210),
       (4, 'Campany Ride', 'Dawida 14, 50-527 Wrocław', 51.094267, 17.039415, 'campany-ride@camperoo.pl', 358120549);

SELECT SETVAL('seq_company_branches_id', 4);
