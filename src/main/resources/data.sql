-- Sätta in mockdata för motorcykelbokningssystemet
INSERT INTO address (street, zip_code, city) VALUES
                                                 ('Main Street 1', '12345', 'City A'),
                                                 ('Second Street 2', '23456', 'City B'),
                                                 ('Third Street 3', '34567', 'City C'),
                                                 ('Fourth Street 4', '45678', 'City D'),
                                                 ('Fifth Street 5', '56789', 'City E');

INSERT INTO customer (first_name, last_name, phone_number, date_of_birth, email, username, password, role, address_id) VALUES
                                                                                                                           ('John', 'Doe', '1234567890', '1990-01-01', 'john.doe@example.com', 'johndoe', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', 'ROLE_USER', 1),
                                                                                                                           ('Jane', 'Doe', '2345678901', '1991-02-02', 'jane.doe@example.com', 'janedoe', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', 'ROLE_ADMIN', 2),
                                                                                                                           ('Mike', 'Smith', '3456789012', '1985-03-03', 'mike.smith@example.com', 'mikesmith', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', 'ROLE_USER', 3),
                                                                                                                           ('Anna', 'Johnson', '4567890123', '1975-04-04', 'anna.johnson@example.com', 'annajohnson', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', 'ROLE_USER', 4),
                                                                                                                           ('Tom', 'Brown', '5678901234', '2000-05-05', 'tom.brown@example.com', 'tombrown', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', 'ROLE_ADMIN', 5);

INSERT INTO motorcycle (brand, model, registration_number, price_per_day, availability) VALUES
                                                                                            ('Harley-Davidson', 'Street 750', 'ABC123', 500, TRUE),
                                                                                            ('Yamaha', 'MT-07', 'DEF456', 400, TRUE),
                                                                                            ('Honda', 'CB500F', 'GHI789', 350, FALSE),
                                                                                            ('BMW', 'R1200', 'JKL012', 600, TRUE),
                                                                                            ('Suzuki', 'GSX-R600', 'MNO345', 450, TRUE);

INSERT INTO booking (start_date, end_date, price, customer_id) VALUES
                                                                   ('2024-01-01', '2024-01-05', 2000, 1),
                                                                   ('2024-02-01', '2024-02-07', 2800, 2),
                                                                   ('2024-03-01', '2024-03-03', 1050, 3),
                                                                   ('2024-04-01', '2024-04-05', 2400, 4),
                                                                   ('2024-05-01', '2024-05-04', 1800, 5);

INSERT INTO booking_mc (booking_id, motorcycle_id) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (2, 3),
                                                       (3, 4),
                                                       (4, 5),
                                                       (5, 1);

-- Sätta in mockdata för biografbokningssystemet
INSERT INTO cinema_address (street, zip_code, city) VALUES
                                                        ('Storgatan 1', '12345', 'Stockholm'),
                                                        ('Huvudgatan 2', '23456', 'Göteborg'),
                                                        ('Sidelinjen 3', '34567', 'Malmö'),
                                                        ('Kungsleden 4', '45678', 'Uppsala'),
                                                        ('Vasagatan 5', '56789', 'Lund');

INSERT INTO cinema_customer (enabled, first_name, last_name, phone_number, date_of_birth, email, username, password, role, cinema_address_id) VALUES
                                                                                                                                                  (TRUE, 'John', 'Doe', '0701234567', '1990-01-01', 'john.doe@example.com', 'johndoe', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', 'ROLE_USER', 1),
                                                                                                                                                  (TRUE, 'Jane', 'Smith', '0709876543', '1985-05-05', 'jane.smith@example.com', 'janesmith', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', 'ROLE_ADMIN', 2),
                                                                                                                                                  (TRUE, 'Bob', 'Johnson', '0712345678', '1992-12-12', 'bob.johnson@example.com', 'bobjohnson', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', 'ROLE_USER', 3),
                                                                                                                                                  (TRUE, 'Alice', 'Williams', '0723456789', '1980-04-04', 'alice.williams@example.com', 'alicew', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', 'ROLE_ADMIN', 4);

-- Lägg till filmer i cinema_movie
INSERT INTO cinema_movie (title, length, genre, age_limit) VALUES
                                                               ('Inception', 148, 'SCIENCEFICTION', 13),
                                                               ('The Godfather', 175, 'THRILLER', 15),
                                                               ('The Dark Knight', 152, 'ACTION', 13),
                                                               ('Pulp Fiction', 154, 'THRILLER', 15),
                                                               ('Interstellar', 169, 'SCIENCEFICTION', 13);

-- Lägg till venues i cinema_venue
INSERT INTO cinema_venue (name, max_no_of_guests) VALUES
                                                      ('Venue A', 200),
                                                      ('Venue B', 150),
                                                      ('Venue C', 300),
                                                      ('Venue D', 100),
                                                      ('Venue E', 250);

-- Lägg till bokningar i cinema_booking_venue
INSERT INTO cinema_booking_venue (nr_of_guests, cinema_venue_id, cinema_customer_id, entertainment, date_and_time, total_price_in_SEK, total_price_in_USD) VALUES
                                                                                                                                                               (50, 1, 1, 'Live Band', '2024-12-01 19:00:00', 5000.00, 500.00),  -- 50 gäster i Venue A, kund 1
                                                                                                                                                               (30, 2, 2, 'DJ Set', '2024-12-05 21:00:00', 3000.00, 300.00),     -- 30 gäster i Venue B, kund 2
                                                                                                                                                               (20, 3, 3, 'Comedy Show', '2024-12-10 18:00:00', 2500.00, 250.00), -- 20 gäster i Venue C, kund 3
                                                                                                                                                               (10, 4, 4, 'Theater Performance', '2024-12-15 20:00:00', 2000.00, 200.00); -- 10 gäster i Venue D, kund 4

