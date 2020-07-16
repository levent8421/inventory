package com.monolithiot.inventory.repository.mapper;

import com.monolithiot.inventory.commons.entity.PartQuantity;
import com.monolithiot.inventory.repository.AbstractMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 19:27
 * Class Name: PartQuantityMapper
 * Author: Levent8421
 * Description:
 * 物料数量相关数据库访问组件
 *
 * @author Levent8421
 */
@Repository
public interface PartQuantityMapper extends AbstractMapper<PartQuantity> {
    /**
     * Select PartQuantity By PartId
     *
     * @param partId partId
     * @return Quantity List
     */
    List<PartQuantity> selectByPartId(@Param("partId") Integer partId);

    /**
     * 搜索物料库存量
     *
     * @param partNoList 物料号列表
     * @param desc       描述
     * @param categoryId 类别ID
     * @param clusterId  库位ID
     * @return PartQuantityDto List
     */
    List<PartQuantity> search(@Param("partNoList") List<String> partNoList,
                              @Param("desc") String desc,
                              @Param("categoryId") Integer categoryId,
                              @Param("clusterId") Integer clusterId);

    /**
     * Select XXX from PartQuantity where quantity < minQuantity
     *
     * @return PartQuantity List
     */
    List<PartQuantity> selectFetchAllByQuantityLessThanMinQuantity();
}
