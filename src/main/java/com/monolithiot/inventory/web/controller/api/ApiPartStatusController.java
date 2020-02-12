package com.monolithiot.inventory.web.controller.api;

import com.monolithiot.inventory.commons.entity.PartStatus;
import com.monolithiot.inventory.service.general.PartStatusService;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/12 22:07
 * Class Name: ApiPartStatusController
 * Author: Levent8421
 * Description:
 * 物料状态相关API数据访问控制器
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/part-status")
public class ApiPartStatusController extends AbstractController {
    private final PartStatusService partStatusService;

    public ApiPartStatusController(PartStatusService partStatusService) {
        this.partStatusService = partStatusService;
    }

    /**
     * Find All Status
     *
     * @return GR
     */
    @GetMapping("/")
    public GeneralResult<List<PartStatus>> all() {
        val allStatus = partStatusService.all();
        return GeneralResult.ok(allStatus);
    }
}
