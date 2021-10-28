DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS person;

CREATE TABLE person
(
    id         BIGINT IDENTITY PRIMARY KEY,
    name       VARCHAR(256) NOT NULL,
    birth_date DATE         NOT NULL
);

CREATE TABLE car
(
    id          BIGINT IDENTITY PRIMARY KEY,
    model       VARCHAR(256) NOT NULL,
    horse_power INTEGER      NOT NULL,
    owner_id    BIGINT       NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES person (id) ON DELETE CASCADE
);