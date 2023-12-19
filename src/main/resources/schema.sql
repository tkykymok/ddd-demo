CREATE TABLE tasks (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP
);

CREATE TABLE sub_tasks (
    id BIGINT PRIMARY KEY,
    parent_id BIGINT, -- 親タスクへの参照
    title VARCHAR(255),
    content TEXT,
    status INT NOT NULL,
    created_at TIMESTAMP,
    FOREIGN KEY (parent_id) REFERENCES tasks(id)
);