DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name     VARCHAR                          NOT NULL,
    email    VARCHAR                          NOT NULL,
    password VARCHAR                          NOT NULL,
    enabled  BOOL                DEFAULT TRUE NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR NOT NULL,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL
);

CREATE TABLE menus
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name    VARCHAR NOT NULL,
    price   REAL    NOT NULL,
    rest_id INTEGER NOT NULL,
    FOREIGN KEY (rest_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
    id        INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id   INTEGER   NOT NULL,
    rest_id   INTEGER   NOT NULL,
    date_time TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (rest_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX vote_unique_user_rest_datetime_idx ON votes (user_id, rest_id, date_time);