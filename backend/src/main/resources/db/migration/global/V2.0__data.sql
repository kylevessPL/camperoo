--
-- locales
--
INSERT INTO locales (id, code)
VALUES (1, 'pl-PL'),
       (2, 'en-US');

--
-- users
--
-- admin, pwd: adminpwd
INSERT INTO users (id, email, password_hash, active)
VALUES (1, 'admin@camperoo.pl', '$argon2id$v=19$m=12,t=3,p=1$eXEwdzN5eTR2NW4wMDAwMA$p7F3YqUv5nYnSDzIs8O2FA', TRUE);

SELECT SETVAL('seq_users_id', 1);

--
-- persons
--
-- admin
INSERT INTO persons (id, first_name, last_name, address_one, zip_code, city, phone_number, user_id)
VALUES (1, 'Admin', 'Adminowski', 'Adminowo 1', '00-001', 'Warszawa', 123456789, 1);

SELECT SETVAL('seq_persons_id', 1);

--
-- roles
--
INSERT INTO roles (id, name)
VALUES (1, 'ADMIN'),
       (2, 'CUSTOMER');

--
-- role_descriptions
--
INSERT INTO role_descriptions (id, description, locale_id, role_id)
VALUES (1, 'Konto administratora', 1, 1),
       (2, 'Administrator account', 2, 1),
       (3, 'Konto klienta', 1, 2),
       (4, 'Customer account', 2, 2);

--
-- users_roles
--
INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1);

--
-- product_categories
--
INSERT INTO product_categories (id, code)
VALUES (1, 'TRAVEL_TRAILER'),
       (2, 'FIFTH_WHEEL'),
       (3, 'LIGHTWEIGHT_TRAILER'),
       (4, 'TOY_HAULER'),
       (5, 'EXPANDABLE_TRAILER'),
       (6, 'CLASS_A_MOTORHOME'),
       (7, 'CLASS_B_CAMPER_VAN'),
       (8, 'CLASS_C_MOTORHOME'),
       (9, 'ACCESSORY');

SELECT SETVAL('seq_product_categories_id', 9);

--
-- product_category_names
--
INSERT INTO product_category_names (id, name, locale_id, product_category_id)
VALUES (1, 'Przyczepy podróżne', 1, 1),
       (2, 'Travel Trailers', 2, 1),
       (3, 'Pięciokołowce', 1, 2),
       (4, 'Fifth Wheels', 2, 2),
       (5, 'Lekkie przyczepy', 1, 3),
       (6, 'Lightweight Trailers', 2, 3),
       (7, 'Przyczepy z garażem', 1, 4),
       (8, 'Toy Haulers', 2, 4),
       (9, 'Powiększane kampery', 1, 5),
       (10, 'Expandable Trailers', 2, 5),
       (11, 'Kampery mieszkalne – klasa A', 1, 6),
       (12, 'Class A Motorhomes', 2, 6),
       (13, 'Kampery mieszkalne – klasa B', 1, 7),
       (14, 'Class B Camper Vans', 2, 7),
       (15, 'Kampery mieszkalne – klasa C', 1, 8),
       (16, 'Class C Motorhomes', 2, 8),
       (17, 'Akcesoria', 1, 9),
       (18, 'Accessories', 2, 9);

SELECT SETVAL('seq_product_category_names_id', 18);

