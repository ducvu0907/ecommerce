INSERT INTO categories (id, title) VALUES
('550e8400-e29b-41d4-a716-446655440000', 'Electronics'),
('550e8400-e29b-41d4-a716-446655440001', 'Clothing'),
('550e8400-e29b-41d4-a716-446655440002', 'Home & Kitchen'),
('550e8400-e29b-41d4-a716-446655440003', 'Books'),
('550e8400-e29b-41d4-a716-446655440004', 'Sports & Outdoors'),
('550e8400-e29b-41d4-a716-446655440005', 'Beauty & Personal Care'),
('550e8400-e29b-41d4-a716-446655440006', 'Toys & Games'),
('550e8400-e29b-41d4-a716-446655440007', 'Automotive'),
('550e8400-e29b-41d4-a716-446655440008', 'Health & Wellness'),
('550e8400-e29b-41d4-a716-446655440009', 'Pet Supplies');

INSERT INTO products (id, seller_id, title, description, image_url, sku, price, category_id, created_at, updated_at) VALUES
-- Electronics
('6ba7b810-9dad-11d1-80b4-00c04fd430c0', 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Smartphone X', 'A high-end smartphone with advanced features.', 'https://example.com/smartphonex.jpg', 'SKU12345', 999.99, '550e8400-e29b-41d4-a716-446655440000', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430c1', 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Laptop Pro', 'A powerful laptop for professionals.', 'https://example.com/laptoppro.jpg', 'SKU67890', 1499.99, '550e8400-e29b-41d4-a716-446655440000', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430c2', 'f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Wireless Earbuds', 'Noise-cancelling wireless earbuds.', 'https://example.com/earbuds.jpg', 'SKU11223', 199.99, '550e8400-e29b-41d4-a716-446655440000', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430c3', 'h7eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Smart Watch', 'A smartwatch with health tracking features.', 'https://example.com/smartwatch.jpg', 'SKU44556', 299.99, '550e8400-e29b-41d4-a716-446655440000', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430c4', 'j9eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', '4K TV', 'A 55-inch 4K Ultra HD Smart TV.', 'https://example.com/4ktv.jpg', 'SKU77889', 799.99, '550e8400-e29b-41d4-a716-446655440000', NOW(), null),

-- Clothing
('6ba7b810-9dad-11d1-80b4-00c04fd430c5', 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'T-Shirt Classic', 'A comfortable and stylish t-shirt.', 'https://example.com/tshirt.jpg', 'SKU99001', 19.99, '550e8400-e29b-41d4-a716-446655440001', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430c6', 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Jeans Slim Fit', 'Slim fit jeans for a modern look.', 'https://example.com/jeans.jpg', 'SKU22334', 49.99, '550e8400-e29b-41d4-a716-446655440001', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430c7', 'f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Winter Jacket', 'A warm and stylish winter jacket.', 'https://example.com/jacket.jpg', 'SKU55667', 129.99, '550e8400-e29b-41d4-a716-446655440001', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430c8', 'h7eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Running Shoes', 'Lightweight running shoes for athletes.', 'https://example.com/shoes.jpg', 'SKU88990', 89.99, '550e8400-e29b-41d4-a716-446655440001', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430c9', 'j9eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'Formal Shirt', 'A crisp formal shirt for business occasions.', 'https://example.com/shirt.jpg', 'SKU11224', 59.99, '550e8400-e29b-41d4-a716-446655440001', NOW(), null),

-- Home & Kitchen
('6ba7b810-9dad-11d1-80b4-00c04fd430d0', 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Blender', 'A high-speed blender for smoothies and more.', 'https://example.com/blender.jpg', 'SKU33445', 79.99, '550e8400-e29b-41d4-a716-446655440002', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d1', 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Air Fryer', 'A versatile air fryer for healthy cooking.', 'https://example.com/airfryer.jpg', 'SKU55678', 129.99, '550e8400-e29b-41d4-a716-446655440002', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d2', 'f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Coffee Maker', 'A programmable coffee maker for fresh brews.', 'https://example.com/coffeemaker.jpg', 'SKU77889', 99.99, '550e8400-e29b-41d4-a716-446655440002', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d3', 'h7eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Cookware Set', 'A complete set of non-stick cookware.', 'https://example.com/cookware.jpg', 'SKU99001', 199.99, '550e8400-e29b-41d4-a716-446655440002', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d4', 'j9eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'Vacuum Cleaner', 'A powerful vacuum cleaner for all surfaces.', 'https://example.com/vacuum.jpg', 'SKU11225', 149.99, '550e8400-e29b-41d4-a716-446655440002', NOW(), null),

-- Books
('6ba7b810-9dad-11d1-80b4-00c04fd430d5', 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Novel: The Great Adventure', 'A thrilling novel about exploration.', 'https://example.com/novel.jpg', 'SKU33446', 14.99, '550e8400-e29b-41d4-a716-446655440003', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d6', 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Cookbook: Healthy Recipes', 'A cookbook with 100+ healthy recipes.', 'https://example.com/cookbook.jpg', 'SKU55679', 24.99, '550e8400-e29b-41d4-a716-446655440003', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d7', 'f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Self-Help: Mindset Mastery', 'A guide to developing a growth mindset.', 'https://example.com/selfhelp.jpg', 'SKU77890', 19.99, '550e8400-e29b-41d4-a716-446655440003', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d8', 'h7eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Children’s Book: The Magic Forest', 'A colorful book for young readers.', 'https://example.com/childrensbook.jpg', 'SKU99002', 9.99, '550e8400-e29b-41d4-a716-446655440003', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d9', 'j9eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'Biography: The Life of a Leader', 'An inspiring biography of a great leader.', 'https://example.com/biography.jpg', 'SKU11226', 29.99, '550e8400-e29b-41d4-a716-446655440003', NOW(), null),

-- Sports & Outdoors
('6ba7b810-9dad-11d1-80b4-00c04fd430e0', 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Yoga Mat', 'A non-slip yoga mat for all levels.', 'https://example.com/yogamat.jpg', 'SKU33447', 29.99, '550e8400-e29b-41d4-a716-446655440004', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e1', 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Camping Tent', 'A durable tent for outdoor adventures.', 'https://example.com/tent.jpg', 'SKU55680', 149.99, '550e8400-e29b-41d4-a716-446655440004', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e2', 'f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Running Belt', 'A lightweight belt for carrying essentials.', 'https://example.com/runningbelt.jpg', 'SKU77891', 19.99, '550e8400-e29b-41d4-a716-446655440004', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e3', 'h7eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Hiking Backpack', 'A spacious backpack for hiking trips.', 'https://example.com/backpack.jpg', 'SKU99003', 79.99, '550e8400-e29b-41d4-a716-446655440004', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e4', 'j9eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'Dumbbell Set', 'A set of adjustable dumbbells for home workouts.', 'https://example.com/dumbbells.jpg', 'SKU11227', 99.99, '550e8400-e29b-41d4-a716-446655440004', NOW(), null),

-- Beauty & Personal Care
('6ba7b810-9dad-11d1-80b4-00c04fd430e5', 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Face Cream', 'A hydrating face cream for all skin types.', 'https://example.com/facecream.jpg', 'SKU33448', 39.99, '550e8400-e29b-41d4-a716-446655440005', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e6', 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Electric Toothbrush', 'A rechargeable electric toothbrush.', 'https://example.com/toothbrush.jpg', 'SKU55681', 59.99, '550e8400-e29b-41d4-a716-446655440005', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e7', 'f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Hair Dryer', 'A professional hair dryer for salon-quality results.', 'https://example.com/hairdryer.jpg', 'SKU77892', 89.99, '550e8400-e29b-41d4-a716-446655440005', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e8', 'h7eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Perfume', 'A long-lasting fragrance for everyday wear.', 'https://example.com/perfume.jpg', 'SKU99004', 69.99, '550e8400-e29b-41d4-a716-446655440005', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e9', 'j9eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'Sunscreen', 'A broad-spectrum sunscreen for all-day protection.', 'https://example.com/sunscreen.jpg', 'SKU11228', 24.99, '550e8400-e29b-41d4-a716-446655440005', NOW(), null),

-- Toys & Games
('6ba7b810-9dad-11d1-80b4-00c04fd430f0', 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'LEGO Set', 'A creative LEGO set for kids and adults.', 'https://example.com/lego.jpg', 'SKU33449', 49.99, '550e8400-e29b-41d4-a716-446655440006', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f1', 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Board Game', 'A fun board game for family nights.', 'https://example.com/boardgame.jpg', 'SKU55682', 34.99, '550e8400-e29b-41d4-a716-446655440006', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f2', 'f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Remote Control Car', 'A high-speed remote control car.', 'https://example.com/rcar.jpg', 'SKU77893', 29.99, '550e8400-e29b-41d4-a716-446655440006', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f3', 'h7eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Puzzle Set', 'A challenging 1000-piece puzzle set.', 'https://example.com/puzzle.jpg', 'SKU99005', 19.99, '550e8400-e29b-41d4-a716-446655440006', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f4', 'j9eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'Dollhouse', 'A beautifully designed dollhouse for kids.', 'https://example.com/dollhouse.jpg', 'SKU11229', 99.99, '550e8400-e29b-41d4-a716-446655440006', NOW(), null),

-- Automotive
('6ba7b810-9dad-11d1-80b4-00c04fd430f5', 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Car Wax', 'A premium car wax for a shiny finish.', 'https://example.com/carwax.jpg', 'SKU33450', 19.99, '550e8400-e29b-41d4-a716-446655440007', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f6', 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Tire Pump', 'A portable tire pump for emergencies.', 'https://example.com/tirepump.jpg', 'SKU55683', 39.99, '550e8400-e29b-41d4-a716-446655440007', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f7', 'f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Car Seat Cover', 'A comfortable and durable car seat cover.', 'https://example.com/carseatcover.jpg', 'SKU77894', 49.99, '550e8400-e29b-41d4-a716-446655440007', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f8', 'h7eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Jump Starter', 'A portable jump starter for dead batteries.', 'https://example.com/jumpstarter.jpg', 'SKU99006', 99.99, '550e8400-e29b-41d4-a716-446655440007', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f9', 'j9eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'Dash Cam', 'A high-quality dash cam for recording drives.', 'https://example.com/dashcam.jpg', 'SKU11230', 129.99, '550e8400-e29b-41d4-a716-446655440007', NOW(), null),

-- Health & Wellness
('6ba7b810-9dad-11d1-80b4-00c04fd43100', 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Vitamin C Supplements', 'Boost your immune system with Vitamin C.', 'https://example.com/vitaminc.jpg', 'SKU33451', 14.99, '550e8400-e29b-41d4-a716-446655440008', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43101', 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Yoga Block', 'A sturdy yoga block for better poses.', 'https://example.com/yogablock.jpg', 'SKU55684', 9.99, '550e8400-e29b-41d4-a716-446655440008', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43102', 'f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Foam Roller', 'A foam roller for muscle recovery.', 'https://example.com/foamroller.jpg', 'SKU77895', 24.99, '550e8400-e29b-41d4-a716-446655440008', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43103', 'h7eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Resistance Bands', 'A set of resistance bands for workouts.', 'https://example.com/resistancebands.jpg', 'SKU99007', 19.99, '550e8400-e29b-41d4-a716-446655440008', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43104', 'j9eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'Massage Gun', 'A powerful massage gun for muscle relief.', 'https://example.com/massagegun.jpg', 'SKU11231', 149.99, '550e8400-e29b-41d4-a716-446655440008', NOW(), null),

-- Pet Supplies
('6ba7b810-9dad-11d1-80b4-00c04fd43105', 'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'Dog Food', 'Nutritious dog food for all breeds.', 'https://example.com/dogfood.jpg', 'SKU33452', 29.99, '550e8400-e29b-41d4-a716-446655440009', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43106', 'd3eebc99-9c0b-4ef8-bb6d-6bb9bd380a14', 'Cat Litter', 'Odor-free cat litter for a clean home.', 'https://example.com/catlitter.jpg', 'SKU55685', 19.99, '550e8400-e29b-41d4-a716-446655440009', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43107', 'f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a16', 'Pet Bed', 'A cozy pet bed for your furry friend.', 'https://example.com/petbed.jpg', 'SKU77896', 39.99, '550e8400-e29b-41d4-a716-446655440009', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43108', 'h7eebc99-9c0b-4ef8-bb6d-6bb9bd380a18', 'Dog Leash', 'A durable dog leash for walks.', 'https://example.com/dogleash.jpg', 'SKU99008', 14.99, '550e8400-e29b-41d4-a716-446655440009', NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43109', 'j9eebc99-9c0b-4ef8-bb6d-6bb9bd380a20', 'Cat Toy', 'An interactive toy for playful cats.', 'https://example.com/cattoy.jpg', 'SKU11232', 9.99, '550e8400-e29b-41d4-a716-446655440009', NOW(), null);

INSERT INTO inventories (id, product_id, location, stock, created_at, updated_at) VALUES
-- Smartphone X
('6ba7b810-9dad-11d1-80b4-00c04fd430c3', '6ba7b810-9dad-11d1-80b4-00c04fd430c0', 'Warehouse A', 100, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430c4', '6ba7b810-9dad-11d1-80b4-00c04fd430c0', 'Warehouse B', 50, NOW(), null),

-- Laptop Pro
('6ba7b810-9dad-11d1-80b4-00c04fd430c5', '6ba7b810-9dad-11d1-80b4-00c04fd430c1', 'Warehouse C', 75, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430c6', '6ba7b810-9dad-11d1-80b4-00c04fd430c1', 'Warehouse D', 25, NOW(), null),

-- Wireless Earbuds
('6ba7b810-9dad-11d1-80b4-00c04fd430c7', '6ba7b810-9dad-11d1-80b4-00c04fd430c2', 'Warehouse A', 200, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430c8', '6ba7b810-9dad-11d1-80b4-00c04fd430c2', 'Warehouse B', 100, NOW(), null),

-- Smart Watch
('6ba7b810-9dad-11d1-80b4-00c04fd430c9', '6ba7b810-9dad-11d1-80b4-00c04fd430c3', 'Warehouse C', 150, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d0', '6ba7b810-9dad-11d1-80b4-00c04fd430c3', 'Warehouse D', 50, NOW(), null),

-- 4K TV
('6ba7b810-9dad-11d1-80b4-00c04fd430d1', '6ba7b810-9dad-11d1-80b4-00c04fd430c4', 'Warehouse A', 30, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d2', '6ba7b810-9dad-11d1-80b4-00c04fd430c4', 'Warehouse B', 20, NOW(), null),

-- T-Shirt Classic
('6ba7b810-9dad-11d1-80b4-00c04fd430d3', '6ba7b810-9dad-11d1-80b4-00c04fd430c5', 'Warehouse C', 300, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d4', '6ba7b810-9dad-11d1-80b4-00c04fd430c5', 'Warehouse D', 150, NOW(), null),

-- Jeans Slim Fit
('6ba7b810-9dad-11d1-80b4-00c04fd430d5', '6ba7b810-9dad-11d1-80b4-00c04fd430c6', 'Warehouse A', 100, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d6', '6ba7b810-9dad-11d1-80b4-00c04fd430c6', 'Warehouse B', 50, NOW(), null),

-- Winter Jacket
('6ba7b810-9dad-11d1-80b4-00c04fd430d7', '6ba7b810-9dad-11d1-80b4-00c04fd430c7', 'Warehouse C', 75, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430d8', '6ba7b810-9dad-11d1-80b4-00c04fd430c7', 'Warehouse D', 25, NOW(), null),

-- Running Shoes
('6ba7b810-9dad-11d1-80b4-00c04fd430d9', '6ba7b810-9dad-11d1-80b4-00c04fd430c8', 'Warehouse A', 200, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e0', '6ba7b810-9dad-11d1-80b4-00c04fd430c8', 'Warehouse B', 100, NOW(), null),

-- Formal Shirt
('6ba7b810-9dad-11d1-80b4-00c04fd430e1', '6ba7b810-9dad-11d1-80b4-00c04fd430c9', 'Warehouse C', 150, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e2', '6ba7b810-9dad-11d1-80b4-00c04fd430c9', 'Warehouse D', 50, NOW(), null),

-- Blender
('6ba7b810-9dad-11d1-80b4-00c04fd430e3', '6ba7b810-9dad-11d1-80b4-00c04fd430d0', 'Warehouse A', 30, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e4', '6ba7b810-9dad-11d1-80b4-00c04fd430d0', 'Warehouse B', 20, NOW(), null),

-- Air Fryer
('6ba7b810-9dad-11d1-80b4-00c04fd430e5', '6ba7b810-9dad-11d1-80b4-00c04fd430d1', 'Warehouse C', 100, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e6', '6ba7b810-9dad-11d1-80b4-00c04fd430d1', 'Warehouse D', 50, NOW(), null),

-- Coffee Maker
('6ba7b810-9dad-11d1-80b4-00c04fd430e7', '6ba7b810-9dad-11d1-80b4-00c04fd430d2', 'Warehouse A', 75, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430e8', '6ba7b810-9dad-11d1-80b4-00c04fd430d2', 'Warehouse B', 25, NOW(), null),

-- Cookware Set
('6ba7b810-9dad-11d1-80b4-00c04fd430e9', '6ba7b810-9dad-11d1-80b4-00c04fd430d3', 'Warehouse C', 50, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f0', '6ba7b810-9dad-11d1-80b4-00c04fd430d3', 'Warehouse D', 25, NOW(), null),

-- Vacuum Cleaner
('6ba7b810-9dad-11d1-80b4-00c04fd430f1', '6ba7b810-9dad-11d1-80b4-00c04fd430d4', 'Warehouse A', 40, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f2', '6ba7b810-9dad-11d1-80b4-00c04fd430d4', 'Warehouse B', 20, NOW(), null),

-- Novel: The Great Adventure
('6ba7b810-9dad-11d1-80b4-00c04fd430f3', '6ba7b810-9dad-11d1-80b4-00c04fd430d5', 'Warehouse C', 500, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f4', '6ba7b810-9dad-11d1-80b4-00c04fd430d5', 'Warehouse D', 250, NOW(), null),

-- Cookbook: Healthy Recipes
('6ba7b810-9dad-11d1-80b4-00c04fd430f5', '6ba7b810-9dad-11d1-80b4-00c04fd430d6', 'Warehouse A', 300, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f6', '6ba7b810-9dad-11d1-80b4-00c04fd430d6', 'Warehouse B', 150, NOW(), null),

-- Self-Help: Mindset Mastery
('6ba7b810-9dad-11d1-80b4-00c04fd430f7', '6ba7b810-9dad-11d1-80b4-00c04fd430d7', 'Warehouse C', 200, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd430f8', '6ba7b810-9dad-11d1-80b4-00c04fd430d7', 'Warehouse D', 100, NOW(), null),

-- Children’s Book: The Magic Forest
('6ba7b810-9dad-11d1-80b4-00c04fd430f9', '6ba7b810-9dad-11d1-80b4-00c04fd430d8', 'Warehouse A', 400, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43100', '6ba7b810-9dad-11d1-80b4-00c04fd430d8', 'Warehouse B', 200, NOW(), null),

-- Biography: The Life of a Leader
('6ba7b810-9dad-11d1-80b4-00c04fd43101', '6ba7b810-9dad-11d1-80b4-00c04fd430d9', 'Warehouse C', 150, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43102', '6ba7b810-9dad-11d1-80b4-00c04fd430d9', 'Warehouse D', 75, NOW(), null),

-- Yoga Mat
('6ba7b810-9dad-11d1-80b4-00c04fd43103', '6ba7b810-9dad-11d1-80b4-00c04fd430e0', 'Warehouse A', 100, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43104', '6ba7b810-9dad-11d1-80b4-00c04fd430e0', 'Warehouse B', 50, NOW(), null),

-- Camping Tent
('6ba7b810-9dad-11d1-80b4-00c04fd43105', '6ba7b810-9dad-11d1-80b4-00c04fd430e1', 'Warehouse C', 30, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43106', '6ba7b810-9dad-11d1-80b4-00c04fd430e1', 'Warehouse D', 15, NOW(), null),

-- Running Belt
('6ba7b810-9dad-11d1-80b4-00c04fd43107', '6ba7b810-9dad-11d1-80b4-00c04fd430e2', 'Warehouse A', 200, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43108', '6ba7b810-9dad-11d1-80b4-00c04fd430e2', 'Warehouse B', 100, NOW(), null),

-- Hiking Backpack
('6ba7b810-9dad-11d1-80b4-00c04fd43109', '6ba7b810-9dad-11d1-80b4-00c04fd430e3', 'Warehouse C', 75, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43110', '6ba7b810-9dad-11d1-80b4-00c04fd430e3', 'Warehouse D', 25, NOW(), null),

-- Dumbbell Set
('6ba7b810-9dad-11d1-80b4-00c04fd43111', '6ba7b810-9dad-11d1-80b4-00c04fd430e4', 'Warehouse A', 50, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43112', '6ba7b810-9dad-11d1-80b4-00c04fd430e4', 'Warehouse B', 25, NOW(), null),

-- Face Cream
('6ba7b810-9dad-11d1-80b4-00c04fd43113', '6ba7b810-9dad-11d1-80b4-00c04fd430e5', 'Warehouse C', 300, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43114', '6ba7b810-9dad-11d1-80b4-00c04fd430e5', 'Warehouse D', 150, NOW(), null),

-- Electric Toothbrush
('6ba7b810-9dad-11d1-80b4-00c04fd43115', '6ba7b810-9dad-11d1-80b4-00c04fd430e6', 'Warehouse A', 200, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43116', '6ba7b810-9dad-11d1-80b4-00c04fd430e6', 'Warehouse B', 100, NOW(), null),

-- Hair Dryer
('6ba7b810-9dad-11d1-80b4-00c04fd43117', '6ba7b810-9dad-11d1-80b4-00c04fd430e7', 'Warehouse C', 100, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43118', '6ba7b810-9dad-11d1-80b4-00c04fd430e7', 'Warehouse D', 50, NOW(), null),

-- Perfume
('6ba7b810-9dad-11d1-80b4-00c04fd43119', '6ba7b810-9dad-11d1-80b4-00c04fd430e8', 'Warehouse A', 150, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43120', '6ba7b810-9dad-11d1-80b4-00c04fd430e8', 'Warehouse B', 75, NOW(), null),

-- Sunscreen
('6ba7b810-9dad-11d1-80b4-00c04fd43121', '6ba7b810-9dad-11d1-80b4-00c04fd430e9', 'Warehouse C', 400, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43122', '6ba7b810-9dad-11d1-80b4-00c04fd430e9', 'Warehouse D', 200, NOW(), null),

-- LEGO Set
('6ba7b810-9dad-11d1-80b4-00c04fd43123', '6ba7b810-9dad-11d1-80b4-00c04fd430f0', 'Warehouse A', 100, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43124', '6ba7b810-9dad-11d1-80b4-00c04fd430f0', 'Warehouse B', 50, NOW(), null),

-- Board Game
('6ba7b810-9dad-11d1-80b4-00c04fd43125', '6ba7b810-9dad-11d1-80b4-00c04fd430f1', 'Warehouse C', 75, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43126', '6ba7b810-9dad-11d1-80b4-00c04fd430f1', 'Warehouse D', 25, NOW(), null),

-- Remote Control Car
('6ba7b810-9dad-11d1-80b4-00c04fd43127', '6ba7b810-9dad-11d1-80b4-00c04fd430f2', 'Warehouse A', 200, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43128', '6ba7b810-9dad-11d1-80b4-00c04fd430f2', 'Warehouse B', 100, NOW(), null),

-- Puzzle Set
('6ba7b810-9dad-11d1-80b4-00c04fd43129', '6ba7b810-9dad-11d1-80b4-00c04fd430f3', 'Warehouse C', 300, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43130', '6ba7b810-9dad-11d1-80b4-00c04fd430f3', 'Warehouse D', 150, NOW(), null),

-- Dollhouse
('6ba7b810-9dad-11d1-80b4-00c04fd43131', '6ba7b810-9dad-11d1-80b4-00c04fd430f4', 'Warehouse A', 50, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43132', '6ba7b810-9dad-11d1-80b4-00c04fd430f4', 'Warehouse B', 25, NOW(), null),

-- Car Wax
('6ba7b810-9dad-11d1-80b4-00c04fd43133', '6ba7b810-9dad-11d1-80b4-00c04fd430f5', 'Warehouse C', 100, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43134', '6ba7b810-9dad-11d1-80b4-00c04fd430f5', 'Warehouse D', 50, NOW(), null),

-- Tire Pump
('6ba7b810-9dad-11d1-80b4-00c04fd43135', '6ba7b810-9dad-11d1-80b4-00c04fd430f6', 'Warehouse A', 75, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43136', '6ba7b810-9dad-11d1-80b4-00c04fd430f6', 'Warehouse B', 25, NOW(), null),

-- Car Seat Cover
('6ba7b810-9dad-11d1-80b4-00c04fd43137', '6ba7b810-9dad-11d1-80b4-00c04fd430f7', 'Warehouse C', 50, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43138', '6ba7b810-9dad-11d1-80b4-00c04fd430f7', 'Warehouse D', 25, NOW(), null),

-- Jump Starter
('6ba7b810-9dad-11d1-80b4-00c04fd43139', '6ba7b810-9dad-11d1-80b4-00c04fd430f8', 'Warehouse A', 30, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43140', '6ba7b810-9dad-11d1-80b4-00c04fd430f8', 'Warehouse B', 15, NOW(), null),

-- Dash Cam
('6ba7b810-9dad-11d1-80b4-00c04fd43141', '6ba7b810-9dad-11d1-80b4-00c04fd430f9', 'Warehouse C', 40, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43142', '6ba7b810-9dad-11d1-80b4-00c04fd430f9', 'Warehouse D', 20, NOW(), null),

-- Vitamin C Supplements
('6ba7b810-9dad-11d1-80b4-00c04fd43143', '6ba7b810-9dad-11d1-80b4-00c04fd43100', 'Warehouse A', 500, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43144', '6ba7b810-9dad-11d1-80b4-00c04fd43100', 'Warehouse B', 250, NOW(), null),

-- Yoga Block
('6ba7b810-9dad-11d1-80b4-00c04fd43145', '6ba7b810-9dad-11d1-80b4-00c04fd43101', 'Warehouse C', 300, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43146', '6ba7b810-9dad-11d1-80b4-00c04fd43101', 'Warehouse D', 150, NOW(), null),

-- Foam Roller
('6ba7b810-9dad-11d1-80b4-00c04fd43147', '6ba7b810-9dad-11d1-80b4-00c04fd43102', 'Warehouse A', 200, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43148', '6ba7b810-9dad-11d1-80b4-00c04fd43102', 'Warehouse B', 100, NOW(), null),

-- Resistance Bands
('6ba7b810-9dad-11d1-80b4-00c04fd43149', '6ba7b810-9dad-11d1-80b4-00c04fd43103', 'Warehouse C', 150, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43150', '6ba7b810-9dad-11d1-80b4-00c04fd43103', 'Warehouse D', 75, NOW(), null),

-- Massage Gun
('6ba7b810-9dad-11d1-80b4-00c04fd43151', '6ba7b810-9dad-11d1-80b4-00c04fd43104', 'Warehouse A', 50, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43152', '6ba7b810-9dad-11d1-80b4-00c04fd43104', 'Warehouse B', 25, NOW(), null),

-- Dog Food
('6ba7b810-9dad-11d1-80b4-00c04fd43153', '6ba7b810-9dad-11d1-80b4-00c04fd43105', 'Warehouse C', 100, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43154', '6ba7b810-9dad-11d1-80b4-00c04fd43105', 'Warehouse D', 50, NOW(), null),

-- Cat Litter
('6ba7b810-9dad-11d1-80b4-00c04fd43155', '6ba7b810-9dad-11d1-80b4-00c04fd43106', 'Warehouse A', 200, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43156', '6ba7b810-9dad-11d1-80b4-00c04fd43106', 'Warehouse B', 100, NOW(), null),

-- Pet Bed
('6ba7b810-9dad-11d1-80b4-00c04fd43157', '6ba7b810-9dad-11d1-80b4-00c04fd43107', 'Warehouse C', 75, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43158', '6ba7b810-9dad-11d1-80b4-00c04fd43107', 'Warehouse D', 25, NOW(), null),

-- Dog Leash
('6ba7b810-9dad-11d1-80b4-00c04fd43159', '6ba7b810-9dad-11d1-80b4-00c04fd43108', 'Warehouse A', 300, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43160', '6ba7b810-9dad-11d1-80b4-00c04fd43108', 'Warehouse B', 150, NOW(), null),

-- Cat Toy
('6ba7b810-9dad-11d1-80b4-00c04fd43161', '6ba7b810-9dad-11d1-80b4-00c04fd43109', 'Warehouse C', 400, NOW(), null),
('6ba7b810-9dad-11d1-80b4-00c04fd43162', '6ba7b810-9dad-11d1-80b4-00c04fd43109', 'Warehouse D', 200, NOW(), null);