IF NOT EXISTS(SELECT * FROM sys.databases WHERE name = 'ddd_demo')
BEGIN
    CREATE DATABASE ddd_demo;
END
GO

USE ddd_demo;
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'tasks')
BEGIN
CREATE TABLE tasks
(
    id         BIGINT PRIMARY KEY,
    title      NVARCHAR(255),
    content    NVARCHAR(255),
    status     INT NOT NULL,
    created_at DATETIME
);
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'sub_tasks')
BEGIN
CREATE TABLE sub_tasks
(
    id         BIGINT PRIMARY KEY,
    parent_id  BIGINT,
    title      NVARCHAR(255),
    content    NVARCHAR(255),
    status     INT NOT NULL,
    created_at DATETIME,
    FOREIGN KEY (parent_id) REFERENCES tasks (id)
);
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'app_users')
BEGIN
CREATE TABLE app_users
(
    id    BIGINT PRIMARY KEY,
    name  NVARCHAR(255),
    email NVARCHAR(255)
);
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'products')
BEGIN
CREATE TABLE products
(
    id    BIGINT PRIMARY KEY,
    name  NVARCHAR(255),
    price DECIMAL
);
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'orders')
BEGIN
CREATE TABLE orders
(
    id           BIGINT IDENTITY(1,1) NOT NULL,
    user_id      BIGINT,
    order_date   DATE,
    total_amount DECIMAL,
    version      BIGINT,
    FOREIGN KEY (user_id) REFERENCES app_users (id),
    PRIMARY KEY (id)
);
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'order_items')
BEGIN
CREATE TABLE order_items
(
    id               BIGINT IDENTITY(1,1) NOT NULL,
    order_id         BIGINT,
    seq_no           INT,
    product_id       BIGINT,
    quantity         INT,
    sub_total_amount DECIMAL,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id),
    PRIMARY KEY (id)
);
END
GO

INSERT INTO tasks (id, title, content, status, created_at)
VALUES (1, N'親タスク1', N'親タスクの内容1', 0, CURRENT_TIMESTAMP),
       (2, N'親タスク2', N'親タスクの内容2', 1, CURRENT_TIMESTAMP);

INSERT INTO sub_tasks (id, parent_id, title, content, status, created_at)
VALUES (101, 1, N'子タスク1-1', N'子タスクの内容1-1', 0, CURRENT_TIMESTAMP),
       (102, 1, N'子タスク1-2', N'子タスクの内容1-2', 2, CURRENT_TIMESTAMP),
       (201, 2, N'子タスク2-1', N'子タスクの内容2-1', 0, CURRENT_TIMESTAMP);

INSERT INTO app_users (id, name, email)
VALUES (1, 'John Doe', 'john.doe@example.com'),
       (2, 'Jane Smith', 'jane.smith@example.com');

INSERT INTO orders (user_id, order_date, total_amount, version)
VALUES (1, '2023-01-01', 2000.00, 0),
       (2, '2023-01-02', 1500.00, 0);

INSERT INTO products (id, name, price)
VALUES (1, N'商品A', 500.00),
       (2, N'商品B', 1000.00),
       (3, N'商品C', 1500.00);

INSERT INTO order_items (order_id, seq_no, product_id, quantity, sub_total_amount)
VALUES (1, 1, 1, 2, 1000.00),
       (1, 2, 2, 1, 1000.00),
       (2, 3, 3, 1, 1500.00);