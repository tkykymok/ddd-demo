-- 親タスクの挿入
INSERT INTO tasks (id, title, content, status, created_at) VALUES (1, '親タスク1', '親タスクの内容1', 0, CURRENT_TIMESTAMP);
INSERT INTO tasks (id, title, content, status, created_at) VALUES (2, '親タスク2', '親タスクの内容2', 1, CURRENT_TIMESTAMP);

-- 親タスク1に関連する子タスクの挿入
INSERT INTO sub_tasks (id, parent_id, title, content, status, created_at) VALUES (101, 1, '子タスク1-1', '子タスクの内容1-1', 0, CURRENT_TIMESTAMP);
INSERT INTO sub_tasks (id, parent_id, title, content, status, created_at) VALUES (102, 1, '子タスク1-2', '子タスクの内容1-2', 2, CURRENT_TIMESTAMP);

-- 親タスク2に関連する子タスクの挿入
INSERT INTO sub_tasks (id, parent_id, title, content, status, created_at) VALUES (201, 2, '子タスク2-1', '子タスクの内容2-1', 0, CURRENT_TIMESTAMP);