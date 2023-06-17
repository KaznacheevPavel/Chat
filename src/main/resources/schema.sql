CREATE TABLE account
(
    id uuid PRIMARY KEY,
    username varchar(16) UNIQUE NOT NULL,
    password varchar NOT NULL
);

CREATE TABLE message
(
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    sender_id uuid NOT NULL REFERENCES account(id),
    sent_timestamp timestamp NOT NULL,
    text varchar(256) NOT NULL
);

CREATE TABLE file
(
    id uuid PRIMARY KEY,
    content_type varchar NOT NULL,
    data oid NOT NULL,
    name varchar NOT NULL,
    size bigint NOT NULL
);