CREATE TABLE TASKS
(
    ID         BIGINT PRIMARY KEY,
    TITLE      VARCHAR(255),
    CONTENT    TEXT,
    STATUS     INT NOT NULL,
    CREATED_AT TIMESTAMP
);

CREATE TABLE SUB_TASKS
(
    ID         BIGINT PRIMARY KEY,
    PARENT_ID  BIGINT, -- 親タスクへの参照
    TITLE      VARCHAR(255),
    CONTENT    TEXT,
    STATUS     INT NOT NULL,
    CREATED_AT TIMESTAMP,
    FOREIGN KEY (PARENT_ID) REFERENCES TASKS (ID)
);

CREATE TABLE APP_USERS
(
    ID    BIGINT PRIMARY KEY,
    NAME  VARCHAR(255),
    EMAIL VARCHAR(255)
);

CREATE TABLE ORDERS
(
    ID           BIGINT PRIMARY KEY,
    USER_ID      BIGINT,
    ORDER_DATE   DATE,
    TOTAL_AMOUNT DECIMAL,
    FOREIGN KEY (USER_ID) REFERENCES APP_USERS (ID)
);

CREATE TABLE PRODUCTS
(
    ID       BIGINT PRIMARY KEY,
    NAME     VARCHAR(255),
    PRICE    DECIMAL
);

CREATE TABLE ORDER_ITEMS
(
    ID               BIGINT PRIMARY KEY,
    ORDER_ID         BIGINT,
    PRODUCT_ID       BIGINT,
    QUANTITY         INT,
    SUB_TOTAL_AMOUNT DECIMAL,
    FOREIGN KEY (ORDER_ID) REFERENCES ORDERS (ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS (ID)
);