CREATE SCHEMA readstack DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci;
USE readstack;

CREATE TABLE IF NOT EXISTS category
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(500)
    );

CREATE TABLE IF NOT EXISTS discovery
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(80)  NOT NULL,
    url         VARCHAR(500) NOT NULL UNIQUE,
    description VARCHAR(500) NOT NULL,
    date_added  DATETIME     NOT NULL,
    category_id INT          NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category (id)
    );

CREATE TABLE IF NOT EXISTS user
(
    id                INT PRIMARY KEY AUTO_INCREMENT,
    username          VARCHAR(50)  NOT NULL UNIQUE,
    email             VARCHAR(100) NOT NULL UNIQUE,
    registration_date DATETIME     NOT NULL,
    password          VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS user_role
(
    username  VARCHAR(50) NOT NULL,
    role_name VARCHAR(20) NOT NULL DEFAULT 'USER',
    PRIMARY KEY (username, role_name),
    FOREIGN KEY (username) REFERENCES user (username)
    );

ALTER TABLE discovery
    ADD user_id INT,
    ADD FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE discovery
    MODIFY COLUMN user_id INT NOT NULL;

CREATE TABLE vote
(
    user_id      INT                 NOT NULL,
    discovery_id INT                 NOT NULL,
    type         ENUM ('UP', 'DOWN') NOT NULL,
    date_added   DATETIME            NOT NULL,
    PRIMARY KEY (user_id, discovery_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (discovery_id) REFERENCES discovery (id)
);

/* użytkownicy admin/admin i user/pass */
INSERT INTO user (username, email, registration_date, password)
VALUES ('admin', 'admin@example.com', '2022-11-30 09:37:22',
        '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918'),
       ('user', 'user@example.com', '2022-11-30 10:37:22',
        'd74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1');

INSERT INTO user_role (username, role_name)
VALUES ('admin', 'ADMIN'),
       ('user', 'USER');

INSERT INTO category (name, description)
VALUES ('Rozrywka',
        'Ultrices posuere cubilia curae duis eleifend donec ut dolor morbi vel lectus in quam fringilla faucibus accumsan odio curabitur convallis duis consequat dui nec nisi volutpat.'),
       ('Biznes',
        'Curae nulla dapibus dolor vel est donec aliquam augue quam sollicitudin vitae consectetuer eget rutrum at lorem integer tincidunt.'),
       ('Polityka',
        'Curabitur convallis duis consequat dui nec nisi volutpat eleifend donec ut dolor morbi. Ultrices posuere cubilia curae duis faucibus accumsan odio.');

INSERT INTO discovery (title, url, description, date_added, category_id, user_id)
VALUES ('Cascade and inheritance', 'https://developer.mozilla.org/en-US/docs/Learn/CSS/Building_blocks/Cascade_and_inheritance',
        'The aim of this lesson is to develop your understanding of some of the most fundamental concepts of CSS — the cascade, specificity, and inheritance — which control how CSS is applied to HTML and how conflicts between style declarations are resolved.',
        '2023-01-11 21:20:34', 3, 2),
       ('Pomodoro tracker', 'https://pomodoro-tracker.com',
        'Technika Pomodoro to metoda zarządzania czasem, która może być stosowana do ukończenia dowolnego zadania. Celem metody Pomodoro jest użycie czasu jako wartościowego sprzymierzeńca w osiąganiu naszych celów. Technika ta pozwala nieustannie usprawniać sposób w jaki uczymy się i pracujemy.',
        '2023-01-11 21:18:15', 1, 1),
       ('Mockups', 'https://moqups.com',
        'A streamlined web app that helps you create and collaborate in real-time on wireframes, mockups, diagrams and prototypes.',
        '2023-01-02 22:40:57', 1, 2);