--
-- discounts
--
INSERT INTO discounts (id, code, value)
VALUES (1, 'BONUS5', 5);

SELECT SETVAL('seq_discounts_id', 1);

--
-- discount_descriptions
--
INSERT INTO discount_descriptions (id, description, locale_id, discount_id)
VALUES (NEXTVAL('seq_discount_descriptions_id'), '5% zniżki', 1, 1),
       (NEXTVAL('seq_discount_descriptions_id'), '5% discount', 2, 1);
