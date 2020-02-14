drop table if exists t_supplier;

create table t_supplier
(
    id         int(10) auto_increment
        primary key,
    name       varchar(255) not null,
    address    varchar(255) null,
    url        varchar(255) null,
    contactor1 varchar(255) null,
    phone1     varchar(255) null,
    other1     varchar(255) null,
    contactor2 varchar(255) null,
    phone2     varchar(255) null,
    other2     varchar(255) null,
    fax        varchar(255) null,
    remark     varchar(255) null
);

alter table t_supplier
    add column create_time datetime null comment '创建时间' after remark;
alter table t_supplier
    add column update_time datetime null comment '更新时间' after create_time;
alter table t_supplier
    add column deleted bit(1) null comment '标记删除' after update_time;


select sup.id          sup_id,
       sup.name        sup_name,
       sup.address     sup_address,
       sup.url         sup_url,
       sup.contactor1  sup_contactor1,
       sup.contactor2  sup_contactor2,
       sup.phone1      sup_phone1,
       sup.phone2      sup_phone2,
       sup.other1      sup_other1,
       sup.other2      sup_other2,
       sup.fax         sup_fax,
       sup.remark      sup_remark,
       sup.create_time sup_create_time,
       sup.update_time sup_update_time,
       sup.deleted     sup_deleted
from t_supplier as sup
where (sup.deleted = false or sup.deleted is null);