package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.Part;
import com.monolithiot.inventory.repository.mapper.PartMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.PartService;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 18:06
 * Class Name: PartServiceImpl
 * Author: Levent8421
 * Description:
 * Part物料相关业务行为实现
 *
 * @author Levent8421
 */
@Service
public class PartServiceImpl extends AbstractServiceImpl<Part> implements PartService {
    private final PartMapper partMapper;

    public PartServiceImpl(PartMapper partMapper) {
        super(partMapper);
        this.partMapper = partMapper;
    }
}
