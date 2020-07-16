package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.PartInoutHistory;
import com.monolithiot.inventory.repository.mapper.PartInoutHistoryMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.PartInoutHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/7/16 13:42
 * Class Name: PartInoutHistoryServiceImpl
 * Author: Levent8421
 * Description:
 * 物料出入库历史业务行为实现
 *
 * @author Levent8421
 */
@Slf4j
@Service
public class PartInoutHistoryServiceImpl extends AbstractServiceImpl<PartInoutHistory> implements PartInoutHistoryService {
    private final PartInoutHistoryMapper partInoutHistoryMapper;

    public PartInoutHistoryServiceImpl(PartInoutHistoryMapper partInoutHistoryMapper) {
        super(partInoutHistoryMapper);
        this.partInoutHistoryMapper = partInoutHistoryMapper;
    }

    @Override
    public List<PartInoutHistory> findByDateRangeFetchAll(Date start, Date end) {
        return partInoutHistoryMapper.selectFetchAllByDateRange(start, end);
    }
}
