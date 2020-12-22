package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.Part;
import com.monolithiot.inventory.commons.entity.PartQuantity;
import com.monolithiot.inventory.commons.util.TextUtils;
import com.monolithiot.inventory.repository.mapper.PartQuantityMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.PartQuantityService;
import com.monolithiot.inventory.service.vo.PartVo;
import com.monolithiot.inventory.service.vo.QuantityBatchUpdateItem;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
@Slf4j
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

    @Override
    public List<PartVo> search(List<String> partNoList, Integer categoryId, Integer clusterId, String desc, Integer storageLocationId) {
        if (TextUtils.isBlank(desc)) {
            desc = null;
        } else {
            desc = "%" + desc + "%";
        }
        val quantities = partQuantityMapper.search(partNoList, desc, categoryId, clusterId, storageLocationId);
        val partQuantitiesMap = new HashMap<Integer, List<PartQuantity>>(16);
        val partMap = new HashMap<Integer, Part>(16);
        for (final PartQuantity quantity : quantities) {
            val quantityList = partQuantitiesMap.computeIfAbsent(quantity.getPartId(), key -> new ArrayList<>());
            quantityList.add(quantity);
            val key = quantity.getPart().getId();
            val part = quantity.getPart();
            partMap.put(key, part);
            quantity.setPart(null);
        }
        val res = new ArrayList<PartVo>(partMap.size());
        for (final Map.Entry<Integer, Part> entry : partMap.entrySet()) {
            val vo = PartVo.fromPart(entry.getValue());
            val partQuantities = partQuantitiesMap.get(entry.getKey());
            final List<PartQuantity> realQuantities =
                    partQuantities == null ? Collections.emptyList() : partQuantities.stream()
                            .filter(q -> Objects.nonNull(q.getId()))
                            .collect(Collectors.toList());
            vo.setQuantities(realQuantities);
            res.add(vo);
        }
        return res;
    }

    @Override
    public List<PartQuantity> outOfStockQuntityList() {
        return partQuantityMapper.selectFetchAllByQuantityLessThanMinQuantity();
    }

    @Override
    public PartQuantity findByPartAndStorageLocation(Integer partId, Integer storageLocationId) {
        return partQuantityMapper.selectByPartAndStorageLocation(partId, storageLocationId);
    }

    @Override
    public List<PartQuantity> batchOutbound(List<QuantityBatchUpdateItem> items, Integer storageLocationId) {
        final int rows = partQuantityMapper.batchCountdownQuantities(items, storageLocationId);
        if (rows != items.size()) {
            log.warn("BatchUpdate(Countdown) Quantities WARN: paramsSize=[{}], updateRes=[{}]", items.size(), rows);
        }
        final List<Integer> partIds = items.stream()
                .map(QuantityBatchUpdateItem::getPartId)
                .collect(Collectors.toList());

        return partQuantityMapper.selectByPartIdsAndStorageLocation(partIds, storageLocationId);
    }
}
