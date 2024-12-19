INSERT INTO categories (id, title) VALUES
(1, 'Electronics'),
(2, 'Clothing'),
(3, 'Home & Kitchen'),
(4, 'Beauty & Personal Care'),
(5, 'Sports & Outdoors'),
(6, 'Books'),
(7, 'Toys & Games'),
(8, 'Automotive'),
(9, 'Health & Household'),
(10, 'Grocery');

INSERT INTO products (id, category_id, description, seller_id, title, image_url, sku, price, quantity) VALUES
(1, 1, 'Smartphone with a high resolution camera', 1, 'Smartphone X', 'https://placehold.co/600x400', 'SKU12345', 699.99, 100),
(2, 1, 'Noise-cancelling Bluetooth headphones', 2, 'Headphones Pro', 'https://placehold.co/600x400', 'SKU12346', 199.99, 50),
(3, 2, 'Cotton t-shirt for all sizes', 3, 'Cotton T-Shirt', 'https://placehold.co/600x400', 'SKU12347', 14.99, 200),
(4, 2, 'Leather jacket for men', 4, 'Men\'s Leather Jacket', 'https://placehold.co/600x400', 'SKU12348', 129.99, 30),
(5, 3, 'Stainless steel cookware set', 5, 'Cookware Set', 'https://placehold.co/600x400', 'SKU12349', 199.99, 40),
(6, 3, 'Blender for smoothies and shakes', 6, 'Blender Max', 'https://placehold.co/600x400', 'SKU12350', 89.99, 60),
(7, 4, 'Anti-aging skincare cream', 7, 'Anti-Aging Cream', 'https://placehold.co/600x400', 'SKU12351', 29.99, 80),
(8, 4, 'Hair dryer with multiple settings', 8, 'Professional Hair Dryer', 'https://placehold.co/600x400', 'SKU12352', 59.99, 70),
(9, 5, 'Outdoor hiking boots', 9, 'Hiking Boots', 'https://placehold.co/600x400', 'SKU12353', 89.99, 50),
(10, 5, 'Camping tent for 4 people', 10, 'Camping Tent', 'https://placehold.co/600x400', 'SKU12354', 129.99, 25),
(11, 6, 'The Catcher in the Rye (Paperback)', 11, 'Catcher in the Rye', 'https://placehold.co/600x400', 'SKU12355', 15.99, 150),
(12, 6, 'Science fiction novel - Dune', 12, 'Dune', 'https://placehold.co/600x400', 'SKU12356', 18.99, 120),
(13, 7, 'Lego building set for ages 6+', 13, 'Lego Set', 'https://placehold.co/600x400', 'SKU12357', 39.99, 200),
(14, 7, 'Remote-controlled car', 14, 'RC Car', 'https://placehold.co/600x400', 'SKU12358', 49.99, 80),
(15, 8, 'Car seat cover set for protection', 15, 'Car Seat Covers', 'https://placehold.co/600x400', 'SKU12359', 29.99, 150),
(16, 8, 'Portable jump starter for cars', 16, 'Jump Starter', 'https://placehold.co/600x400', 'SKU12360', 59.99, 60),
(17, 9, 'Vitamins and supplements pack', 17, 'Health Supplements', 'https://placehold.co/600x400', 'SKU12361', 25.99, 100),
(18, 9, 'Protein powder for muscle growth', 18, 'Protein Powder', 'https://placehold.co/600x400', 'SKU12362', 39.99, 200),
(19, 10, 'Organic olive oil', 19, 'Olive Oil', 'https://placehold.co/600x400', 'SKU12363', 14.99, 120),
(20, 10, 'Gourmet coffee beans', 20, 'Gourmet Coffee', 'https://placehold.co/600x400', 'SKU12364', 9.99, 150),
(21, 1, '4K Ultra HD Smart TV with streaming capabilities', 1, '4K Smart TV', 'https://placehold.co/600x400', 'SKU12365', 899.99, 30),
(22, 1, 'Wireless charging pad for smartphones', 2, 'Charging Pad', 'https://placehold.co/600x400', 'SKU12366', 29.99, 150),
(23, 2, 'Winter jacket with thermal insulation', 3, 'Thermal Winter Jacket', 'https://placehold.co/600x400', 'SKU12367', 79.99, 60),
(24, 2, 'Women\'s leather boots', 4, 'Leather Boots', 'https://placehold.co/600x400', 'SKU12368', 119.99, 40),
(25, 3, 'Air fryer with multiple cooking presets', 5, 'Air Fryer', 'https://placehold.co/600x400', 'SKU12369', 99.99, 75),
(26, 3, 'Electric kettle with temperature control', 6, 'Electric Kettle', 'https://placehold.co/600x400', 'SKU12370', 59.99, 80),
(27, 4, 'Moisturizing body lotion for dry skin', 7, 'Body Lotion', 'https://placehold.co/600x400', 'SKU12371', 12.99, 200),
(28, 4, 'Face serum for brightening and anti-aging', 8, 'Face Serum', 'https://placehold.co/600x400', 'SKU12372', 39.99, 150),
(29, 5, 'Portable camping stove', 9, 'Camping Stove', 'https://placehold.co/600x400', 'SKU12373', 49.99, 100),
(30, 5, 'Fishing rod and tackle set', 10, 'Fishing Rod Set', 'https://placehold.co/600x400', 'SKU12374', 69.99, 60),
(31, 6, 'A brief history of time - hardcover edition', 11, 'History of Time', 'https://placehold.co/600x400', 'SKU12375', 22.99, 150),
(32, 6, 'The Great Gatsby - Paperback', 12, 'The Great Gatsby', 'https://placehold.co/600x400', 'SKU12376', 14.99, 180),
(33, 7, 'Building blocks set for creative kids', 13, 'Creative Blocks', 'https://placehold.co/600x400', 'SKU12377', 24.99, 250),
(34, 7, 'Interactive drone with camera', 14, 'Drone Camera', 'https://placehold.co/600x400', 'SKU12378', 149.99, 50),
(35, 8, 'Bluetooth car stereo system', 15, 'Car Stereo', 'https://placehold.co/600x400', 'SKU12379', 99.99, 120),
(36, 8, 'LED car headlights for better visibility', 16, 'LED Headlights', 'https://placehold.co/600x400', 'SKU12380', 39.99, 150),
(37, 9, 'Organic protein bars for snacks', 17, 'Protein Bars', 'https://placehold.co/600x400', 'SKU12381', 19.99, 180),
(38, 9, 'Omega-3 fish oil supplements', 18, 'Fish Oil', 'https://placehold.co/600x400', 'SKU12382', 24.99, 120),
(39, 10, 'Organic honey from local farms', 19, 'Organic Honey', 'https://placehold.co/600x400', 'SKU12383', 15.99, 100),
(40, 10, 'Fresh fruit baskets for delivery', 20, 'Fruit Basket', 'https://placehold.co/600x400', 'SKU12384', 29.99, 80),
(41, 1, 'Portable smart speaker with voice assistant', 1, 'Smart Speaker', 'https://placehold.co/600x400', 'SKU12385', 149.99, 70),
(42, 1, 'Wireless gaming mouse with ergonomic design', 2, 'Gaming Mouse', 'https://placehold.co/600x400', 'SKU12386', 59.99, 100),
(43, 2, 'Unisex hoodie with soft fabric', 3, 'Unisex Hoodie', 'https://placehold.co/600x400', 'SKU12387', 39.99, 150),
(44, 2, 'Slim-fit jeans for women', 4, 'Slim-Fit Jeans', 'https://placehold.co/600x400', 'SKU12388', 49.99, 80),
(45, 3, 'Electric pressure cooker for fast meals', 5, 'Pressure Cooker', 'https://placehold.co/600x400', 'SKU12389', 119.99, 50),
(46, 3, 'Non-stick frying pan for easy cooking', 6, 'Frying Pan', 'https://placehold.co/600x400', 'SKU12390', 29.99, 100),
(47, 4, 'Hydrating facial cleanser for sensitive skin', 7, 'Facial Cleanser', 'https://placehold.co/600x400', 'SKU12391', 18.99, 200),
(48, 4, 'Essential oil set for aromatherapy', 8, 'Aromatherapy Oils', 'https://placehold.co/600x400', 'SKU12392', 29.99, 120),
(49, 5, 'Compact sleeping bag for outdoor camping', 9, 'Sleeping Bag', 'https://placehold.co/600x400', 'SKU12393', 59.99, 90),
(50, 5, 'Adjustable trekking poles', 10, 'Trekking Poles', 'https://placehold.co/600x400', 'SKU12394', 39.99, 100),
(51, 6, 'Mystery thriller novel', 11, 'Thriller Novel', 'https://placehold.co/600x400', 'SKU12395', 16.99, 120),
(52, 6, 'Hardcover cookbook with recipes', 12, 'Recipe Book', 'https://placehold.co/600x400', 'SKU12396', 29.99, 150),
(53, 7, 'Educational puzzle set for kids', 13, 'Puzzle Set', 'https://placehold.co/600x400', 'SKU12397', 19.99, 300),
(54, 7, 'Remote-controlled helicopter', 14, 'RC Helicopter', 'https://placehold.co/600x400', 'SKU12398', 59.99, 60),
(55, 8, 'Car vacuum cleaner for easy cleaning', 15, 'Car Vacuum', 'https://placehold.co/600x400', 'SKU12399', 39.99, 80),
(56, 8, 'Magnetic phone mount for cars', 16, 'Phone Mount', 'https://placehold.co/600x400', 'SKU12400', 19.99, 200),
(57, 9, 'Yoga mat with non-slip surface', 17, 'Yoga Mat', 'https://placehold.co/600x400', 'SKU12401', 29.99, 150),
(58, 9, 'Resistance bands for fitness training', 18, 'Resistance Bands', 'https://placehold.co/600x400', 'SKU12402', 24.99, 100),
(59, 10, 'Gourmet dark chocolate bars', 19, 'Dark Chocolate', 'https://placehold.co/600x400', 'SKU12403', 12.99, 200),
(60, 10, 'Premium loose-leaf tea sampler', 20, 'Tea Sampler', 'https://placehold.co/600x400', 'SKU12404', 19.99, 100);
