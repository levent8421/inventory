drop table if exists t_part_category;

-- auto-generated definition
create table t_part_category
(
    id           int(10) auto_increment
        primary key,
    part_type_id int(10)      not null,
    name         varchar(32)  null,
    description  varchar(128) null,
    partNoPrefix varchar(32)  null,
    partNoLength int(10)      not null
);

alter table t_part_category
    add column create_time datetime null after partNoLength;
alter table t_part_category
    add column update_time datetime null after create_time;
alter table t_part_category
    add column deleted bit(1) null after update_time;

select pc.id           pc_id,
       pc.part_type_id pc_part_type_id,
       pc.name         pc_name,
       pc.description  pc_description,
       pc.partNoPrefix pc_partNoPrefix,
       pc.partNoLength pc_partNoLength,
       pc.create_time  pc_create_time,
       pc.update_time  pc_update_time,
       pc.deleted      pc_deleted
from t_part_category as pc
where (pc.deleted = false or pc.deleted is null);