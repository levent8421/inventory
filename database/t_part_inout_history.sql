create table t_part_inout_history
(
    id                   int(10) auto_increment
        primary key,
    user_id              int(10)      not null,
    part_id              int(10)      not null,
    part_inout_action_id int(10)      not null,
    storage_location_id  int(10)      not null,
    binNo                varchar(32)  null,
    quantity             int(10)      not null,
    quantity_before      int(10)      null,
    quantity_after       int(10)      null,
    time                 datetime(6)  null,
    remark               varchar(255) null,
    traceNo              varchar(255) null,
    create_time          datetime     null comment '记录创建时间',
    update_time          datetime     null comment '记录上次更新时间',
    deleted              bit(1)       null comment '删除标记'
);

create index part_id
    on t_part_inout_history (part_id);

alter table t_part_inout_history
    add column create_time datetime null comment '记录创建时间' after traceNo;
alter table t_part_inout_history
    add column update_time datetime null comment '记录上次更新时间' after create_time;
alter table t_part_inout_history
    add column deleted bit(1) null comment '删除标记' after update_time;


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
       pih.deleted              as pih_deleted
from t_part_inout_history as pih
where (pih.deleted = false or pih.deleted is null);
