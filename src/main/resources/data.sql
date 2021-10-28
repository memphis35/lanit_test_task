DELETE
FROM car;
DELETE
FROM person;

INSERT INTO person
VALUES (1, 'Martin Luther King', '1929-01-15'),
       (2, 'Charles Darwin', '1809-02-12'),
       (3, 'George Orwell', '1903-06-25'),
       (4, 'Elon Musk', '1971-06-28'),
       (5, 'Dummy Child', '2015-01-01');

INSERT INTO car
VALUES (1, 'BENTLEY-Azure Mark 1', 360, 3),
       (2, 'CADILLAC-DeVille', 325 , 1),
       (3, 'FERRARI-250 GT Coupe', 395 , 3),
       (4, 'TESLA-Model S', 360, 4)