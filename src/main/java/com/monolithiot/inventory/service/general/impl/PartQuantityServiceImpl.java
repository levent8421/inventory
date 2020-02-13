package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.PartQuantity;
import com.monolithiot.inventory.repository.mapper.PartQuantityMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.PartQuantityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 19:31
 * Class Name: PartQuantityServiceImpl
 * Author: Levent8421
 * Description:
 * Part Quantity 物料数量相关业务行为实现
 *
 * @author Levent8421
 */
@Service
public class PartQuantityServiceImpl extends AbstractServiceImpl<PartQuantity> implements PartQuantityService {
    private final PartQuantityMapper partQuantityMapper;

    public PartQuantityServiceImpl(PartQuantityMapper partQuantityMapper) {
        super(partQuantityMapper);
        this.partQuantityMapper = partQuantityMapper;
    }

    @Override
    public List<PartQuantity> findByPartId(Integer partId) {
        return partQuantityMapper.selectByPartId(partId);
    }
}
