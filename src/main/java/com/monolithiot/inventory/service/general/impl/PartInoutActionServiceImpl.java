package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.PartInoutAction;
import com.monolithiot.inventory.repository.mapper.PartInoutActionMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.PartInoutActionService;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/2/14 19:51
 * Class Name: PartInoutActionServiceImpl
 * Author: Levent8421
 * Description:
 * 物料进出库记录相关业务行为实现
 *
 * @author Levent8421
 */
@Service
public class PartInoutActionServiceImpl extends AbstractServiceImpl<PartInoutAction> implements PartInoutActionService {
    private final PartInoutActionMapper partInoutActionMapper;

    public PartInoutActionServiceImpl(PartInoutActionMapper partInoutActionMapper) {
        super(partInoutActionMapper);
        this.partInoutActionMapper = partInoutActionMapper;
    }
}
