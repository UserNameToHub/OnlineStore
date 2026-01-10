-- drop table order_item CASCADE;

CREATE TABLE IF NOT EXISTS item
(
    Id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(250),
    description VARCHAR(500),
    image_path VARCHAR(250),
    count INT default 0,
    Price DECIMAL(10, 2) default 0.00
);

CREATE TABLE IF NOT EXISTS orders
(
    Id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT REFERENCES user (id)  NOT NULL -- + принадлежность заказа к пользователю
);

CREATE TABLE IF NOT EXISTS order_item
(
    order_id INTEGER REFERENCES orders (id) NOT NULL,
    item_id  INTEGER REFERENCES item (id)  NOT NULL,
    PRIMARY KEY (order_id, item_id)
);

CREATE TABLE IF NOT EXISTS cart
(
    Id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    item_id  BIGINT REFERENCES item (id)  NOT NULL,
    user_id BIGINT REFERENCES user (id)  NOT NULL, -- + принадлежность к пользователю
    order_id BIGINT REFERENCES orders (id) -- + принадлежность к заказу, чтобы определить актуальную корзину пользователя
);

CREATE TABLE IF NOT EXISTS user
(
    Id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS role
(
    Id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS user_role
(
    user_id INTEGER REFERENCES user (id) NOT NULL,
    role_id  INTEGER REFERENCES role (id)  NOT NULL,
    PRIMARY KEY (user_id, role_id)
);