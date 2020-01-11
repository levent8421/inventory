package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.TestLog;
import com.monolithiot.inventory.commons.entity.User;
import com.monolithiot.inventory.commons.util.RandomUtils;
import com.monolithiot.inventory.repository.mapper.TestLogMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.TestLogService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
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
    private static final int TRACE_SUFFIX_LENGTH = 4;
    public static final int TRACE_TIME_STRING_LENGTH = 10;
    private final TestLogMapper testLogMapper;

    public TestLogServiceImpl(TestLogMapper testLogMapper) {
        super(testLogMapper);
        this.testLogMapper = testLogMapper;
    }

    @Override
    public void log(TestLog testLog, User tester) {
        randomTrace(testLog);
        testLog.setTesterId(tester.getId());
        save(testLog);
    }

    /**
     * Fill Random Trace Id
     *
     * @param testLog test log
     */
    private void randomTrace(TestLog testLog) {
        val traceId = RandomUtils.currentTimeMillisWithIncrement(TRACE_TIME_STRING_LENGTH, TRACE_SUFFIX_LENGTH);
        testLog.setTraceNo(traceId);
    }
}
