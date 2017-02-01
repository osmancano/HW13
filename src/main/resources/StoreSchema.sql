CREATE SCHEMA Store;

CREATE TABLE gusers
(
    user_name VARCHAR(30) PRIMARY KEY NOT NULL,
    password VARCHAR(30) NOT NULL,
    display_name VARCHAR(40),
    user_id INTEGER DEFAULT nextval('users_user_id_seq'::regclass) NOT NULL
);
CREATE TABLE items
(
    item_id BIGINT DEFAULT nextval('"Store".items_item_id_seq'::regclass) PRIMARY KEY NOT NULL,
    item_name VARCHAR(40) NOT NULL,
    item_price DOUBLE PRECISION,
    item_qty INTEGER,
    item_isle INTEGER,
    item_category INTEGER,
    user_id INTEGER NOT NULL
);
CREATE UNIQUE INDEX users_user_id_uindex ON gusers (user_id);

INSERT INTO "Store".gusers (user_name, password, display_name) VALUES ('canohibro@gmail.com', 'nothing', 'Osman');
INSERT INTO "Store".gusers (user_name, password, display_name) VALUES ('jason.skipper@gmail.com', '12345', 'Jason');
