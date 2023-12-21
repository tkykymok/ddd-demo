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


[
SELECT "orders"."id"           AS "id",
       "orders"."user_id"      AS "user_id",
       "orders"."order_date"   AS "order_date",
       "orders"."total_amount" AS "total_amount"
FROM "orders"
WHERE "orders"."id" = ?;


SELECT "ORDER_ITEMS"."ID"               AS "ID",
       "ORDER_ITEMS"."ORDER_ID"         AS "ORDER_ID",
       "ORDER_ITEMS"."QUANTITY"         AS "QUANTITY",
       "ORDER_ITEMS"."PRODUCT_ID"       AS "PRODUCT_ID",
       "product"."ID"                   AS "PRODUCT_ID",
       "product"."NAME"                 AS "PRODUCT_NAME",
       "ORDER_ITEMS"."SUB_TOTAL_AMOUNT" AS "SUB_TOTAL_AMOUNT",
       "product"."PRICE"                AS "product_PRICE",
       "ORDER_ITEMS"."ID"               AS "ID"
FROM "ORDER_ITEMS"
         LEFT OUTER JOIN "PRODUCTS" "product" ON "product"."ORDER_ITEMS" = "ORDER_ITEMS"."ID"
WHERE "ORDER_ITEMS"."ORDER_ID" = ?;

