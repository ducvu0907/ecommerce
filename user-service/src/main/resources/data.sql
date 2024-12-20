INSERT INTO users (first_name, last_name, username, password, role, phone)
VALUES
('John', 'Doe', 'johndoe', '123456', 'BUYER', '0912345678'),
('Jane', 'Smith', 'janesmith', '123456', 'SELLER', '0923456789'),
('Alice', 'Johnson', 'alicejohnson', '123456', 'ADMIN', '0934567890'),
('Bob', 'Brown', 'bobbrown', '123456', 'BUYER', '0945678901'),
('Mary', 'Davis', 'marydavis', '123456', 'SELLER', '0956789012'),
('James', 'Wilson', 'jameswilson', '123456', 'ADMIN', '0967890123'),
('Linda', 'Moore', 'lindamoore', '123456', 'BUYER', '0978901234'),
('Charles', 'Taylor', 'charlestaylor', '123456', 'SELLER', '0989012345'),
('Patricia', 'Anderson', 'patriciaanderson', '123456', 'ADMIN', '0990123456'),
('Michael', 'Thomas', 'michaelthomas', '123456', 'BUYER', '0901234567'),
('David', 'Martinez', 'davidmartinez', '123456', 'SELLER', '0913456789'),
('Sophia', 'Miller', 'sophiamiller', '123456', 'ADMIN', '0924567890'),
('Ethan', 'Garcia', 'ethangarcia', '123456', 'BUYER', '0935678901'),
('Olivia', 'Rodriguez', 'oliviarodriguez', '123456', 'SELLER', '0946789012'),
('Lucas', 'Martinez', 'lucasmartinez', '123456', 'ADMIN', '0957890123'),
('Mason', 'Hernandez', 'masonhernandez', '123456', 'BUYER', '0968901234'),
('Isabella', 'Lopez', 'isabellalopez', '123456', 'SELLER', '0979012345'),
('Aiden', 'Gonzalez', 'aidengonzalez', '123456', 'ADMIN', '0980123456'),
('Amelia', 'Wilson', 'ameliawilson', '123456', 'BUYER', '0991234567'),
('Liam', 'Moore', 'liammoore', '123456', 'SELLER', '0902345678'),
('eren', 'jaeger', 'buyer', '123', 'BUYER', '0123456789'),
('gojo', 'satoru', 'seller', '123', 'BUYER', '0123456789'),
('uzuki', 'admin', 'admin', '123', 'ADMIN', '0123456789');

INSERT INTO addresses (user_id, street, city, country)
VALUES
(1, '123 Elm St', 'New York', 'USA'),
(2, '456 Oak St', 'Los Angeles', 'USA'),
(3, '789 Pine St', 'Chicago', 'USA'),
(4, '101 Maple Ave', 'Miami', 'USA'),
(5, '202 Birch Rd', 'Dallas', 'USA'),
(6, '303 Cedar Dr', 'Seattle', 'USA'),
(7, '404 Willow Ln', 'Boston', 'USA'),
(8, '505 Redwood Blvd', 'San Francisco', 'USA'),
(9, '606 Pinecone Cir', 'Denver', 'USA'),
(10, '707 Cypress St', 'Austin', 'USA'),
(11, '808 Maple Ave', 'Las Vegas', 'USA'),
(12, '909 Oak Dr', 'San Diego', 'USA'),
(13, '101 Birch Ln', 'Phoenix', 'USA'),
(14, '202 Cedar Blvd', 'Portland', 'USA'),
(15, '303 Redwood St', 'Atlanta', 'USA'),
(16, '404 Willow Dr', 'Detroit', 'USA'),
(17, '505 Pine St', 'Boston', 'USA'),
(18, '606 Cypress Blvd', 'Chicago', 'USA'),
(19, '707 Cedar Ave', 'Miami', 'USA'),
(20, '808 Redwood Dr', 'San Francisco', 'USA');

