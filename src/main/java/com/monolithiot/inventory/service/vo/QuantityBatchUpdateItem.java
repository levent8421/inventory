package com.monolithiot.inventory.service.vo;

import lombok.Data;

/**
 * Create By Levent8421
 * Create Time: 2020/12/22 13:44
 * Class Name: QuantityBatchUpdateItem
 * Author: Levent8421
 * Description:
 * 库存批量更新项目
 *
 * @author Levent8421
 */
@Data
public class QuantityBatchUpdateItem {
    private Integer partId;
    private Double count;
}
