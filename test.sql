SELECT "orders"."ID"          AS "ID",
       "orders"."USER_ID"     AS "USER_ID",
       "orders"."ORDER_DATE"  AS "ORDER_DATE",
       "totalAmount"."ORDERS" AS "TOTALAMOUNT_ORDERS",
       "totalAmount"."VALUE"  AS "TOTALAMOUNT_VALUE"
FROM "orders"
         LEFT OUTER JOIN "MONEY" "totalAmount" ON "totalAmount"."ORDERS" = "orders"."ID"
WHERE "orders"."ID" = ?;


SELECT "orders"."ID"           AS "ID",
       "orders"."USER_ID"      AS "USER_ID",
       "orders"."ORDER_DATE"   AS "ORDER_DATE",
       "orders"."TOTAL_AMOUNT" AS "TOTAL_AMOUNT",
FROM "orders"
WHERE "orders"."ID" = ?;



SELECT "orders"."id"           AS "id",
       "orders"."user_id"      AS "user_id",
       "orders"."order_date"   AS "order_date",
       "orders"."total_amount" AS "total_amount"
FROM "orders"
WHERE "orders"."id" = ?;



SELECT "ORDERS"."ORDER_DATE" AS "ORDER_DATE", "ORDERS"."USER_ID" AS "USER_ID", "ORDERS"."TOTAL_AMOUNT" AS "TOTAL_AMOUNT"
FROM "ORDERS"
WHERE "ORDERS"."ID" = ?;