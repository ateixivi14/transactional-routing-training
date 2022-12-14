CREATE USER owner;

CREATE DATABASE read;
GRANT ALL PRIVILEGES ON DATABASE read TO owner;

CREATE DATABASE write;
GRANT ALL PRIVILEGES ON DATABASE write TO owner;

CREATE TABLE client
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255),
    deleted     BOOLEAN,
    national_id VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

INSERT INTO client (name, national_id) VALUES ('Alba', '1234567A');