--
-- product_category_descriptions
--
INSERT INTO product_category_descriptions (id, description, locale_id, product_category_id)
VALUES (1,
        'Przyczepy podróżne mogą być holowane przez różne pojazdy i są przeznaczone do rozmaitych zastosowań, zarówno do holowania czy wyjazdów wakacyjnych, jak i również jako pełnoprawne samochody kempingowe.',
        1, 1),
       (2,
        'Travel trailers can be towed by a variety of vehicles and are made for a variety of uses, from hauling to vacationing to full-time RVing.',
        2, 1),
       (3,
        'Popularne wśród pełnoprawnych samochodów kempingowych, pięciokołowce zostały zaprojektowane z myślą o łatwiejszym i bardziej stabilnym holowaniu. Przestrzeń mieszkalna została zmaksymalizowana dzięki przestrzeni nad kabiną.',
        1, 2),
       (4,
        'Popular among full-time RVers, fifth wheels are designed for easier, more stable towing. Living space is maximized with over-cab space.',
        2, 2),
       (5,
        'Pełne udogodnień, lekkie przyczepy zaspokajają rosnący popyt na mniejsze, bardziej paliwooszczędne przyczepy, które można z łatwością holować z pomocą mniejszych pojazdów.',
        1, 3),
       (6,
        'Still packed with amenities, lightweights satisfy the growing demand for smaller, more fuel-efficient trailers that are easily towed by smaller vehicles.',
        2, 3),
       (7,
        'Dostępne jako klasa A, klasa C, przyczepy podróżne lub pięciokołowce, przyczepy z garażem zapewniają miejsce do spania i przestrzeń na pojazdy — od motocykli po pełnowymiarowe quady.',
        1, 4),
       (8,
        'Available as Class A, Class C, Travel Trailers or Fifth Wheels, toy haulers give you a place to sleep and a place for your toys — everything from motorcycles to full-size ATVs.',
        2, 4),
       (9,
        'Powiększane kampery obejmują rozsuwane, zmotoryzowane lub doczepiane kampery. Wysuwane elementy i dodatki zwiększają przestrzeń mieszkalną oraz pozwalają odetchnąć świeżym powietrzem.',
        1, 5),
       (10,
        'Expandables include pop-up campers, motorized, and towable RVs. Slide-outs or add-ons increase living space, or bring in the feel of the outdoors.',
        2, 5),
       (11,
        'Uznawane za jedne z największych tego typu samochodów, kampery mieszkalne klasy A są przestronne, luksusowe i stworzone, aby zabierać rodzinę na dalekie podróże.',
        1, 6),
       (12,
        'Among the largest RVs on the road, Class A motorhomes are spacious, luxurious, and made to take any sized family on long-distance trips.',
        2, 6),
       (13,
        'Znane po prostu jako samochody kempingowe, kampery mieszkalne klasy B są kompaktowe, ale jednocześnie posiadają kuchnię, łazienkę oraz sypialnię.',
        1, 7),
       (14,
        'Commonly known as camper vans, Class B motorhomes are compact, yet still feature a kitchen, a bathroom, and a bedroom.',
        2, 7),
       (15,
        'Klasy C można rozpoznać po miejscach do spania / schowkach nad kabiną, które zapewniają dodatkową przestrzeń mieszkalną dla każdej rodziny. Oferując podobne wyposażenie co klasa A, są jednak nieco mniejsze, ale zapewniają szeroki zakres funkcji.',
        1, 8),
       (16,
        'Class Cs are recognizable by their over-cab sleeping / storage areas which provide additional living space for families of all sizes. Offering much of the craftsmanship of Class As, Class Cs are smaller in size and offer a wide range of features.',
        2, 8),
       (17, 'Dodatkowe akcesoria dla samochodów kempingowych. Dostępne tylko przy zamówieniu kampera.', 1, 9),
       (18, 'Additional accessories for RVs. Cannot be ordered separately.', 2, 9);

SELECT SETVAL('seq_product_category_descriptions_id', 18);

--
-- delivery_types
--
INSERT INTO delivery_types (id, code)
VALUES (1, 'DOOR_TO_DOOR'),
       (2, 'SELF_PICKUP');

--
-- delivery_type_names
--
INSERT INTO delivery_type_names (id, name, locale_id, delivery_type_id)
VALUES (1, 'Door-to-door', 1, 1),
       (2, 'Door-to-door', 2, 1),
       (3, 'Odbiór osobisty', 1, 2),
       (4, 'Self pick-up', 2, 2);

--
-- delivery_type_descriptions
--
INSERT INTO delivery_type_descriptions (id, description, locale_id, delivery_type_id)
VALUES (1, 'Dostawa door-to-door', 1, 1),
       (2, 'Door-to-door delivery', 2, 1),
       (3, 'Odbiór osobisty ', 1, 2),
       (4, 'Self pick-up when ready', 2, 2);

