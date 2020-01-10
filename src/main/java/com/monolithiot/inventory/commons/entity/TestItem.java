package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/1/9 15:54
 * Class Name: TestItem
 * Author: Levent8421
 * Description:
 * 测试项
 *
 * @author Levent*421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_test_item")
public class TestItem extends AbstractEntity {
    /**
     * 主测试记录ID
     */
    @Column(name = "test_log_id", length = 10, nullable = false)
    private Integer testLogId;
    /**
     * 测试项目名称
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * 测试状态
     */
    @Column(name = "state", length = 2, nullable = false)
    private Integer state;
    /**
     * 测试结果
     */
    @Column(name = "result_value", nullable = false)
    private String resultValue;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
}
