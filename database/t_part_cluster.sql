drop table if exists t_part_cluster;

create table t_part_cluster
(
    id          int(10) auto_increment
        primary key,
    name        varchar(32)  not null,
    description varchar(128) null
);


alter table t_part_cluster
    add column create_time datetime null comment '创建时间' after description;
alter table t_part_cluster
    add column update_time datetime null comment '更新时间' after create_time;
alter table t_part_cluster
    add column deleted bit(1) null comment '标记删除' after update_time;

select pcl.id          pcl_id,
       pcl.name        pcl_name,
       pcl.description pcl_description,
       pcl.create_time pcl_create_time,
       pcl.update_time pcl_update_time,
       pcl.deleted     pcl_deleted
from t_part_cluster as pcl
where (pcl.deleted = false or pcl.deleted is null);