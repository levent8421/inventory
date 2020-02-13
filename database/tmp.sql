SELECT t_part.id                                                                               AS ID,
       t_part.partNo                                                                           AS 料号,
       t_part_category.description                                                             AS 分类,
       t_part_status.description                                                               AS 状态,
       t_part_quantity.quantity                                                                AS 库存,
       (case when t_part_quantity1.quantity is null then 0 else t_part_quantity1.quantity end) AS 浙大园,
       (case when t_part_quantity2.quantity is null then 0 else t_part_quantity2.quantity end) AS 沃兰特,
       (case when t_part_quantity3.quantity is null then 0 else t_part_quantity3.quantity end) AS 恒烁,
       t_part.model                                                                            AS 型号,
       t_part.packaging                                                                        AS 封装,
       t_part.description                                                                      AS 描述
FROM t_part
         LEFT JOIN t_part_category ON t_part.part_category_id = t_part_category.id
         LEFT JOIN t_part_status ON t_part.part_status_id = t_part_status.id
         LEFT JOIN (SELECT part_id, quantity FROM t_part_quantity where storage_location_id = 1) AS t_part_quantity
                   ON t_part.id = t_part_quantity.part_id
         LEFT JOIN (SELECT * FROM t_part_quantity WHERE storage_location_id = 1) AS t_part_quantity1
                   ON t_part_quantity1.part_id = t_part.id
         LEFT JOIN (SELECT * FROM t_part_quantity WHERE storage_location_id = 2) AS t_part_quantity2
                   ON t_part_quantity2.part_id = t_part.id
         LEFT JOIN (SELECT * FROM t_part_quantity WHERE storage_location_id = 3) AS t_part_quantity3
                   ON t_part_quantity3.part_id = t_part.id
WHERE t_part.partNo IN ('30000001', '30000002')
  AND (t_part.description LIKE '%电容%' OR t_part.model LIKE '%电容%' OR t_part.packaging LIKE '%电容%')
  AND t_part.part_category_id = 2
ORDER BY t_part.partNo;

desc t_part;

select *
from t_part as p
         left outer join t_part_category as pc on p.part_category_id = pc.id
         left outer join t_part_status as ps on p.part_status_id = ps.id
         left outer join t_part_quantity as pq on pq.part_id = p.id;


select * from t_part_quantity