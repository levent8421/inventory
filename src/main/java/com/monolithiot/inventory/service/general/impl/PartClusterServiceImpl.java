package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.PartCluster;
import com.monolithiot.inventory.repository.mapper.PartClusterMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.PartClusterService;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 0:04
 * Class Name: PartClusterServiceImpl
 * Author: Levent8421
 * Description:
 * 物料类型相关业务行为实现
 *
 * @author Levent8421
 */
@Service
public class PartClusterServiceImpl extends AbstractServiceImpl<PartCluster> implements PartClusterService {
    private final PartClusterMapper partClusterMapper;

    public PartClusterServiceImpl(PartClusterMapper partClusterMapper) {
        super(partClusterMapper);
        this.partClusterMapper = partClusterMapper;
    }
}
