-- Sätta in mockdata för motorcykelbokningssystemet
INSERT INTO address (street, zip_code, city) VALUES
                                                 ('Main Street 1', '12345', 'City A'),
                                                 ('Second Street 2', '23456', 'City B'),
                                                 ('Third Street 3', '34567', 'City C'),
                                                 ('Fourth Street 4', '45678', 'City D'),
                                                 ('Fifth Street 5', '56789', 'City E');

INSERT INTO customer (first_name, last_name, phone_number, date_of_birth, email, username, password, enabled, role, address_id) VALUES
('John', 'Doe', '1234567890', '1990-01-01', 'john.doe@example.com', 'johndoe', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', true, 'ROLE_USER', 1),
('Jane', 'Doe', '2345678901', '1991-02-02', 'jane.doe@example.com', 'janedoe', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', true, 'ROLE_ADMIN', 2),
('Mike', 'Smith', '3456789012', '1985-03-03', 'mike.smith@example.com', 'mikesmith', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', true, 'ROLE_USER', 3),
('Anna', 'Johnson', '4567890123', '1975-04-04', 'anna.johnson@example.com', 'annajohnson', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', true, 'ROLE_USER', 4),
('Tom', 'Brown', '5678901234', '2000-05-05', 'tom.brown@example.com', 'tombrown', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', true, 'ROLE_ADMIN', 5),
('Maria', 'Wiklund', '2345678901', '1991-02-02', 'maria@example.com', 'maria_w', '$2a$10$c1B932Y8UJfcKj3PZtrbmO27C1Sz8llRijjEsAOuyetr6L6TWCN8q', true, 'ROLE_ADMIN', 2),
('Anna', 'Andersson', '3456789012', '1985-03-03', 'anna@example.com', 'anna_a', '$2a$10$8Z0mJH74HKLdPFtbLwacKORdqeRSvfaQifav7yuWynHZ9tx9dpQYG', true, 'ROLE_USER', 3);


INSERT INTO motorcycle (brand, model, registration_number, price_per_day, availability) VALUES
                                                                                            ('Harley-Davidson', 'Street 750', 'ABC123', 500, TRUE),
                                                                                            ('Yamaha', 'MT-07', 'DEF456', 400, TRUE),
                                                                                            ('Honda', 'CB500F', 'GHI789', 350, FALSE),
                                                                                            ('BMW', 'R1200', 'JKL012', 600, TRUE),
                                                                                            ('Suzuki', 'GSX-R600', 'MNO345', 450, TRUE);

INSERT INTO mc_booking (start_date, end_date, price, customer_id) VALUES
                                                                   ('2024-01-01', '2024-01-05', 2000, 1),
                                                                   ('2024-02-01', '2024-02-07', 2800, 2),
                                                                   ('2024-03-01', '2024-03-03', 1050, 3),
                                                                   ('2024-04-01', '2024-04-05', 2400, 4),
                                                                   ('2024-05-01', '2024-05-04', 1800, 5);


-- Lägg till filmer i cinema_movie
INSERT INTO cinema_movie (title, length, genre, age_limit) VALUES
                                                               ('Inception', 148, 'SCIENCEFICTION', 13),
                                                               ('The Godfather', 175, 'THRILLER', 15),
                                                               ('The Dark Knight', 152, 'ACTION', 13),
                                                               ('Pulp Fiction', 154, 'THRILLER', 15),
                                                               ('Interstellar', 169, 'SCIENCEFICTION', 13);

-- Lägg till venues i cinema_venue
INSERT INTO cinema_venue (name, max_no_of_guests, facilities) VALUES
                                                                  ('Hepburn', 200, '3D Projection, Dolby Atmos Sound, Luxury seating'),
                                                                  ('Chaplin', 150, 'Wheelchair accessible, Hearing loops, Catering services'),
                                                                  ('Monroe', 300, 'IMAX screen, Bar, Inseat food and beverage service, Dolby Atmos Sound'),
                                                                  ('Garland', 60, '4K Ultra HD Projection, Bar, Hearing loops'),
                                                                  ('Brando', 250, 'Dolby Atmos Sound, Wheelchair accessible, Hearing loops, 3D projection, Cafe and bar');


-- Lägg till bokningar i cinema_booking_venue
INSERT INTO cinema_booking_venue (nr_of_guests, cinema_venue_id, customer_id, entertainment, date_and_time, total_price_in_SEK, total_price_in_USD) VALUES
 (200, 1, 1, 'Live Band', '2024-12-01 19:00:00', 5000.00, 500.00),
 (135, 2, 2, 'DJ Set', '2024-12-05 21:00:00', 3000.00, 300.00),
 (280, 3, 3, 'Comedy Show', '2024-12-10 18:00:00', 2500.00, 250.00),
(45, 4, 4, 'Theater Performance', '2024-12-15 20:00:00', 2000.00, 200.00);


INSERT INTO destination (city, country) VALUES
('Paris', 'France'),
('New York', 'USA'),
('Tokyo', 'Japan'),
('Sydney', 'Australia'),
('Cape Town', 'South Africa');

-- Infoga resor om de inte redan finns
INSERT INTO trip (hotel, destination_id, weekly_price) VALUES
('Hotel Eiffel', 1, 500.00),
('Manhattan Suite', 2, 700.00),
('Tokyo Plaza', 3, 600.00),
('Harbour Hotel', 4, 800.00),
('Oceanview Lodge', 5, 550.00);

INSERT INTO travel_booking (travel_date, return_date, number_of_weeks, total_price_sek, total_price_pln, booking_date, trip_id, customer_id) VALUES
('2024-10-01', '2024-10-08', 1, 5000.00, 2000.00, '2024-09-25 10:30:00', 1, 2),
('2024-11-15', '2024-11-22', 1, 7000.00, 2800.00, '2024-10-10 14:20:21', 2, 2),
('2024-12-05', '2024-12-19', 2, 12000.00, 4800.00, '2024-11-01 09:00:00', 3, 3),
('2025-01-10', '2025-01-17', 1, 8000.00, 3200.00, '2024-12-01 11:46:03', 4, 4),
('2025-02-20', '2025-03-06', 2, 11000.00, 4400.00, '2025-01-15 08:30:00', 5, 5);