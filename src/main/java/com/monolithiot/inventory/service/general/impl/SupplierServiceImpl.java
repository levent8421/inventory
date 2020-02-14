package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.Supplier;
import com.monolithiot.inventory.repository.mapper.SupplierMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.SupplierService;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/2/14 16:59
 * Class Name: SupplierServiceImpl
 * Author: Levent8421
 * Description:
 * 供应商相关业务行为定义
 *
 * @author Levent8421
 */
@Service
public class SupplierServiceImpl extends AbstractServiceImpl<Supplier> implements SupplierService {
    private final SupplierMapper supplierMapper;

    public SupplierServiceImpl(SupplierMapper supplierMapper) {
        super(supplierMapper);
        this.supplierMapper = supplierMapper;
    }
}
