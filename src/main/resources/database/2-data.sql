INSERT INTO user () VALUES ();

INSERT INTO cart (owner_id) VALUES (1);

INSERT INTO product (name, stock, price) 
VALUES ('Zapatillas Adidas', 9, 5055.98),
('Zapatillas Reebok', 8, 4044.87), 
('Short Nike', 7, 3033.76);

INSERT INTO product_in_cart (product_id, cart_id, quantity) 
VALUES (1, 1, 9),
(2, 1, 4);
