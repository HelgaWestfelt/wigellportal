-- Skapa tabeller om de inte finns
CREATE TABLE IF NOT EXISTS admin (
                                     id VARCHAR(45) PRIMARY KEY NOT NULL,
    password CHAR(68) NOT NULL,
    active TINYINT NOT NULL
    );

INSERT INTO admin (id, password, active)
SELECT 'admin', '$2a$12$yJ17D0.4gvK8L3jgc8Nhu.0OsQdV6Bm4RTm48Y.oSlkAdSM16t/ru', 1
    WHERE NOT EXISTS (SELECT 1 FROM admin WHERE id = 'admin');

INSERT INTO admin (id, password, active)
SELECT 'alfa', '{bcrypt}$2a$12$fuoRGqj.3vO3.dyQ2Mu4wes3/vwMZoMmPrYbxzx78LAkLlJDCtfne', 1
    WHERE NOT EXISTS (SELECT 1 FROM admin WHERE id = 'alfa');

INSERT INTO admin (id, password, active)
SELECT 'hugo', '{bcrypt}$2a$12$y6vUb2nfIJMRndv31r4Br.9YIZ.iN2OZYudvlnsmu1lQ8vAH5j16q', 1
    WHERE NOT EXISTS (SELECT 1 FROM admin WHERE id = 'hugo');

INSERT INTO admin (id, password, active)
SELECT 'bastian', '{bcrypt}$2a$12$8tzTmpf1fwFEuScYm2kO0eCCIqUcD4RWxqPmenlp63qWg668QtDSC', 1
    WHERE NOT EXISTS (SELECT 1 FROM admin WHERE id = 'bastian');

CREATE TABLE IF NOT EXISTS roles (
                                     id VARCHAR(45) NOT NULL,
    role VARCHAR(45) NOT NULL,
    CONSTRAINT authorities5_idx_1 UNIQUE (id, role),
    CONSTRAINT authorities5_ibfk_1 FOREIGN KEY(id) REFERENCES admin (id)
    );
INSERT INTO roles (id, role)
SELECT 'admin', 'ROLE_ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE id = 'admin' AND role = 'ROLE_ADMIN');

INSERT INTO roles (id, role)
SELECT 'alfa', 'ROLE_ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE id = 'alfa' AND role = 'ROLE_ADMIN');

INSERT INTO roles (id, role)
SELECT 'alfa', 'ROLE_USER'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE id = 'alfa' AND role = 'ROLE_USER');

INSERT INTO roles (id, role)
SELECT 'hugo', 'ROLE_USER'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE id = 'hugo' AND role = 'ROLE_USER');

INSERT INTO roles (id, role)
SELECT 'bastian', 'ROLE_USER'
    WHERE NOT EXISTS (SELECT 1 FROM roles WHERE id = 'bastian' AND role = 'ROLE_USER');

