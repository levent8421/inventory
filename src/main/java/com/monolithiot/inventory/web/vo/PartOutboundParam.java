package com.monolithiot.inventory.web.vo;

import lombok.Data;

/**
 * Create By Levent8421
 * Create Time: 2020/12/22 11:03
 * Class Name: PartOutboundParam
 * Author: Levent8421
 * Description:
 * 物料出库参数
 * 对哪个物料从哪个库出库多少数量
 *
 * @author Levent8421
 */
@Data
public class PartOutboundParam {
    private Integer partId;
    private Double count;
    private Integer storageLocationId;
}
