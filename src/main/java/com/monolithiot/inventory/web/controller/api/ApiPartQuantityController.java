package com.monolithiot.inventory.web.controller.api;

import com.monolithiot.inventory.commons.entity.PartQuantity;
import com.monolithiot.inventory.commons.util.TextUtils;
import com.monolithiot.inventory.service.general.PartQuantityService;
import com.monolithiot.inventory.service.vo.PartVo;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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

    /**
     * 搜索库存
     *
     * @param partNoList 物料号列表
     * @param clusterId  分类ID
     * @param categoryId 类别ID
     * @param desc       描述
     * @return GR
     */
    @GetMapping("/_search")
    public GeneralResult<List<PartVo>> search(String partNoList,
                                              Integer clusterId,
                                              Integer categoryId,
                                              String desc) {
        val noList = asPartNoList(partNoList);
        val res = partQuantityService.search(noList, categoryId, clusterId, desc);
        return GeneralResult.ok(res);
    }

    private static final String PART_NO_DELIMITER = ",";

    /**
     * As String List ,delimit by ','
     *
     * @param partNoStr str
     * @return list
     */
    private List<String> asPartNoList(String partNoStr) {
        return TextUtils.isBlank(partNoStr) ? null : Arrays.asList(partNoStr.split(PART_NO_DELIMITER));
    }
}
