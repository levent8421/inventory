package com.monolithiot.inventory.service.general;

import com.monolithiot.inventory.commons.entity.TestItem;
import com.monolithiot.inventory.service.commons.AbstractService;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/1/9 16:04
 * Class Name: TestItemService
 * Author: Levent8421
 * Description:
 * 测试项目相关业务行为定义
 *
 * @author Levent*421
 */
public interface TestItemService extends AbstractService<TestItem> {
    /**
     * 记录测试项
     *
     * @param testItems 测试项
     */
    void log(List<TestItem> testItems);
}
