-- 親タスクの挿入
INSERT INTO tasks (id, title, content, status, created_at) VALUES (1, '親タスク1', '親タスクの内容1', 0, CURRENT_TIMESTAMP);
INSERT INTO tasks (id, title, content, status, created_at) VALUES (2, '親タスク2', '親タスクの内容2', 1, CURRENT_TIMESTAMP);

-- 親タスク1に関連する子タスクの挿入
INSERT INTO sub_tasks (id, parent_id, title, content, status, created_at) VALUES (101, 1, '子タスク1-1', '子タスクの内容1-1', 0, CURRENT_TIMESTAMP);
INSERT INTO sub_tasks (id, parent_id, title, content, status, created_at) VALUES (102, 1, '子タスク1-2', '子タスクの内容1-2', 2, CURRENT_TIMESTAMP);

-- 親タスク2に関連する子タスクの挿入
INSERT INTO sub_tasks (id, parent_id, title, content, status, created_at) VALUES (201, 2, '子タスク2-1', '子タスクの内容2-1', 0, CURRENT_TIMESTAMP);


-- Userテーブルへのサンプルデータ挿入
INSERT INTO app_users (id, name, email) VALUES (1, 'John Doe', 'john.doe@example.com');
INSERT INTO app_users (id, name, email) VALUES (2, 'Jane Smith', 'jane.smith@example.com');

-- Orderテーブルへのサンプルデータ挿入
INSERT INTO orders (id, user_id, order_date) VALUES (1, 1, '2023-01-15');
INSERT INTO orders (id, user_id, order_date) VALUES (2, 2, '2023-01-20');

-- Productテーブルへのサンプルデータ挿入
INSERT INTO products (id, name, price, order_id) VALUES (1, 'Laptop', 1200.00, 1);
INSERT INTO products (id, name, price, order_id) VALUES (2, 'Smartphone', 800.00, 2);
