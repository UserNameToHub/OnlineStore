-- drop table order_item CASCADE;

CREATE TABLE IF NOT EXISTS balance
(
    Id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    userId BIGINT,
    amount DECIMAL(10, 2) default 0.00
);