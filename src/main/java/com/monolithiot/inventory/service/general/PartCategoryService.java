package com.monolithiot.inventory.service.general;

import com.monolithiot.inventory.commons.entity.PartCategory;
import com.monolithiot.inventory.commons.entity.PartType;
import com.monolithiot.inventory.service.commons.AbstractService;
import com.monolithiot.inventory.web.vo.PartTypeVo;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/12 0:22
 * Class Name: PartCategoryService
 * Author: Levent8421
 * Description:
 * 库存类别相关业务行为定义
 *
 * @author Levent8421
 */
public interface PartCategoryService extends AbstractService<PartCategory> {
    /**
     * Find By PartTypeId
     *
     * @param typeId partTypeId
     * @return Category List
     */
    List<PartCategory> findByTypeId(Integer typeId);

    /**
     * As type-Categories Tree
     *
     * @param types      types
     * @param categories categories
     * @return PartTypeVo List
     */
    List<PartTypeVo> asTree(List<PartType> types, List<PartCategory> categories);
}
