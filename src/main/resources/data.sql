DELETE FROM bookings;
DELETE FROM rooms;
DELETE FROM hotels;
DELETE FROM guests;

INSERT INTO hotels (id, name, city, rating) VALUES (1, 'Lakeside Retreat', 'Austin', 4.5);
INSERT INTO hotels (id, name, city, rating) VALUES (2, 'Downtown Suites', 'Austin', 4.2);
INSERT INTO hotels (id, name, city, rating) VALUES (3, 'Beachside Resort', 'Miami', 4.7);

INSERT INTO rooms (id, room_number, type, capacity, base_rate, hotel_id) VALUES (1, '101', 'STANDARD', 2, 129.00, 1);
INSERT INTO rooms (id, room_number, type, capacity, base_rate, hotel_id) VALUES (2, '102', 'DELUXE', 3, 179.00, 1);
INSERT INTO rooms (id, room_number, type, capacity, base_rate, hotel_id) VALUES (3, '201', 'SUITE', 4, 229.00, 2);
INSERT INTO rooms (id, room_number, type, capacity, base_rate, hotel_id) VALUES (4, '301', 'SUITE', 4, 299.00, 3);

INSERT INTO guests (id, first_name, last_name, email, phone) VALUES (1, 'Demo', 'Guest', 'demo@example.com', '555-0100');
