-- auto-generated definition
create table t_part_supply
(
    id           int(10) auto_increment
        primary key,
    part_id      int(10)       not null,
    supplier_id  int(10)       not null,
    moq          int(10)       not null,
    deliveryDays int(10)       not null,
    unitPrice    double(10, 5) not null,
    unitTaxPrice double(10, 5) not null,
    isDefault    int(10)       not null,
    url          varchar(255)  null,
    remark       varchar(255)  null
);

alter table t_part_supply
    add column create_time datetime null comment '记录创建时间' after remark;

alter table t_part_supply
    add column update_time datetime null comment '记录更新时间' after create_time;

alter table t_part_supply
    add column deleted bit(1) null comment '删除标记' after update_time;

select psup.id           as psup_id,
       psup.part_id      as psup_part_id,
       psup.supplier_id  as psup_supplier_id,
       psup.moq          as psup_moq,
       psup.deliveryDays as psup_deliveryDays,
       psup.unitPrice    as psup_unitPrice,
       psup.unitTaxPrice as psup_unitTaxPrice,
       psup.isDefault    as psup_isDefault,
       psup.url          as psup_url,
       psup.remark       as psup_remark,
       psup.create_time  as psup_create_time,
       psup.update_time  as psup_update_time,
       psup.deleted      as psup_deleted
from t_part_supply as psup
where (psup.deleted = false or psup.deleted is null);