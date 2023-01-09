--
-- products
--
INSERT INTO products (id, category_id, price, quantity)
VALUES (1, 1, 120.49, 3);
INSERT INTO products (id, category_id, price, quantity)
VALUES (2, 2, 215.10, 2);
INSERT INTO products (id, category_id, price, quantity)
VALUES (3, 3, 95.00, 9);
INSERT INTO products (id, category_id, price, transportation, quantity)
VALUES (4, 9, 4.50, TRUE, -1);

SELECT SETVAL('seq_products_id', 4);

--
-- product_names
--
INSERT INTO product_names (id, name, locale_id, product_id)
VALUES (NEXTVAL('seq_product_names_id'), 'Produkt 1', 1, 1);
INSERT INTO product_names (id, name, locale_id, product_id)
VALUES (NEXTVAL('seq_product_names_id'), 'Product 1', 2, 1);
INSERT INTO product_names (id, name, locale_id, product_id)
VALUES (NEXTVAL('seq_product_names_id'), 'Produkt 2', 1, 2);
INSERT INTO product_names (id, name, locale_id, product_id)
VALUES (NEXTVAL('seq_product_names_id'), 'Product 2', 2, 2);
INSERT INTO product_names (id, name, locale_id, product_id)
VALUES (NEXTVAL('seq_product_names_id'), 'Produkt 3', 1, 3);
INSERT INTO product_names (id, name, locale_id, product_id)
VALUES (NEXTVAL('seq_product_names_id'), 'Product 3', 2, 3);
INSERT INTO product_names (id, name, locale_id, product_id)
VALUES (NEXTVAL('seq_product_names_id'), 'Transport', 1, 4);
INSERT INTO product_names (id, name, locale_id, product_id)
VALUES (NEXTVAL('seq_product_names_id'), 'Transportation', 2, 4);

--
-- product_descriptions
--
INSERT INTO product_descriptions (id, description, locale_id, product_id)
VALUES (NEXTVAL('seq_product_descriptions_id'), 'Opis produktu 1', 1, 1);
INSERT INTO product_descriptions (id, description, locale_id, product_id)
VALUES (NEXTVAL('seq_product_descriptions_id'), 'Product description 1', 2, 1);
INSERT INTO product_descriptions (id, description, locale_id, product_id)
VALUES (NEXTVAL('seq_product_descriptions_id'), 'Opis produktu 2', 1, 2);
INSERT INTO product_descriptions (id, description, locale_id, product_id)
VALUES (NEXTVAL('seq_product_descriptions_id'), 'Product description 2', 2, 2);
INSERT INTO product_descriptions (id, description, locale_id, product_id)
VALUES (NEXTVAL('seq_product_descriptions_id'), 'Opis produktu 3', 1, 3);
INSERT INTO product_descriptions (id, description, locale_id, product_id)
VALUES (NEXTVAL('seq_product_descriptions_id'), 'Product description 3', 2, 3);
INSERT INTO product_descriptions (id, description, locale_id, product_id)
VALUES (NEXTVAL('seq_product_descriptions_id'), 'Transport. Nie może być zamówiony osobno.', 1, 4);
INSERT INTO product_descriptions (id, description, locale_id, product_id)
VALUES (NEXTVAL('seq_product_descriptions_id'), 'Transportation. Cannot be ordered separately.', 2, 4);