--
-- order_statuses
--
INSERT INTO order_statuses (id, code)
VALUES (1, 'PLACED'),
       (2, 'PROCESSED'),
       (3, 'IN_TRANSIT'),
       (4, 'PICKUP_READY'),
       (5, 'COMPLETED'),
       (6, 'CANCELED');

--
-- order_status_names
--
INSERT INTO order_status_names (id, name, locale_id, order_status_id)
VALUES (1, 'Złożone', 1, 1),
       (2, 'Placed', 2, 1),
       (3, 'W realizacji', 1, 2),
       (4, 'Processed', 2, 2),
       (5, 'W transporcie', 1, 3),
       (6, 'In transit', 2, 3),
       (7, 'Do odbioru', 1, 4),
       (8, 'Ready for pickup', 2, 4),
       (9, 'Zrealizowane', 1, 5),
       (10, 'Completed', 2, 5),
       (11, 'Anulowane', 1, 6),
       (12, 'Canceled', 2, 6);

--
-- order_status_descriptions
--
INSERT INTO order_status_descriptions (id, description, locale_id, order_status_id)
VALUES (1, 'Zamówienie zostało złożone i oczekuje na potwierdzenie', 1, 1),
       (2, 'Order has been placed and is waiting for confirmation', 2, 1),
       (3, 'Zamówienie zostało potwierdzone i jest w trakcie realizacji', 1, 2),
       (4, 'Order has been confirmed and is currently being processed', 2, 2),
       (5, 'Zamówienie jest w trakcie dostawy pod wskazany adres', 1, 3),
       (6, 'Order is in transit to the indicated address', 2, 3),
       (7, 'Zamówienie jest gotowe do odbioru w wybranym oddziale ', 1, 4),
       (8, 'Order is ready for pick-up at the selected branch', 2, 4),
       (9, 'Zamówienie zostało zrealizowane', 1, 5),
       (10, 'Order has been completed', 2, 5),
       (11, 'Zamówienie zostało anulowane', 1, 6),
       (12, 'Order has been canceled', 2, 6);

--
-- payment_types
--
INSERT INTO payment_types (id, code)
VALUES (1, 'BANK_TRANSFER');

--
-- payment_type_names
--
INSERT INTO payment_type_names (id, name, locale_id, payment_type_id)
VALUES (1, 'Przelew bankowy', 1, 1),
       (2, 'Bank transfer', 2, 1);

--
-- payment_statuses
--
INSERT INTO payment_statuses (id, code)
VALUES (1, 'INITIATED'),
       (2, 'SUCCEEDED'),
       (3, 'FAILED'),
       (4, 'EXPIRED'),
       (5, 'CANCELED');

--
-- payment_status_names
--
INSERT INTO payment_status_names (id, name, locale_id, payment_status_id)
VALUES (1, 'Rozpoczęta', 1, 1),
       (2, 'Initiated', 2, 1),
       (3, 'Udana', 1, 2),
       (4, 'Succeeded', 2, 2),
       (5, 'Nieudana', 1, 3),
       (6, 'Failed', 2, 3),
       (7, 'Wygasła', 1, 4),
       (8, 'Expired', 2, 4),
       (9, 'Anulowana', 1, 5),
       (10, 'Canceled', 2, 5);

--
-- payment_status_descriptions
--
INSERT INTO payment_status_descriptions (id, description, locale_id, payment_status_id)
VALUES (1, 'Oczekiwanie na zaksięgowanie płatności', 1, 1),
       (2, 'Payment has been initiated', 2, 1),
       (3, 'Płatność zakończona sukcesem', 1, 2),
       (4, 'Payment has been finished with success', 2, 2),
       (5, 'Payment zakończona niepowodzeniem', 1, 3),
       (6, 'Payment has been finished with failure', 2, 3),
       (7, 'Termin płatności minął', 1, 4),
       (8, 'Payment deadline has been exceeded', 2, 4),
       (9, 'Płatność została anulowana', 1, 5),
       (10, 'Payment has been cancelled', 2, 5);
