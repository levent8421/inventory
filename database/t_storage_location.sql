drop table if exists t_storage_location;

create table t_storage_location
(
    id       int(10) auto_increment
        primary key,
    code     varchar(32) null,
    location varchar(64) null
);

alter table t_storage_location
    add column create_time datetime null comment '创建时间' after location;
alter table t_storage_location
    add column update_time datetime null comment '更新时间' after create_time;
alter table t_storage_location
    add column deleted bit(1) null comment '标记删除' after update_time;

select sl.id          sl_id,
       sl.code        sl_code,
       sl.location    sl_location,
       sl.create_time sl_create_time,
       sl.update_time sl_update_time,
       sl.deleted     sl_deleted
from t_storage_location as sl
where (sl.deleted = false or sl.deleted is null);