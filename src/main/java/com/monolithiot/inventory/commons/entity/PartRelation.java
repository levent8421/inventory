package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/12/21 20:27
 * Class Name: PartRelation
 * Author: Levent8421
 * Description:
 * 物料关系
 *
 * @author Levent8421
 */
@Table(name = "t_part_relation")
@EqualsAndHashCode(callSuper = true)
@Data
public class PartRelation extends AbstractEntity {
    /**
     * 主物料ID
     */
    @Column(name = "part_id", length = 10, nullable = false)
    private Integer partId;
    /**
     * 子物料ID
     */
    @Column(name = "part_id_child", length = 10, nullable = false)
    private Integer partIdChild;
    /**
     * 子物料对象
     */
    private Part childPart;
    /**
     * 子物料数量
     */
    @Column(name = "quantity", length = 10)
    private Double quantity;
    /**
     * 损耗
     */
    @Column(name = "loss", length = 10)
    private Integer loss;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
}
