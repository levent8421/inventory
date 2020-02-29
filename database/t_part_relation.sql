drop table if exists t_part_relation;

create table t_part_relation
(
    id            int(10) auto_increment
        primary key,
    part_id       int(10)           not null,
    part_id_child int(10)           not null,
    quantity      int(10)           not null,
    loss          int(10) default 0 not null,
    remark        text              null
);

alter table t_part_relation
    add column create_time datetime null comment '创建时间' after remark;
alter table t_part_relation
    add column update_time datetime null comment '更新时间' after create_time;
alter table t_part_relation
    add column deleted bit(1) null comment '标记删除' after update_time;