CREATE TABLE IF NOT EXISTS padel_address (
                                             id INT NOT NULL AUTO_INCREMENT,
                                             street VARCHAR(100) NOT NULL,
    zip_code VARCHAR(10) NOT NULL,
    city VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS padel_customer (
                                              id INT NOT NULL AUTO_INCREMENT,
                                              first_name VARCHAR(45),
    last_name VARCHAR(45),
    username VARCHAR(45),
    password VARCHAR(68),
    role VARCHAR(45),
    email VARCHAR(45),
    phone_number VARCHAR(20),
    date_of_birth VARCHAR(20),
    address_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (address_id) REFERENCES padel_address(id)
    );

CREATE TABLE IF NOT EXISTS court (
                                     id INT NOT NULL AUTO_INCREMENT,
                                     name VARCHAR(255),
    location VARCHAR(255),
    price_per_hour INT,
    description VARCHAR(255),
    PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS court_times (
                                           court_id INT NOT NULL,
                                           time TIME NOT NULL,
                                           is_available BIT,
                                           PRIMARY KEY (court_id, time),
    FOREIGN KEY (court_id) REFERENCES court(id)
    );

CREATE TABLE IF NOT EXISTS padel_booking (
                                             id INT NOT NULL AUTO_INCREMENT,
                                             date DATE,
                                             time TIME,
                                             total_price INT,
                                             total_price_eur FLOAT,
                                             currency VARCHAR(255),
    players_count INT,
    court_id INT,
    customer_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (court_id) REFERENCES court(id),
    FOREIGN KEY (customer_id) REFERENCES padel_customer(id)
    );

-- Radera alla poster i rätt ordning
DELETE FROM padel_booking WHERE 1;
DELETE FROM padel_customer WHERE 1;
DELETE FROM court_times WHERE 1;
DELETE FROM court WHERE 1;
DELETE FROM padel_address WHERE 1;
DELETE FROM roles WHERE 1;
DELETE FROM admin WHERE 1;

-- Lägg till adresser
INSERT INTO padel_address (street, zip_code, city) VALUES ('Userstreet 1', '12345', 'Usercity');
INSERT INTO padel_address (street, zip_code, city) VALUES ('Space Needle 1', '98101', 'Seattle');
INSERT INTO padel_address (street, zip_code, city) VALUES ('8 mile', '48226', 'Detroit');
INSERT INTO padel_address (street, zip_code, city) VALUES ('Space Needle 2', '98101', 'Seattle');
INSERT INTO padel_address (street, zip_code, city) VALUES ('Adminstreet 1', '11111', 'Admincity');

-- Lägg till kunder
INSERT INTO padel_customer (first_name, last_name, username, password, role, email, phone_number, date_of_birth, address_id)
VALUES ('User', 'Testsson', 'user', '$2a$12$/Jm2ptrJsvK.AKc0XlGluOMezMArC.AYbeSVfYC2PGL3uysP5O8Ya', 'USER', 'user@gmail.com', '+46123-456-789', '1990-01-01', 1);

INSERT INTO padel_customer (first_name, last_name, username, password, role, email, phone_number, date_of_birth, address_id)
VALUES ('Kam', 'Chancellor', 'Seahawks', '$2a$12$ku8GoYFXS1kIgEBvzJUH0u90VQc7WebHe4uZY5wm1Fw/I27gNEwWe', 'USER', 'Kchancellor@gmail.com', '+46987-654-321', '1988-04-03', 2);

INSERT INTO padel_customer (first_name, last_name, username, password, role, email, phone_number, date_of_birth, address_id)
VALUES ('Amon-Ra', 'St.Brown', 'Detroit', '$2a$12$9OrtN8UIZjvngnxsOfRYKeUBHkGnH9KqIABXdTmszjdgnOyOJYPEW', 'USER', 'ABrown@gmail.com', '+4623-456-7891', '1999-10-24', 3);

INSERT INTO padel_customer (first_name, last_name, username, password, role, email, phone_number, date_of_birth, address_id)
VALUES ('Doug', 'Baldwin', 'Seattle', '$2a$12$.3REAazMTtwj5rWvt6EYheUNO9aQV/lNcaOo6NghM/f0dAwPec0GO', 'USER', 'DBaldwin@gmail.com', '+4687-654-329', '1989-09-21', 4);

INSERT INTO padel_customer (first_name, last_name, username, password, role, email, phone_number, date_of_birth, address_id)
VALUES ('Admin', 'Adminsson', 'admin', '$2a$12$gyHZLSd6p84ZSavY.yY62uewbD7kpPtjQarBByLaW7B.97DX7D.AO', 'ADMIN', 'admin@gmail.com', '+46', '1980-01-01', 5);

-- Lägg till padelbanor
INSERT INTO court (name, location, price_per_hour, description)
VALUES ('Padel Court 1', 'Södertälje', 200, 'Inomhus, grönt gräs med vita linjer, finns omklädningsrum och kamera');

INSERT INTO court (name, location, price_per_hour, description)
VALUES ('Padel Court 2', 'Årsta', 250, 'Inomhus, blått gräs med vita linjer, finns omklädningsrum och kamera');

INSERT INTO court (name, location, price_per_hour, description)
VALUES ('Padel Court 3', 'Älvsjö', 200, 'Inomhus, grönt gräs med röda linjer');

INSERT INTO court (name, location, price_per_hour, description)
VALUES ('Padel Court 4', 'Rättvik', 250, 'Utomhus, grönt gräs med vita linjer');

INSERT INTO court (name, location, price_per_hour, description)
VALUES ('Padel Court 5', 'Haninge', 200, 'Utomhus, grönt gräs med vita linjer, finns omklädningsrum');

-- Lägg till bokningsbara tider för varje padelbana
-- Tiderna är mellan 08:00 och 20:00
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '08:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '09:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '10:00:00', FALSE); -- Bokad tid
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '11:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '12:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '13:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '14:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '15:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '16:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '17:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '18:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '19:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '20:00:00', TRUE);

INSERT INTO court_times (court_id, time, is_available) VALUES (2, '08:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '09:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '10:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '11:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '12:00:00', FALSE); -- Bokad tid
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '13:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '14:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '15:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '16:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '17:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '18:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '19:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '20:00:00', TRUE);

INSERT INTO court_times (court_id, time, is_available) VALUES (3, '08:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '09:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '10:00:00', FALSE);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '11:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '12:00:00', TRUE); -- Bokad tid
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '13:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '14:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '15:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '16:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '17:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '18:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '19:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '20:00:00', TRUE);

INSERT INTO court_times (court_id, time, is_available) VALUES (4, '08:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '09:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '10:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '11:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '12:00:00', FALSE);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '13:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '14:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '15:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '16:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '17:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '18:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '19:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '20:00:00', TRUE);

INSERT INTO court_times (court_id, time, is_available) VALUES (5, '08:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '09:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '10:00:00', FALSE);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '11:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '12:00:00', TRUE); -- Bokad tid
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '13:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '14:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '15:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '16:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '17:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '18:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '19:00:00', TRUE);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '20:00:00', TRUE);

-- Lägg till bokningar
INSERT INTO padel_booking (date, time, total_price, total_price_eur, currency, players_count, court_id, customer_id)
VALUES ('2024-10-03', '10:00:00', 200, 19.0, 'SEK', 4, 1, 1);

INSERT INTO padel_booking (date, time, total_price, total_price_eur, currency, players_count, court_id, customer_id)
VALUES ('2024-10-03', '12:00:00', 250, 23.75, 'SEK', 4, 2, 2);

INSERT INTO padel_booking (date, time, total_price, total_price_eur, currency, players_count, court_id, customer_id)
VALUES ('2024-10-03', '10:00:00', 200, 19.0, 'SEK', 4, 3, 3);

INSERT INTO padel_booking (date, time, total_price, total_price_eur, currency, players_count, court_id, customer_id)
VALUES ('2024-10-03', '12:00:00', 250, 23.75, 'SEK', 4, 4, 4);

INSERT INTO padel_booking (date, time, total_price, total_price_eur, currency, players_count, court_id, customer_id)
VALUES ('2024-10-03', '10:00:00', 200, 19.0, 'SEK', 4, 5, 5);
