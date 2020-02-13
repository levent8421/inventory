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

select pq.id                  pq_id,
       pq.part_id             pq_part_id,
       pq.storage_location_id pq_storage_location_id,
       pq.binNo               pq_binNo,
       pq.quantity            pq_quantity,
       pq.create_time         pq_create_time,
       pq.update_time         pq_update_time,
       pq.deleted             pq_deleted,
       p.id                   p_id,
       p.user_id              p_user_id,
       p.part_category_id     p_part_category_id,
       p.part_status_id       p_part_status_id,
       p.part_cluster_id      p_part_cluster_id,
       p.partNo               p_partNo,
       p.description          p_description,
       p.model                p_model,
       p.packaging            p_packaging,
       p.brand                p_brand,
       p.packingQty           p_packingQty,
       p.version              p_version,
       p.create_time          p_create_time,
       p.update_time          p_update_time,
       p.deleted              p_deleted,
       pc.id                  pc_id,
       pc.part_type_id        pc_part_type_id,
       pc.name                pc_name,
       pc.description         pc_description,
       pc.partNoPrefix        pc_partNoPrefix,
       pc.partNoLength        pc_partNoLength,
       pc.create_time         pc_create_time,
       pc.update_time         pc_update_time,
       pc.deleted             pc_deleted,
       pcl.id                 pcl_id,
       pcl.name               pcl_name,
       pcl.description        pcl_description,
       pcl.create_time        pcl_create_time,
       pcl.update_time        pcl_update_time,
       pcl.deleted            pcl_deleted,
       ps.id                  ps_id,
       ps.name                ps_name,
       ps.description         ps_description,
       ps.create_time         ps_create_time,
       ps.update_time         ps_update_time,
       ps.deleted             ps_deleted
from t_part_quantity as pq
         left outer join t_part as p on pq.part_id = p.id
         left outer join t_part_category as pc on p.part_category_id = pc.id
         left outer join t_part_cluster as pcl on p.part_cluster_id = pcl.id
         left outer join t_part_status as ps on p.part_status_id = ps.id
where ((pq.deleted = false or pq.deleted is null) and (p.deleted = false or p.deleted is null) and
       (pc.deleted = false or pc.deleted is null) and (pcl.deleted = false or pcl.deleted is null) and
       (ps.deleted = false or ps.deleted is null))
  and (p.part_category_id = 2 and (p.description like ? or p.model like ? or p.model like ?) and p.partNo in (?, ?))