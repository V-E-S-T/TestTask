DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (firstName, lastName, birthDay, male) VALUES
  ('Petro', 'Sagaydak', '02-11-1984', TRUE ),
  ('Yakov', 'Betrich', '03-10-1974', TRUE),
  ('Mustafa', 'Mabibulin', '04-09-1988', TRUE),
  ('Igor', 'Kravec', '05-08-1992', TRUE),
  ('Nikolette', 'Faster', '06-07-1991', false),
  ('Omar', 'Barteluya', '07-08-1984', TRUE),
  ('Vladislav', 'Orlov', '08-09-1982', TRUE),
  ('Katerina', 'Sobol', '09-10-1995', FALSE ),
  ('Michael', 'Vasyliev', '10-02-1989', TRUE),
  ('Elena', 'Gorobec', '26-11-1990', FALSE );
