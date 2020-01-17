package com.monolithiot.inventory.web.util;

import com.monolithiot.inventory.commons.entity.TestItem;
import com.monolithiot.inventory.commons.entity.TestLog;
import com.monolithiot.inventory.commons.exception.BadRequestException;
import com.monolithiot.inventory.commons.vo.Tuple2;
import com.monolithiot.inventory.web.vo.TestItemParam;
import com.monolithiot.inventory.web.vo.TestLogParam;
import lombok.val;

import java.util.List;
import java.util.stream.Collectors;

import static com.monolithiot.inventory.commons.util.ParamChecker.notEmpty;
import static com.monolithiot.inventory.commons.util.ParamChecker.notNull;

/**
 * Create By Levent8421
 * Create Time: 2020/1/11 13:46
 * Class Name: TestLogUtils
 * Author: Levent8421
 * Description:
 * 测试记录相关工具类
 *
 * @author Levent*421
 */
public class TestLogUtils {
    /**
     * 检查测试日志参数
     *
     * @param param 参数
     */
    public static void checkTestLogParam(TestLogParam param) {
        val ex = BadRequestException.class;
        notNull(param, ex, "No Param!");
        notEmpty(param.getDeviceSn(), ex, "SN必填！");
        notNull(param.getState(), ex, "最终测试状态必填！");
        notNull(param.getTestTime(), ex, "测试时间必填！");
        notNull(param.getDuration(), ex, "测试耗时必填！");
        notEmpty(param.getPartNo(), ex, "物料号必填！");
        if (param.getItems() != null) {
            for (TestItemParam itemParam : param.getItems()) {
                checkTestItemParam(itemParam);
            }
        }
    }

    /**
     * 检查测试项参数
     *
     * @param param 参数
     */
    public static void checkTestItemParam(TestItemParam param) {
        val ex = BadRequestException.class;
        notNull(param, ex, "测试项不能为空！");
        notEmpty(param.getName(), ex, "测试项名称必填！");
        notNull(param.getState(), ex, "测试项状态必填！");
        notNull(param.getTestTime(), ex, "测试时间必填！");
        notNull(param.getDuration(), ex, "测试项测试耗时必填！");
    }

    /**
     * 转换请求参数为实体类
     *
     * @param param param
     * @return TestLog , TestItem
     */
    public static Tuple2<TestLog, List<TestItem>> convertParam2Entity(TestLogParam param) {
        checkTestLogParam(param);
        val testLog = new TestLog();
        testLog.setTestTime(param.getTestTime());
        testLog.setDuration(param.getDuration());
        testLog.setDeviceSn(param.getDeviceSn());
        testLog.setProductOrderId(param.getOrderId());
        testLog.setProductPartNo(param.getPartNo());
        testLog.setState(param.getState());
        testLog.setRemark(param.getRemark());

        List<TestItem> items = null;
        if (param.getItems() != null) {
            items = param.getItems().stream()
                    .map(TestLogUtils::convertParam2TestItem)
                    .collect(Collectors.toList());
        }

        return Tuple2.of(testLog, items);
    }

    /**
     * 转换测试项参数为测试项实体对象
     *
     * @param param param
     * @return test item entity
     */
    public static TestItem convertParam2TestItem(TestItemParam param) {
        val testItem = new TestItem();
        testItem.setName(param.getName());
        testItem.setState(param.getState());
        testItem.setTestTime(param.getTestTime());
        testItem.setDuration(param.getDuration());
        testItem.setResultValue(param.getResultValue());
        testItem.setRemark(param.getRemark());
        return testItem;
    }
}
