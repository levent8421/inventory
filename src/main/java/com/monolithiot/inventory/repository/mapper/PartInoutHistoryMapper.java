package com.monolithiot.inventory.repository.mapper;

import com.monolithiot.inventory.commons.entity.PartInoutHistory;
import com.monolithiot.inventory.repository.AbstractMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/7/16 11:35
 * Class Name: PartInoutHistoryMapper
 * Author: Levent8421
 * Description:
 * 物料出入库历史 Mybatis Mapper
 *
 * @author Levent8421
 */
@Repository
public interface PartInoutHistoryMapper extends AbstractMapper<PartInoutHistory> {
    /**
     * Select history by date range fetch all inner objects
     *
     * @param start date start
     * @param end   date end
     * @return History List
     */
    List<PartInoutHistory> selectFetchAllByDateRange(@Param("start") Date start,
                                                     @Param("end") Date end);
}
