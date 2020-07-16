package com.monolithiot.inventory.web.controller.api;

import com.monolithiot.inventory.commons.context.Constants;
import com.monolithiot.inventory.commons.entity.PartInoutHistory;
import com.monolithiot.inventory.service.general.PartInoutHistoryService;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/7/16 13:44
 * Class Name: ApiPartInoutHistoryController
 * Author: Levent8421
 * Description:
 * 出入库历史相关数据访问控制器
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/inout-history")
@Slf4j
public class ApiPartInoutHistoryController extends AbstractController {
    private final PartInoutHistoryService partInoutHistoryService;

    public ApiPartInoutHistoryController(PartInoutHistoryService partInoutHistoryService) {
        this.partInoutHistoryService = partInoutHistoryService;
    }

    /**
     * Find Part InOut History list by date range
     *
     * @param start start date
     * @param end   date end
     * @return GR
     */
    @GetMapping("/_by-date-range")
    public GeneralResult<List<PartInoutHistory>> findByDateRange(
            @RequestParam("start") @DateTimeFormat(pattern = Constants.Datetime.DATE_FORMAT) Date start,
            @RequestParam("end") @DateTimeFormat(pattern = Constants.Datetime.DATE_FORMAT) Date end) {
        final List<PartInoutHistory> historyList = partInoutHistoryService.findByDateRangeFetchAll(start, end);
        return GeneralResult.ok(historyList);
    }
}
