package com.monolithiot.inventory.web.controller.api;

import com.monolithiot.inventory.commons.entity.PartQuantity;
import com.monolithiot.inventory.service.general.PartQuantityService;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 19:32
 * Class Name: ApiPartQuantityController
 * Author: Levent8421
 * Description:
 * 物料数量相关API数据访问控制器
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/part-quantity")
public class ApiPartQuantityController extends AbstractController {
    private final PartQuantityService partQuantityService;

    public ApiPartQuantityController(PartQuantityService partQuantityService) {
        this.partQuantityService = partQuantityService;
    }

    /**
     * Find PartQuantity List By PartId
     *
     * @param partId partId
     * @return GR
     */
    @GetMapping("/_by-part")
    public GeneralResult<List<PartQuantity>> findByPart(@RequestParam("partId") Integer partId) {
        val quantityList = partQuantityService.findByPartId(partId);
        return GeneralResult.ok(quantityList);
    }
}
