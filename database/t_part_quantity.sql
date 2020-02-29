drop table if exists t_part_quantity;

create table t_part_quantity
(
    id                  int(10) auto_increment
        primary key,
    part_id             int(10)     not null,
    storage_location_id int(10)     not null,
    binNo               varchar(32) null,
    quantity            int(10)     not null
);

create index part_id
    on t_part_quantity (part_id);
alter table t_part_quantity
    add column create_time datetime null comment '创建时间' after quantity;
alter table t_part_quantity
    add column update_time datetime null comment '更新时间' after create_time;
alter table t_part_quantity
    add column deleted bit(1) null comment '标记删除' after update_time;

select pq.id                  pq_id,
       pq.part_id             pq_part_id,
       pq.storage_location_id pq_storage_location_id,
       pq.binNo               pq_binNo,
       pq.quantity            pq_quantity,
       pq.create_time         pq_create_time,
       pq.update_time         pq_update_time,
       pq.deleted             pq_deleted
from t_part_quantity as pq
where (pq.deleted = false or pq.deleted is null);