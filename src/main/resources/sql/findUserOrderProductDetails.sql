SELECT u.id         AS userId,
       u.name       AS userName,
       u.email      AS userEmail,
       o.id         AS orderId,
       o.order_date AS orderDate,
       p.id         AS productId,
       p.name       AS productName,
       p.price      AS productPrice
FROM app_users u
         JOIN orders o ON u.id = o.user_id
         JOIN products p ON o.id = p.order_id;