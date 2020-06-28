package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 19:24
 * Class Name: PartQuantity
 * Author: Levent8421
 * Description:
 * 物料数量实体类
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_part_quantity")
public class PartQuantity extends AbstractEntity {
    /**
     * 物料ID
     */
    @Column(name = "part_id", length = 10, nullable = false)
    private Integer partId;
    /**
     * 物料对象
     */
    private Part part;
    /**
     * 库位ID
     */
    @Column(name = "storage_location_id", length = 10, nullable = false)
    private Integer storageLocationId;
    /**
     * 关联的库位对象
     */
    private StorageLocation storageLocation;
    /**
     * 料盒号
     */
    @Column(name = "binNo", length = 32)
    private String binNo;
    /**
     * 数量
     */
    @Column(name = "quantity", length = 10, nullable = false)
    private Double quantity;
}
