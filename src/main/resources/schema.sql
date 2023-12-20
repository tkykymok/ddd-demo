CREATE TABLE tasks
(
    id         BIGINT PRIMARY KEY,
    title      VARCHAR(255),
    content    TEXT,
    status     INT NOT NULL,
    created_at TIMESTAMP
);

CREATE TABLE sub_tasks
(
    id         BIGINT PRIMARY KEY,
    parent_id  BIGINT, -- 親タスクへの参照
    title      VARCHAR(255),
    content    TEXT,
    status     INT NOT NULL,
    created_at TIMESTAMP,
    FOREIGN KEY (parent_id) REFERENCES tasks (id)
);

CREATE TABLE app_users
(
    id    BIGINT PRIMARY KEY,
    name  VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE orders
(
    id         BIGINT PRIMARY KEY,
    user_id    BIGINT,
    order_date DATE,
    FOREIGN KEY (user_id) REFERENCES app_users (id)
);

CREATE TABLE products
(
    id       BIGINT PRIMARY KEY,
    name     VARCHAR(255),
    price DOUBLE,
    order_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES orders (id)
);
