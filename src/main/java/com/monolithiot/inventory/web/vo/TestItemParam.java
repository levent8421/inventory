package com.monolithiot.inventory.web.vo;

import lombok.Data;

import java.util.Date;

/**
 * Create By Levent8421
 * Create Time: 2020/1/11 13:42
 * Class Name: TestItemParam
 * Author: Levent8421
 * Description:
 * 测试项log参数
 *
 * @author Levent*421
 */
@Data
public class TestItemParam {
    /**
     * 测试项名称
     */
    private String name;
    /**
     * 状态
     */
    private Integer state;
    /**
     * 测试结果
     */
    private String resultValue;
    /**
     * 测试时间
     */
    private Date testTime;
    /**
     * 测试耗时
     */
    private Double duration;
    /**
     * 备注
     */
    private String remark;
}
