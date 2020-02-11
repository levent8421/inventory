package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/2/11 23:23
 * Class Name: PartType
 * Author: Levent8421
 * Description:
 * 库存类型（树形结构）
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_part_type")
public class PartType extends AbstractEntity {
    /**
     * 类型名称
     */
    @Column(name = "name", length = 32, nullable = false)
    private String name;
    /**
     * 分类描述
     */
    @Column(name = "description", length = 128)
    private String description;
}
