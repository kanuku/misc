# Schemas
# --- !Ups


CREATE SEQUENCE seq_items_id;

CREATE TABLE items (
    id integer PRIMARY KEY DEFAULT nextval('seq_items_id'),
    name varchar(255),
    description varchar);

 

# --- !Downs

DROP SEQUENCE IF EXISTS seq_items_id; 
DROP TABLE IF EXISTS items; 