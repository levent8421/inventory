package com.monolithiot.inventory.service.general;

import com.monolithiot.inventory.commons.entity.PartQuantity;
import com.monolithiot.inventory.service.commons.AbstractService;
import com.monolithiot.inventory.service.vo.PartVo;
import com.monolithiot.inventory.service.vo.QuantityBatchUpdateItem;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 19:30
 * Class Name: PartQuantityService
 * Author: Levent8421
 * Description:
 * 物料数量相关业务行为定义
 *
 * @author Levent8421
 */
public interface PartQuantityService extends AbstractService<PartQuantity> {
    /**
     * Find PartQuantity By PartId
     *
     * @param partId partId
     * @return QuantityList
     */
    List<PartQuantity> findByPartId(Integer partId);

    /**
     * 物料库存搜素
     *
     * @param partNoList        物料号列表
     * @param categoryId        类别ID
     * @param clusterId         分类ID
     * @param desc              描述
     * @param storageLocationId 库位ID
     * @return PartVo
     */
    List<PartVo> search(List<String> partNoList, Integer categoryId, Integer clusterId, String desc, Integer storageLocationId);

    /**
     * 查询所有缺货库存 即库存小于最小库存
     *
     * @return quantity list
     */
    List<PartQuantity> outOfStockQuntityList();

    /**
     * Find quantity bu part id and storage location id
     *
     * @param partId            part id
     * @param storageLocationId location id
     * @return quantity object
     */
    PartQuantity findByPartAndStorageLocation(Integer partId, Integer storageLocationId);

    /**
     * 批量出库
     *
     * @param items             items
     * @param storageLocationId storage location id
     * @return quantities
     */
    List<PartQuantity> batchOutbound(List<QuantityBatchUpdateItem> items, Integer storageLocationId);
}
