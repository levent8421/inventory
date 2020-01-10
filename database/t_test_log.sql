drop table if exists t_test_log;

# 表结构定义
create table t_test_log
(
    id               int(10)      not null auto_increment primary key comment 'ID',
    trace_no         varchar(255) not null comment '测试记录号',
    tester_id        int(10)      not null comment '测试员ID',
    product_order_id int(10)      null comment '订单ID',
    product_part_id  int(10)      not null comment '物料ID',
    device_sn        varchar(255) not null comment '设备序列号',
    state            int(1)       not null comment '测试状态',
    remark           varchar(255) null comment '备注',
    create_time      datetime     not null comment '记录创建时间',
    update_time      datetime     not null comment '记录更新时间',
    deleted          bit(1)       not null comment '是否被标记删除'
);

# 基本查询语句
select tl.id               tl_id,
       tl.trace_no         tl_trace_no,
       tl.tester_id        tl_tester_id,
       tl.product_order_id tl_product_order_id,
       tl.product_part_id  tl_product_part_id,
       tl.device_sn        tl_device_sn,
       tl.state            tl_state,
       tl.remark           tl_remark,
       tl.create_time      tl_create_time,
       tl.update_time      tl_update_time,
       tl.deleted          tl_deleted
from t_test_log as tl
where tl.deleted = false;