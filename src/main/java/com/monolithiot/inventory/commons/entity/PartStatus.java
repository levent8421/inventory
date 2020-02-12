package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/2/12 21:50
 * Class Name: PartStatus
 * Author: Levent8421
 * Description:
 * 物料状态数据字典实体类
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_part_status")
public class PartStatus extends AbstractEntity {
    /**
     * 状态名称
     */
    @Column(name = "name", length = 32, nullable = false)
    private String name;
    /**
     * 状态描述
     */
    @Column(name = "description", length = 128)
    private String description;
}
