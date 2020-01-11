drop table if exists t_test_item;

# 表结构定义

create table t_test_item
(
    id           int(10)      not null auto_increment primary key comment 'ID',
    test_log_id  int(10)      not null comment '测试记录ID',
    name         varchar(255) not null comment '测试项名称',
    state        int(2)       not null comment '测试结果（状态）',
    result_value varchar(255) null comment '测试结果（值）',
    remark       varchar(255) null comment '备注',
    create_time  datetime     not null comment '记录创建时间',
    update_time  datetime     not null comment '记录更新时间',
    deleted      bit(1)       not null comment '是否标记删除'
);

alter table t_test_item
    add column test_time datetime not null comment '测试时间' after state;
alter table t_test_item
    add column duration int(10) not null comment '测试耗时ms' after test_time;

select ti.id           ti_id,
       ti.test_log_id  ti_test_log_id,
       ti.name         ti_name,
       ti.state        ti_state,
       ti.test_time    ti_test_time,
       ti.duration     ti_duration,
       ti.result_value ti_result_value,
       ti.remark       ti_remark,
       ti.create_time  ti_create_time,
       ti.update_time  ti_update_time,
       ti.deleted      ti_deleted
from t_test_item as ti
where ti.deleted = false;