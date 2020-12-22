package com.monolithiot.inventory.web.controller.api;

import com.github.pagehelper.PageInfo;
import com.monolithiot.inventory.commons.entity.Part;
import com.monolithiot.inventory.commons.exception.BadRequestException;
import com.monolithiot.inventory.commons.exception.InternalServerErrorException;
import com.monolithiot.inventory.commons.util.ParamChecker;
import com.monolithiot.inventory.service.general.PartQuantityService;
import com.monolithiot.inventory.service.general.PartRelationService;
import com.monolithiot.inventory.service.general.PartService;
import com.monolithiot.inventory.service.task.OutboundTask;
import com.monolithiot.inventory.service.task.PartOutboundException;
import com.monolithiot.inventory.service.task.dto.PartOutboundRecord;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import com.monolithiot.inventory.web.vo.PartOutboundParam;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 18:08
 * Class Name: ApiPartController
 * Author: Levent8421
 * Description:
 * Part API物料相关数据访问控制器
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/part")
public class ApiPartController extends AbstractController {
    private final PartService partService;
    private final PartQuantityService partQuantityService;
    private final PartRelationService partRelationService;

    public ApiPartController(PartService partService,
                             PartQuantityService partQuantityService,
                             PartRelationService partRelationService) {
        this.partService = partService;
        this.partQuantityService = partQuantityService;
        this.partRelationService = partRelationService;
    }

    /**
     * 全部物料
     *
     * @param page page
     * @param rows rows per page
     * @return GR
     */
    @GetMapping("/_pagination")
    public GeneralResult<PageInfo<Part>> all(Integer page, Integer rows) {
        page = defaultPage(page);
        rows = defaultRows(rows);
        val parts = partService.list(page, rows);
        return GeneralResult.ok(parts);
    }

    /**
     * 虚拟出库
     *
     * @param id    物料ID
     * @param param 参数
     * @return GR
     */
    @PostMapping("/{id}/_virtual-outbound")
    public GeneralResult<List<PartOutboundRecord>> virtualOutbound(@PathVariable("id") Integer id,
                                                                   @RequestBody PartOutboundParam param) {
        ParamChecker.notNull(param, BadRequestException.class, "No params");
        ParamChecker.notNull(param.getCount(), BadRequestException.class, "count is required!");
        ParamChecker.notNull(param.getStorageLocationId(), BadRequestException.class, "StorageLocationId is required!");
        final Part part = partService.require(id);
        final OutboundTask task = new OutboundTask(partQuantityService, partRelationService);
        try {
            task.init(part, param.getStorageLocationId(), param.getCount())
                    .virtualOutbound();
        } catch (PartOutboundException e) {
            throw new InternalServerErrorException("Outbound error:" + e.getMessage(), e);
        }
        final List<PartOutboundRecord> records = task.getRecords();
        return GeneralResult.ok(records);
    }
}
