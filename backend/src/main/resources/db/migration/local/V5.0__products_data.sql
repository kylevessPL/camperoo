--
-- products
--
INSERT INTO products (id, category_id, price, limited, quantity)
VALUES (1, 1, 120.49, FALSE, 3);
INSERT INTO products (id, category_id, price, limited, quantity)
VALUES (2, 2, 215.10, FALSE, 2);
INSERT INTO products (id, category_id, price, limited, quantity)
VALUES (3, 9, 95.00, TRUE, 9);

SELECT SETVAL('seq_products_id', 3);

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
