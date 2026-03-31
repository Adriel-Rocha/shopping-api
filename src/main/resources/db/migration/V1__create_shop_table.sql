CREATE SCHEMA IF NOT EXISTS shopping;

CREATE TABLE shopping.shop (
    id BIGSERIAL PRIMARY KEY,
    user_identifier VARCHAR(100) NOT NULL,
    date TIMESTAMP NOT NULL,
    total NUMERIC(10, 2) NOT NULL
);

CREATE TABLE shopping.item (
    shop_id BIGSERIAL REFERENCES shopping.shop(id),
    product_identifier VARCHAR(100) NOT NULL,
    price NUMERIC(10, 2) NOT NULL
);
