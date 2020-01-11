package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * Create By Levent8421
 * Create Time: 2020/1/9 15:40
 * Class Name: TestLog
 * Author: Levent8421
 * Description:
 * 测试记录
 *
 * @author Levent*421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_test_log")
public class TestLog extends AbstractEntity {
    /**
     * 测试编号
     */
    @Column(name = "trace_no", nullable = false)
    private String traceNo;
    /**
     * 测试员
     */
    @Column(name = "tester_id", length = 10, nullable = false)
    private Integer testerId;
    /**
     * 测试人对象
     */
    private User tester;
    /**
     * 测试时间
     */
    @Column(name = "test_time", nullable = false)
    private Date testTime;
    /**
     * 测试耗时
     */
    @Column(name = "duration", length = 10)
    private Integer duration;
    /**
     * 订单ID
     */
    @Column(name = "product_order_id", length = 10)
    private Integer productOrderId;
    /**
     * 测试物料号
     */
    @Column(name = "product_part_id", length = 10, nullable = false)
    private Integer productPartId;
    /**
     * 设备SN
     */
    @Column(name = "device_sn", nullable = false)
    private String deviceSn;
    /**
     * 测试状态
     */
    @Column(name = "state", length = 2, nullable = false)
    private Integer state;
    /**
     * 备注
     */
    @Column(name = "remark", nullable = false)
    private String remark;
}
