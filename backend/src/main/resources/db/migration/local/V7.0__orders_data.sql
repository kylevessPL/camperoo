--
-- orders
--
INSERT INTO orders (id, placement_date, status_change_date, subtotal_price, total_price, discount_id, address, latitude,
                    longitude, company_branch_id, delivery_type_id, status_id, user_id)
VALUES (NEXTVAL('seq_orders_id'), TO_TIMESTAMP('2022-11-16 07:50:10-02:00', 'YYYY-MM-DD HH24:MI:SSTZH:TZM'),
        TO_TIMESTAMP('2022-11-18 14:23:05-01:00', 'YYYY-MM-DD HH24:MI:SSTZH:TZM'), 645.69, 613.41, 1,
        'Street 4/19, 00-001 City', 52.200000, 21.166667, 1, 1, 3, 2);

--
-- orders_products
--
INSERT INTO orders_products (order_id, product_id, quantity, total_price)
VALUES (1, 1, 1, 120.49);
INSERT INTO orders_products (order_id, product_id, quantity, total_price)
VALUES (1, 2, 2, 430.20);
INSERT INTO orders_products (order_id, product_id, quantity, total_price)
VALUES (1, 3, 1, 95.00);
