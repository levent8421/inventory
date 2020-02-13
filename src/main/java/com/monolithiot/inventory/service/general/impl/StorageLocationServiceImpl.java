package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.StorageLocation;
import com.monolithiot.inventory.repository.mapper.StorageLocationMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.StorageLocationService;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 10:30
 * Class Name: StorageLocationServiceImpl
 * Author: Levent8421
 * Description:
 * 库存位置相关业务行为实现
 *
 * @author Levent8421
 */
@Service
public class StorageLocationServiceImpl extends AbstractServiceImpl<StorageLocation> implements StorageLocationService {
    private final StorageLocationMapper storageLocationMapper;

    public StorageLocationServiceImpl(StorageLocationMapper storageLocationMapper) {
        super(storageLocationMapper);
        this.storageLocationMapper = storageLocationMapper;
    }
}
