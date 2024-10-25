-- Sätta in mockdata för motorcykelbokningssystemet
INSERT INTO address (street, zip_code, city) VALUES
                                                 ('Main Street 1', '12345', 'City A'),
                                                 ('Second Street 2', '23456', 'City B'),
                                                 ('Third Street 3', '34567', 'City C'),
                                                 ('Fourth Street 4', '45678', 'City D'),
                                                 ('Fifth Street 5', '56789', 'City E'),
                                                 ('Userstreet 1', '12345', 'Usercity'),
                                                 ('Adminstreet 1', '11111', 'Admincity');


INSERT INTO customer (first_name, last_name, phone_number, date_of_birth, email, username, password, enabled, role, address_id) VALUES
('John', 'Doe', '1234567890', '1990-01-01', 'john.doe@example.com', 'johndoe', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', true, 'ROLE_USER', 1),
('Jane', 'Doe', '2345678901', '1991-02-02', 'jane.doe@example.com', 'janedoe', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', true, 'ROLE_ADMIN', 2),
('Mike', 'Smith', '3456789012', '1985-03-03', 'mike.smith@example.com', 'mikesmith', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', true, 'ROLE_USER', 3),
('Anna', 'Johnson', '4567890123', '1975-04-04', 'anna.johnson@example.com', 'annajohnson', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', true, 'ROLE_USER', 4),
('Tom', 'Brown', '5678901234', '2000-05-05', 'tom.brown@example.com', 'tombrown', '$2a$10$aG3vGynwbbJ/mob5nBp3euA62CzvjBwRJVQrCgn3TeH6gkB3Eh3Mq', true, 'ROLE_ADMIN', 5),
('Maria', 'Wiklund', '2345678901', '1991-02-02', 'maria@example.com', 'maria_w', '$2a$10$c1B932Y8UJfcKj3PZtrbmO27C1Sz8llRijjEsAOuyetr6L6TWCN8q', true, 'ROLE_ADMIN', 2),
('Anna', 'Andersson', '3456789012', '1985-03-03', 'anna@example.com', 'anna_a', '$2a$10$8Z0mJH74HKLdPFtbLwacKORdqeRSvfaQifav7yuWynHZ9tx9dpQYG', true, 'ROLE_USER', 3),
('User', 'Testsson', '+46123-456-789', '1990-01-01', 'user@gmail.com', 'user', '$2a$12$/Jm2ptrJsvK.AKc0XlGluOMezMArC.AYbeSVfYC2PGL3uysP5O8Ya', true, 'ROLE_USER', 6),
('Admin', 'Adminsson', '+46', '1980-01-01', 'admin@gmail.com', 'admin', '$2a$12$gyHZLSd6p84ZSavY.yY62uewbD7kpPtjQarBByLaW7B.97DX7D.AO', true, 'ROLE_ADMIN', 7);


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

INSERT INTO booking_mc (booking_id, motorcycle_id) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (2, 3),
                                                       (3, 4),
                                                       (4, 5),
                                                       (5, 1);

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

-- Radera alla poster relaterade till padelbanor och bokningar
DELETE FROM padel_booking;
DELETE FROM court_times;  -- Först radera court_times för att undvika referensproblem
DELETE FROM court;

-- Lägg till padelbanor med manuellt specificerade `id`
INSERT INTO court (id, name, location, price_per_hour, description)
VALUES (1, 'Padel Court 1', 'Södertälje', 200, 'Inomhus, grönt gräs med vita linjer, finns omklädningsrum och kamera');
INSERT INTO court (id, name, location, price_per_hour, description)
VALUES (2, 'Padel Court 2', 'Årsta', 250, 'Inomhus, blått gräs med vita linjer, finns omklädningsrum och kamera');
INSERT INTO court (id, name, location, price_per_hour, description)
VALUES (3, 'Padel Court 3', 'Älvsjö', 200, 'Inomhus, grönt gräs med röda linjer');
INSERT INTO court (id, name, location, price_per_hour, description)
VALUES (4, 'Padel Court 4', 'Rättvik', 250, 'Utomhus, grönt gräs med vita linjer');
INSERT INTO court (id, name, location, price_per_hour, description)
VALUES (5, 'Padel Court 5', 'Haninge', 200, 'Utomhus, grönt gräs med vita linjer, finns omklädningsrum');

INSERT INTO court_times (court_id, time, is_available) VALUES (1, '08:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '09:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '10:00:00', false); -- Bokad tid
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '11:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '12:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '13:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '14:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '15:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '16:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '17:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '18:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '19:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (1, '20:00:00', true);
-- Repetera för fler tider...

-- Repetera samma mönster för alla padelbanor
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '08:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '09:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '10:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '11:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '12:00:00', false); -- Bokad tid
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '13:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '14:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '15:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '16:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '17:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '18:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '19:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (2, '20:00:00', true);

INSERT INTO court_times (court_id, time, is_available) VALUES (3, '08:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '09:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '10:00:00', false);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '11:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '12:00:00', true); -- Bokad tid
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '13:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '14:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '15:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '16:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '17:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '18:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '19:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (3, '20:00:00', true);

INSERT INTO court_times (court_id, time, is_available) VALUES (4, '08:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '09:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '10:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '11:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '12:00:00', false);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '13:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '14:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '15:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '16:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '17:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '18:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '19:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (4, '20:00:00', true);

INSERT INTO court_times (court_id, time, is_available) VALUES (5, '08:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '09:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '10:00:00', false);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '11:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '12:00:00', true); -- Bokad tid
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '13:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '14:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '15:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '16:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '17:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '18:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '19:00:00', true);
INSERT INTO court_times (court_id, time, is_available) VALUES (5, '20:00:00', true);

-- Lägg till bokningar med korrekt `court_id` och `customer_id`
INSERT INTO padel_booking (date, time, total_price, total_price_eur, currency, players_count, court_id, customer_id)
VALUES ('2024-10-03', '10:00:00', 200, 19.0, 'SEK', 4, 1, 1);

INSERT INTO padel_booking (date, time, total_price, total_price_eur, currency, players_count, court_id, customer_id)
VALUES ('2024-10-03', '12:00:00', 250, 23.75, 'SEK', 4, 2, 2);

-- Lägg till ytterligare bokningar för andra banor
INSERT INTO padel_booking (date, time, total_price, total_price_eur, currency, players_count, court_id, customer_id)
VALUES ('2024-10-03', '10:00:00', 200, 19.0, 'SEK', 4, 3, 3);

INSERT INTO padel_booking (date, time, total_price, total_price_eur, currency, players_count, court_id, customer_id)
VALUES ('2024-10-03', '12:00:00', 250, 23.75, 'SEK', 4, 4, 4);

INSERT INTO padel_booking (date, time, total_price, total_price_eur, currency, players_count, court_id, customer_id)
VALUES ('2024-10-03', '10:00:00', 200, 19.0, 'SEK', 4, 5, 5);
