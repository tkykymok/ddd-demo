-- 親タスクの挿入
INSERT INTO TASKS (ID, TITLE, CONTENT, STATUS, CREATED_AT)
VALUES (1, '親タスク1', '親タスクの内容1', 0, CURRENT_TIMESTAMP);
INSERT INTO TASKS (ID, TITLE, CONTENT, STATUS, CREATED_AT)
VALUES (2, '親タスク2', '親タスクの内容2', 1, CURRENT_TIMESTAMP);

-- 親タスク1に関連する子タスクの挿入
INSERT INTO SUB_TASKS (ID, PARENT_ID, TITLE, CONTENT, STATUS, CREATED_AT)
VALUES (101, 1, '子タスク1-1', '子タスクの内容1-1', 0, CURRENT_TIMESTAMP);
INSERT INTO SUB_TASKS (ID, PARENT_ID, TITLE, CONTENT, STATUS, CREATED_AT)
VALUES (102, 1, '子タスク1-2', '子タスクの内容1-2', 2, CURRENT_TIMESTAMP);

-- 親タスク2に関連する子タスクの挿入
INSERT INTO SUB_TASKS (ID, PARENT_ID, TITLE, CONTENT, STATUS, CREATED_AT)
VALUES (201, 2, '子タスク2-1', '子タスクの内容2-1', 0, CURRENT_TIMESTAMP);

-- Userテーブルへのサンプルデータ挿入
INSERT INTO APP_USERS (ID, NAME, EMAIL)
VALUES (1, 'John Doe', 'john.doe@example.com');
INSERT INTO APP_USERS (ID, NAME, EMAIL)
VALUES (2, 'Jane Smith', 'jane.smith@example.com');

-- Orderテーブルへのサンプルデータ挿入
INSERT INTO ORDERS (USER_ID, ORDER_DATE, TOTAL_AMOUNT)
VALUES (1, '2023-01-01', 2000.00),
       (2, '2023-01-02', 1500.00);

-- Productテーブルへのサンプルデータ挿入
INSERT INTO PRODUCTS (ID, NAME, PRICE)
VALUES (1, '商品A', 500.00),
       (2, '商品B', 1000.00),
       (3, '商品C', 1500.00);

-- OrderItemテーブルへのサンプルデータ挿入
INSERT INTO ORDER_ITEMS (ID, ORDER_ID, SEQ_NO, PRODUCT_ID, QUANTITY, SUB_TOTAL_AMOUNT)
VALUES (100, 1, 1, 1, 2, 1000.00),
       (101, 1, 2, 2, 1, 1000.00),
       (102, 2, 3, 3, 1, 1500.00);