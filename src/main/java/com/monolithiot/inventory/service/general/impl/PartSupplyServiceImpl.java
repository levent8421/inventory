package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.PartSupply;
import com.monolithiot.inventory.repository.mapper.PartSupplyMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.PartSupplyService;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/7/16 15:11
 * Class Name: PartSupplyServiceImpl
 * Author: Levent8421
 * Description:
 * 物料-供应商关系相关业务行为实现
 *
 * @author Levent8421
 */
@Service
public class PartSupplyServiceImpl extends AbstractServiceImpl<PartSupply> implements PartSupplyService {
    private final PartSupplyMapper partSupplyMapper;

    public PartSupplyServiceImpl(PartSupplyMapper partSupplyMapper) {
        super(partSupplyMapper);
        this.partSupplyMapper = partSupplyMapper;
    }
}
