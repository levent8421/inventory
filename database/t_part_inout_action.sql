drop table if exists t_part_inout_action;

create table t_part_inout_action
(
    id          int(10) auto_increment
        primary key,
    name        varchar(32)  not null,
    description varchar(128) null,
    direction   int(6)       not null
);

alter table t_part_inout_action
    add column create_time datetime null comment '创建时间' after direction;
alter table t_part_inout_action
    add column update_time datetime null comment '更新时间' after create_time;
alter table t_part_inout_action
    add column deleted bit(1) null comment '标记删除' after update_time;

select pia.id          pia_id,
       pia.name        pia_name,
       pia.description pia_description,
       pia.direction   pia_direction,
       pia.create_time pia_create_time,
       pia.update_time pia_update_time,
       pia.deleted     pia_deleted
from t_part_inout_action as pia
where (pia.deleted = false or pia.deleted is null);