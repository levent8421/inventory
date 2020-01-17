package com.monolithiot.inventory.web.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/1/11 11:26
 * Class Name: TestLogParam
 * Author: Levent8421
 * Description:
 * 测试记录参数
 *
 * @author Levent*421
 */
@Data
public class TestLogParam {
    /**
     * 设备SN 必填
     */
    private String deviceSn;
    /**
     * 最终测试状态 必填
     */
    private Integer state;
    /**
     * 测试时间 必填
     */
    private Date testTime;
    /**
     * 总测试用时(ms) 必填
     */
    private Integer duration;
    /**
     * 订单ID 非必填
     */
    private Integer orderId;
    /**
     * 物料I号 必填
     */
    private String partNo;
    /**
     * 备注 非必填
     */
    private String remark;
    /**
     * 测试项
     */
    private List<TestItemParam> items;
}
