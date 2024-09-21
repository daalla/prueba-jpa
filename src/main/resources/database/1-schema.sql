CREATE TABLE user
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE cart
(
    owner_id BIGINT PRIMARY KEY,
    FOREIGN KEY (owner_id) REFERENCES user (id)
);

CREATE TABLE product
(
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(100)   NOT NULL,
    stock BIGINT         NOT NULL,
    price DOUBLE NOT NULL
);

CREATE TABLE product_in_cart
(
    product_id BIGINT,
    cart_id    BIGINT,
    quantity   BIGINT NOT NULL,
    PRIMARY KEY (product_id, cart_id),
    FOREIGN KEY (product_id) REFERENCES product (id),
    FOREIGN KEY (cart_id) REFERENCES cart (owner_id)
);
