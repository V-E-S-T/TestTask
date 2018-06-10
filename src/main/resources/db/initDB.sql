DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;
CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  firstName            VARCHAR                 NOT NULL,
  lastName             VARCHAR                 NOT NULL,
  birthDay             DATE                    NOT NULL,
  male                 BOOLEAN                 NOT NULL
);
