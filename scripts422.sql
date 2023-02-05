CREATE TABLE cars
(
    id    SERIAL PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    price INTEGER
);

CREATE TABLE persons
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(100) NOT NULL DEFAULT 'ron',
    age     INTEGER      NOT NULL,
    licence BOOLEAN,
    car_id INTEGER REFERENCES cars (id)
);