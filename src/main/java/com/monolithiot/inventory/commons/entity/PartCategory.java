package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;


/**
 * Create By Levent8421
 * Create Time: 2020/2/11 23:18
 * Class Name: PartCategory
 * Author: Levent8421
 * Description:
 * 库存类别实体类
 *
 * @author Levent*421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_part_category")
public class PartCategory extends AbstractEntity {
    /**
     * 类型ID
     */
    @Column(name = "part_type_id", length = 10, nullable = false)
    private Integer partTypeId;
    /**
     * 类别名称
     */
    @Column(name = "name", length = 32)
    private String name;
    /**
     * 类别描述
     */
    @Column(name = "description", length = 128)
    private String description;
    /**
     * 物料号前缀
     */
    @Column(name = "partNoPrefix", length = 32)
    private String partNoPrefix;
    /**
     * 物料号长度
     */
    @Column(name = "partNoLength", length = 10, nullable = false)
    private Integer partNoLength;
}
