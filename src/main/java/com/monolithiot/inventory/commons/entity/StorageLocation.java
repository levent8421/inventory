package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 10:08
 * Class Name: StorageLocation
 * Author: Levent8421
 * Description:
 * Storage Location 库存位置实体类
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_storage_location")
public class StorageLocation extends AbstractEntity {
    /**
     * Code
     */
    @Column(name = "code", length = 32)
    private String code;
    /**
     * Location
     */
    @Column(name = "location", length = 84)
    private String location;
}
