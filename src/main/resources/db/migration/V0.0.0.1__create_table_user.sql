CREATE TABLE spring_security.user (
    id INT NOT NULL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(100) NOT NULL
);

INSERT INTO spring_security.user (id, username, password, role) VALUES (1, 'user', 'pass', 'ROLE_ADMIN');
INSERT INTO spring_security.user (id, username, password, role) VALUES (2, 'user2', 'pass2', 'ROLE_USER');
