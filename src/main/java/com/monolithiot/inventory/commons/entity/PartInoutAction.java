package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/2/14 18:46
 * Class Name: PartInoutAction
 * Author: Levent8421
 * Description:
 * 出入库记录实体类
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_part_inout_action")
public class PartInoutAction extends AbstractEntity {
    /**
     * 名称
     */
    @Column(name = "name", length = 32, nullable = false)
    private String name;
    /**
     * 描述
     */
    @Column(name = "description", length = 128)
    private String description;
    /**
     * 库存影响
     */
    @Column(name = "direction", length = 6, nullable = false)
    private Integer direction;
}
