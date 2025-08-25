---- DDL
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
    Id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY
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
    item_id  BIGINT REFERENCES item (id)  NOT NULL

);

---- DML
insert into item (title, description, image_path, Price)
values ('Iphone 16 126 black',
        'iPhone 16 создан вместе с Apple Intelligence',
        '/Users/aleksandrgineyka/Desktop/store/catalog/Iphone.jpg',
        62000.00);

insert into item (title, description, image_path, Price)
values ('Iphone 16 256 black',
        'iPhone 16 создан вместе с Apple Intelligence',
        'image/Iphone.jpg',
        82000.00);