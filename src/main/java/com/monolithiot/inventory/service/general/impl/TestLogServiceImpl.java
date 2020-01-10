package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.TestLog;
import com.monolithiot.inventory.repository.mapper.TestLogMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.TestLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/1/9 16:05
 * Class Name: TestLogServiceImpl
 * Author: Levent8421
 * Description:
 * 测试记录相关业务行为实现
 *
 * @author Levent*421
 */
@Service
@Slf4j
public class TestLogServiceImpl extends AbstractServiceImpl<TestLog> implements TestLogService {
    private final TestLogMapper testLogMapper;

    public TestLogServiceImpl(TestLogMapper testLogMapper) {
        super(testLogMapper);
        this.testLogMapper = testLogMapper;
    }
}
