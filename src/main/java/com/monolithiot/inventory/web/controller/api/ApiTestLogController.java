package com.monolithiot.inventory.web.controller.api;

import com.github.pagehelper.PageInfo;
import com.monolithiot.inventory.commons.entity.TestItem;
import com.monolithiot.inventory.commons.entity.TestLog;
import com.monolithiot.inventory.service.general.TestItemService;
import com.monolithiot.inventory.service.general.TestLogService;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.util.TestLogUtils;
import com.monolithiot.inventory.web.vo.GeneralResult;
import com.monolithiot.inventory.web.vo.TestLogParam;
import com.monolithiot.inventory.web.vo.TestLogResult;
import lombok.val;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

/**
 * Create By Levent8421
 * Create Time: 2020/1/11 10:54
 * Class Name: ApiTestLogController
 * Author: Levent8421
 * Description:
 * TestLog 数据访问控制器
 *
 * @author Levent*421
 */
@RestController
@RequestMapping("/api/test-log")
public class ApiTestLogController extends AbstractController {
    private final TestLogService testLogService;
    private final TestItemService testItemService;

    public ApiTestLogController(TestLogService testLogService,
                                TestItemService testItemService) {
        this.testLogService = testLogService;
        this.testItemService = testItemService;
    }

    /**
     * 记录日志
     *
     * @param param 参数
     * @return GR
     */
    @PutMapping("/")
    @RequiresPermissions("TEST:W")
    public GeneralResult<TestLogResult> log(@RequestBody TestLogParam param) {
        val pair = TestLogUtils.convertParam2Entity(param);
        val testLog = pair.getO1();
        val tester = requireCurrentUser();
        testLogService.log(testLog, tester);
        val testItems = pair.getO2();
        for (TestItem testItem : testItems) {
            testItem.setTestLogId(testLog.getId());
        }
        testItemService.log(testItems);
        return GeneralResult.ok();
    }

    /**
     * 查询所有日志
     *
     * @param page page
     * @param rows rows
     * @return GR
     */
    @GetMapping("/")
    public GeneralResult<PageInfo<TestLog>> all(Integer page, Integer rows) {
        page = defaultPage(page);
        rows = defaultRows(rows);

        val res = testLogService.listFetchAll(page, rows);
        return GeneralResult.ok(res);
    }
}
