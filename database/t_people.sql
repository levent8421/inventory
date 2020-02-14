drop table if exists t_people;

create table t_people
(
    id         int(10) auto_increment
        primary key,
    name       varchar(32)  null,
    phone1     varchar(32)  null,
    phone2     varchar(32)  null,
    qq         varchar(32)  null,
    email      varchar(64)  null,
    company    varchar(255) null,
    title      varchar(255) null,
    address    varchar(255) null,
    postalCode varchar(32)  null,
    remark     varchar(255) null
);

alter table t_people
    add column create_time datetime null comment '创建时间' after remark;
alter table t_people
    add column update_time datetime null comment '更新时间' after create_time;
alter table t_people
    add column deleted bit(1) null comment '标记删除' after update_time;

select peo.id          peo_id,
       peo.name        peo_name,
       peo.phone1      peo_phone1,
       peo.phone2      peo_phone2,
       peo.qq          peo_qq,
       peo.email       peo_email,
       peo.company     peo_company,
       peo.title       peo_title,
       peo.address     peo_address,
       peo.postalCode  peo_postalCode,
       peo.remark      peo_remark,
       peo.create_time peo_create_time,
       peo.update_time peo_update_time,
       peo.deleted     peo_deleted
from t_people as peo
where (peo.deleted = false or peo.deleted is null);