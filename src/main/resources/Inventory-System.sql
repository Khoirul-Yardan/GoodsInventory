-- Create database (run this separately if needed)
-- CREATE DATABASE inventory_db;

-- Drop tables if they exist (for clean setup)
DROP TABLE IF EXISTS goods_output CASCADE;
DROP TABLE IF EXISTS goods_input CASCADE;
DROP TABLE IF EXISTS goods CASCADE;
DROP TABLE IF EXISTS staff CASCADE;
DROP TABLE IF EXISTS storage CASCADE;
DROP TABLE IF EXISTS category CASCADE;

-- Create Category table
CREATE TABLE category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Storage table
CREATE TABLE storage (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    capacity INTEGER DEFAULT 0,
    current_stock INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Staff table
CREATE TABLE staff (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    position VARCHAR(255),
    phone VARCHAR(255),
    salary DECIMAL(10,2),
    hire_date DATE DEFAULT CURRENT_DATE,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Goods table
CREATE TABLE goods (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INTEGER DEFAULT 0,
    price DECIMAL(10,2) NOT NULL,
    category VARCHAR(255),
    sku VARCHAR(100) UNIQUE,
    barcode VARCHAR(255),
    min_stock_level INTEGER DEFAULT 10,
    max_stock_level INTEGER DEFAULT 1000,
    unit VARCHAR(50) DEFAULT 'pcs',
    weight DECIMAL(8,2),
    dimensions VARCHAR(100),
    supplier VARCHAR(255),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Goods Input table (Incoming stock)
CREATE TABLE goods_input (
    id BIGSERIAL PRIMARY KEY,
    goods_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    unit_price DECIMAL(10,2),
    total_price DECIMAL(10,2),
    supplier VARCHAR(255),
    supplier_invoice VARCHAR(255),
    batch_number VARCHAR(255),
    expiry_date DATE,
    notes TEXT,
    staff_id BIGINT,
    input_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (goods_id) REFERENCES goods(id) ON DELETE CASCADE,
    FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE SET NULL
);

-- Create Goods Output table (Outgoing stock)
CREATE TABLE goods_output (
    id BIGSERIAL PRIMARY KEY,
    goods_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    unit_price DECIMAL(10,2),
    total_price DECIMAL(10,2),
    customer VARCHAR(255),
    customer_contact VARCHAR(255),
    invoice_number VARCHAR(255),
    delivery_address TEXT,
    notes TEXT,
    staff_id BIGINT,
    output_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delivery_date DATE,
    status VARCHAR(50) DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (goods_id) REFERENCES goods(id) ON DELETE CASCADE,
    FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE SET NULL
);

-- Create indexes for better performance
CREATE INDEX idx_goods_category ON goods(category);
CREATE INDEX idx_goods_name ON goods(name);
CREATE INDEX idx_goods_sku ON goods(sku);
CREATE INDEX idx_goods_input_date ON goods_input(input_date);
CREATE INDEX idx_goods_output_date ON goods_output(output_date);
CREATE INDEX idx_goods_input_goods_id ON goods_input(goods_id);
CREATE INDEX idx_goods_output_goods_id ON goods_output(goods_id);
CREATE INDEX idx_staff_email ON staff(email);

-- Insert sample categories
INSERT INTO category (name, description) VALUES 
('Electronics', 'Electronic devices, components, and accessories'),
('Furniture', 'Office and home furniture items'),
('Stationery', 'Office supplies, paper, and writing materials'),
('Tools', 'Hardware tools and equipment'),
('Clothing', 'Apparel and accessories'),
('Food & Beverage', 'Food items and beverages'),
('Medical', 'Medical supplies and equipment'),
('Automotive', 'Car parts and automotive accessories');

-- Insert sample storage locations
INSERT INTO storage (name, location, capacity, current_stock) VALUES 
('Main Warehouse', 'Building A, Floor 1', 10000, 0),
('Electronics Storage', 'Building B, Floor 2', 5000, 0),
('Office Storage', 'Building A, Floor 3', 2000, 0),
('Cold Storage', 'Building C, Floor 1', 1500, 0);

-- Insert sample staff
INSERT INTO staff (name, email, position, phone, salary, hire_date) VALUES 
('John Doe', 'john.doe@company.com', 'Warehouse Manager', '+1-555-0101', 65000.00, '2023-01-15'),
('Jane Smith', 'jane.smith@company.com', 'Inventory Clerk', '+1-555-0102', 45000.00, '2023-03-20'),
('Mike Johnson', 'mike.johnson@company.com', 'Stock Supervisor', '+1-555-0103', 55000.00, '2023-02-10'),
('Sarah Wilson', 'sarah.wilson@company.com', 'Purchasing Officer', '+1-555-0104', 60000.00, '2023-01-05'),
('David Brown', 'david.brown@company.com', 'Quality Controller', '+1-555-0105', 50000.00, '2023-04-12');

-- Insert sample goods
INSERT INTO goods (name, description, quantity, price, category, sku, min_stock_level, max_stock_level, unit, supplier) VALUES 
('Dell Laptop 15"', 'Dell Inspiron 15 3000 Series, Intel i5, 8GB RAM, 256GB SSD', 25, 799.99, 'Electronics', 'DELL-LAP-001', 5, 100, 'pcs', 'Dell Technologies'),
('iPhone 13', 'Apple iPhone 13, 128GB, Blue', 15, 699.99, 'Electronics', 'APPL-PHN-001', 3, 50, 'pcs', 'Apple Inc'),
('Office Chair', 'Ergonomic office chair with lumbar support, black leather', 40, 249.99, 'Furniture', 'FURN-CHR-001', 10, 200, 'pcs', 'Office Depot'),
('Desk Lamp', 'LED desk lamp with adjustable brightness, USB charging port', 60, 29.99, 'Electronics', 'ELEC-LMP-001', 15, 300, 'pcs', 'IKEA'),
('A4 Paper Ream', 'White copy paper, 500 sheets per ream, 80gsm', 200, 8.99, 'Stationery', 'STAT-PAP-001', 50, 1000, 'ream', 'Staples'),
('Wireless Mouse', 'Logitech wireless optical mouse, 2.4GHz, black', 80, 19.99, 'Electronics', 'LOGI-MOU-001', 20, 400, 'pcs', 'Logitech'),
('Filing Cabinet', '4-drawer metal filing cabinet, gray', 12, 189.99, 'Furniture', 'FURN-CAB-001', 3, 50, 'pcs', 'Steelcase'),
('Ballpoint Pens', 'Blue ink ballpoint pens, pack of 12', 150, 4.99, 'Stationery', 'STAT-PEN-001', 30, 500, 'pack', 'BIC'),
('Monitor 24"', 'Samsung 24" Full HD LED monitor, HDMI/VGA', 18, 159.99, 'Electronics', 'SAMS-MON-001', 5, 100, 'pcs', 'Samsung'),
('Notebook', 'Spiral notebook, A4 size, 200 pages, lined', 300, 3.99, 'Stationery', 'STAT-NOT-001', 50, 1000, 'pcs', 'Moleskine');

-- Insert sample goods input records
INSERT INTO goods_input (goods_id, quantity, unit_price, total_price, supplier, supplier_invoice, notes, staff_id, input_date) VALUES 
(1, 50, 750.00, 37500.00, 'Dell Technologies', 'INV-DELL-2024-001', 'Initial stock purchase', 1, '2024-01-15 10:30:00'),
(2, 30, 650.00, 19500.00, 'Apple Inc', 'INV-APPL-2024-001', 'iPhone 13 restock', 1, '2024-01-16 14:20:00'),
(3, 100, 220.00, 22000.00, 'Office Depot', 'INV-OFFD-2024-001', 'Office chair bulk order', 2, '2024-01-17 09:15:00'),
(4, 150, 25.00, 3750.00, 'IKEA', 'INV-IKEA-2024-001', 'Desk lamp restock', 2, '2024-01-18 11:45:00'),
(5, 500, 7.50, 3750.00, 'Staples', 'INV-STAP-2024-001', 'Paper supply restock', 3, '2024-01-19 08:30:00');

-- Insert sample goods output records
INSERT INTO goods_output (goods_id, quantity, unit_price, total_price, customer, customer_contact, invoice_number, notes, staff_id, output_date, status) VALUES 
(1, 25, 799.99, 19999.75, 'ABC Corporation', 'contact@abc-corp.com', 'OUT-2024-001', 'Corporate laptop order', 1, '2024-01-20 13:30:00', 'delivered'),
(2, 15, 699.99, 10499.85, 'John Personal', 'john@email.com', 'OUT-2024-002', 'Personal purchase', 2, '2024-01-21 16:20:00', 'delivered'),
(3, 60, 249.99, 14999.40, 'XYZ Office Solutions', 'orders@xyz-office.com', 'OUT-2024-003', 'Office furniture order', 2, '2024-01-22 10:15:00', 'shipped'),
(4, 90, 29.99, 2699.10, 'University Bookstore', 'procurement@university.edu', 'OUT-2024-004', 'Student desk lamps', 3, '2024-01-23 14:45:00', 'pending'),
(5, 300, 8.99, 2697.00, 'Print Shop Plus', 'manager@printshop.com', 'OUT-2024-005', 'Monthly paper supply', 3, '2024-01-24 09:20:00', 'delivered');

-- Create triggers to automatically update timestamps
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Apply triggers to all tables with updated_at column
CREATE TRIGGER update_category_updated_at BEFORE UPDATE ON category FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_storage_updated_at BEFORE UPDATE ON storage FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_staff_updated_at BEFORE UPDATE ON staff FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_goods_updated_at BEFORE UPDATE ON goods FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- Create a view for goods with stock levels
CREATE VIEW goods_stock_view AS
SELECT 
    g.id,
    g.name,
    g.description,
    g.quantity,
    g.price,
    g.category,
    g.sku,
    g.min_stock_level,
    g.max_stock_level,
    g.supplier,
    CASE 
        WHEN g.quantity <= g.min_stock_level THEN 'Low Stock'
        WHEN g.quantity >= g.max_stock_level THEN 'Overstock'
        ELSE 'Normal'
    END as stock_status,
    g.is_active,
    g.created_at,
    g.updated_at
FROM goods g
WHERE g.is_active = true;

-- Create a view for inventory summary
CREATE VIEW inventory_summary AS
SELECT 
    COUNT(*) as total_goods,
    SUM(quantity * price) as total_inventory_value,
    COUNT(CASE WHEN quantity <= min_stock_level THEN 1 END) as low_stock_items,
    COUNT(CASE WHEN quantity >= max_stock_level THEN 1 END) as overstock_items
FROM goods 
WHERE is_active = true;

-- Grant permissions (adjust as needed for your setup)
-- GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO your_username;
-- GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO your_username;

-- Display summary
SELECT 'Database setup completed successfully!' as status;
SELECT * FROM inventory_summary;