drop table if exists t_part_status;

create table t_part_status
(
    id          int(10) auto_increment
        primary key,
    name        varchar(32)  not null,
    description varchar(128) null
);

alter table t_part_status
    add column create_time datetime null comment '创建时间' after description;
alter table t_part_status
    add column update_time datetime null comment '更新时间' after create_time;
alter table t_part_status
    add column deleted bit(1) null comment '标记删除' after update_time;

select ps.id          ps_id,
       ps.name        ps_name,
       ps.description ps_description,
       ps.create_time ps_create_time,
       ps.update_time ps_update_time,
       ps.deleted     ps_deleted
from t_part_status as ps
where (ps.deleted = false or ps.deleted is null);