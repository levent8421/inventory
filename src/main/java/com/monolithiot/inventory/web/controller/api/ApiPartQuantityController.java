package com.monolithiot.inventory.web.controller.api;

import com.monolithiot.inventory.commons.entity.PartQuantity;
import com.monolithiot.inventory.commons.exception.BadRequestException;
import com.monolithiot.inventory.commons.util.TextUtils;
import com.monolithiot.inventory.service.general.PartQuantityService;
import com.monolithiot.inventory.service.vo.PartVo;
import com.monolithiot.inventory.service.vo.QuantityBatchUpdateItem;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import com.monolithiot.inventory.web.vo.QuantityBatchOutboundParam;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.monolithiot.inventory.commons.util.ParamChecker.notEmpty;
import static com.monolithiot.inventory.commons.util.ParamChecker.notNull;

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
                                              String desc,
                                              Integer storageLocationId) {
        val noList = asPartNoList(partNoList);
        val res = partQuantityService.search(noList, categoryId, clusterId, desc, storageLocationId);
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

    /**
     * 缺货库存
     *
     * @return GR
     */
    @GetMapping("/_out-of-stock")
    private GeneralResult<List<PartQuantity>> outOfStock() {
        final List<PartQuantity> quantities = partQuantityService.outOfStockQuntityList();
        return GeneralResult.ok(quantities);
    }

    /**
     * 批量出库
     *
     * @param param 参数
     * @return GR
     */
    @PostMapping("/_batch-outbound")
    public GeneralResult<List<PartQuantity>> batchOutbound(@RequestBody QuantityBatchOutboundParam param) {
        final Class<? extends RuntimeException> e = BadRequestException.class;
        notNull(param, e, "Empty params!");
        notNull(param.getStorageLocationId(), e, "StorageLocationId is required!");
        notEmpty(param.getItems(), e, "items is required!");
        for (QuantityBatchUpdateItem item : param.getItems()) {
            notNull(item, e, "null item");
            notNull(item.getPartId(), e, "partId is required!");
            notNull(item.getCount(), e, "count is required!");
        }
        final List<PartQuantity> quantities = partQuantityService.batchOutbound(param.getItems(), param.getStorageLocationId());
        return GeneralResult.ok(quantities);
    }
}
