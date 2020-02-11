drop table if exists t_part_type;

-- auto-generated definition
create table t_part_type
(
    id          int(10) auto_increment
        primary key,
    name        varchar(32)  not null,
    description varchar(128) null
);



alter table t_part_type
    add column create_time datetime null after description;
alter table t_part_type
    add column update_time datetime null after create_time;
alter table t_part_type
    add column deleted bit(1) null after update_time;

select pt.id          pt_id,
       pt.name        pt_name,
       pt.description pt_description,
       pt.create_time pt_create_time,
       pt.update_time pt_update_time,
       pt.deleted     pt_deleted
from t_part_type as pt
where (pt.deleted = false or pt.deleted is null);