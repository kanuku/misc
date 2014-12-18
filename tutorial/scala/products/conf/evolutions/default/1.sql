# Schemas
# --- !Ups

CREATE TABLE products (
    id serial PRIMARY KEY,
    ean bigint ,
    name varchar,
    description varchar);


CREATE TABLE warehouses (
    id serial PRIMARY KEY,
    name varchar);
    

CREATE TABLE stock_items (
    id serial PRIMARY KEY,
    product_id bigint,
    warehouse_id bigint,
    quantity smallint);



# --- !Downs

DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS warehouses;
DROP TABLE IF EXISTS stock_items;