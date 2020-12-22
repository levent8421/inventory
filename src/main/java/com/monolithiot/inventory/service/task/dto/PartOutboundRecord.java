package com.monolithiot.inventory.service.task.dto;

import com.monolithiot.inventory.commons.entity.Part;
import lombok.Data;

/**
 * Create By Levent8421
 * Create Time: 2020/12/21 19:32
 * Class Name: PartOutboundRecord
 * Author: Levent8421
 * Description:
 * 物料出入库记录
 *
 * @author Levent8421
 */
@Data
public class PartOutboundRecord {
    /**
     * 物料
     */
    private Part part;
    /**
     * 库位ID
     */
    private Integer storageLocationId;
    /**
     * 出库数量
     */
    private double count;
}
