INSERT INTO categories (name, description) VALUES
('Electronics', 'Phones, laptops, accessories'),
('Clothing', 'Men and women clothing'),
('Books', 'Fiction, non-fiction, guides'),
('Home & Garden', 'Furniture, tools, plants');

INSERT INTO products (name, description, available, price, stock, main_photo, gallery, category_id) VALUES
('iPhone 15', 'Apple smartphone 128GB', 'AVAILABLE', 3999.99, 50, 'iphone15.jpg', ARRAY['iphone15_1.jpg', 'iphone15_2.jpg'], 1),
('MacBook Pro', 'Apple laptop M3 chip', 'AVAILABLE', 8999.99, 20, 'macbook.jpg', ARRAY['macbook_1.jpg'], 1),
('Nike T-Shirt', 'Cotton t-shirt size M', 'AVAILABLE', 89.99, 200, 'nike_tshirt.jpg', NULL, 2),
('Clean Code', 'Book by Robert C. Martin', 'AVAILABLE', 59.99, 100, 'cleancode.jpg', NULL, 3),
('Garden Chair', 'Wooden outdoor chair', 'TEMPORARILY_UNAVAILABLE', 299.99, 0, 'chair.jpg', NULL, 4);