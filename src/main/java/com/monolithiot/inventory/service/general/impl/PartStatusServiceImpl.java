package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.PartStatus;
import com.monolithiot.inventory.repository.mapper.PartStatusMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.PartStatusService;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/2/12 22:06
 * Class Name: PartStatusServiceImpl
 * Author: Levent8421
 * Description:
 * Part Status 物料状态相关业务行为实现
 *
 * @author Levent8421
 */
@Service
public class PartStatusServiceImpl extends AbstractServiceImpl<PartStatus> implements PartStatusService {
    private final PartStatusMapper partStatusMapper;

    public PartStatusServiceImpl(PartStatusMapper partStatusMapper) {
        super(partStatusMapper);
        this.partStatusMapper = partStatusMapper;
    }
}
