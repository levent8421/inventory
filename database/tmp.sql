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
  and (p.part_category_id = 2 and (p.description like ? or p.model like ? or p.model like ?) and p.partNo in (?, ?));



SELECT DATE(t2.time)                 AS '日期',
       TIME(t2.time)                 AS '时间',
       t5.partNo,
       t3.description,
       t2.quantity,
       t4.`code`,
       t4.location,
       t2.binNo,
       t7.description                AS '分类',
       t5.model,
       t5.packaging,
       t5.description                AS '描述',
       t6.unitPrice                  AS '未税单价',
       t6.unitTaxPrice               AS '含税单价',
       t6.unitPrice * t2.quantity    AS '未税总价',
       t6.unitTaxPrice * t2.quantity AS '含税总价'
FROM (
         SELECT *
         FROM t_part_inout_history AS t1
         WHERE t1.time >= "2020-07-06"
           AND t1.time <= "2020-07-12"
     ) AS t2
         LEFT JOIN t_part_inout_action AS t3 ON t2.part_inout_action_id = t3.id
         LEFT JOIN t_storage_location AS t4 ON t2.storage_location_id = t4.id
         LEFT JOIN t_part AS t5 ON t2.part_id = t5.id
         LEFT JOIN (
    SELECT *
    FROM t_part_supply
    WHERE t_part_supply.isDefault = 1
) AS t6 ON t2.part_id = t6.part_id
         LEFT JOIN t_part_category AS t7 ON t5.part_category_id = t7.id
ORDER BY t5.partNo,
         t2.time;



select pih.id                   as pih_id,
       pih.user_id              as pih_user_id,
       pih.part_id              as pih_part_id,
       pih.part_inout_action_id as pih_part_inout_action_id,
       pih.storage_location_id  as pih_storage_location_id,
       pih.binNo                as pih_binNo,
       pih.quantity             as pih_quantity,
       pih.quantity_before      as pih_quantity_before,
       pih.quantity_after       as pih_quantity_after,
       pih.time                 as pih_time,
       pih.remark               as pih_remark,
       pih.traceNo              as pih_traceNo,
       pih.create_time          as pih_create_time,
       pih.update_time          as pih_update_time,
       pih.deleted              as pih_deleted,
       pia.id                      pia_id,
       pia.name                    pia_name,
       pia.description             pia_description,
       pia.direction               pia_direction,
       pia.create_time             pia_create_time,
       pia.update_time             pia_update_time,
       pia.deleted                 pia_deleted,
       sl.id                       sl_id,
       sl.code                     sl_code,
       sl.location                 sl_location,
       sl.create_time              sl_create_time,
       sl.update_time              sl_update_time,
       sl.deleted                  sl_deleted,
       p.id                        p_id,
       p.user_id                   p_user_id,
       p.part_category_id          p_part_category_id,
       p.part_status_id            p_part_status_id,
       p.part_cluster_id           p_part_cluster_id,
       p.partNo                    p_partNo,
       p.description               p_description,
       p.model                     p_model,
       p.packaging                 p_packaging,
       p.brand                     p_brand,
       p.packingQty                p_packingQty,
       p.version                   p_version,
       p.create_time               p_create_time,
       p.update_time               p_update_time,
       p.deleted                   p_deleted,
       psup.id                  as psup_id,
       psup.part_id             as psup_part_id,
       psup.supplier_id         as psup_supplier_id,
       psup.moq                 as psup_moq,
       psup.deliveryDays        as psup_deliveryDays,
       psup.unitPrice           as psup_unitPrice,
       psup.unitTaxPrice        as psup_unitTaxPrice,
       psup.isDefault           as psup_isDefault,
       psup.url                 as psup_url,
       psup.remark              as psup_remark,
       psup.create_time         as psup_create_time,
       psup.update_time         as psup_update_time,
       psup.deleted             as psup_deleted,
       pc.id                       pc_id,
       pc.part_type_id             pc_part_type_id,
       pc.name                     pc_name,
       pc.description              pc_description,
       pc.partNoPrefix             pc_partNoPrefix,
       pc.partNoLength             pc_partNoLength,
       pc.create_time              pc_create_time,
       pc.update_time              pc_update_time,
       pc.deleted                  pc_deleted
from (select * from t_part_inout_history where time between '2020-07-06' and '2020-07-7') as pih
         left outer join t_part_inout_action as pia on pih.part_inout_action_id = pia.id
         left outer join t_storage_location as sl on pih.storage_location_id = sl.id
         left outer join t_part as p on pih.part_id = p.id
         left outer join (select * from t_part_supply where isDefault = 1) as psup on pih.part_id = psup.part_id
         left outer join t_part_category as pc on pih.part_id = p.id;


select count(0)
from t_part_inout_history
where time between '2020-07-06' and '2020-07-7';

select count(0)
from (select * from t_part_inout_history where time between '2020-07-06' and '2020-07-7') as pih
         left outer join t_part_inout_action as pia on pih.part_inout_action_id = pia.id
         left outer join t_storage_location as sl on pih.storage_location_id = sl.id
         left outer join t_part as p on pih.part_id = p.id
         left outer join (select * from t_part_supply where isDefault = 1) as psup on pih.part_id = psup.part_id
         left outer join t_part_category as pc on p.part_category_id = pc.id;


select pq.id                  pq_id,
       pq.part_id             pq_part_id,
       pq.storage_location_id pq_storage_location_id,
       pq.binNo               pq_binNo,
       pq.quantity            pq_quantity,
       pq.minQuantity as      pq_min_quantity,
       pq.create_time         pq_create_time,
       pq.update_time         pq_update_time,
       pq.deleted             pq_deleted,
       sl.id                  sl_id,
       sl.code                sl_code,
       sl.location            sl_location,
       sl.create_time         sl_create_time,
       sl.update_time         sl_update_time,
       sl.deleted             sl_deleted,
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
       p.deleted              p_deleted
from t_part_quantity as pq
         left outer join t_part as p on pq.part_id = p.id
         left outer join t_part_category as pc on p.part_category_id = pc.id
         left outer join t_storage_location as sl on pq.storage_location_id = sl.id
where pq.quantity < pq.minQuantity
