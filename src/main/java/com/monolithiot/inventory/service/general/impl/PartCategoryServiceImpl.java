package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.PartCategory;
import com.monolithiot.inventory.repository.mapper.PartCategoryMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.PartCategoryService;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/2/12 0:23
 * Class Name: PartCategoryServiceImpl
 * Author: Levent8421
 * Description:
 * 库存类别相关业务行为实现
 *
 * @author Levent8421
 */
@Service
public class PartCategoryServiceImpl extends AbstractServiceImpl<PartCategory> implements PartCategoryService {
    private final PartCategoryMapper partCategoryMapper;

    public PartCategoryServiceImpl(PartCategoryMapper partCategoryMapper) {
        super(partCategoryMapper);
        this.partCategoryMapper = partCategoryMapper;
    }
}
