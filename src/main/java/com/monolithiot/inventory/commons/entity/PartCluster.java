package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/2/12 23:54
 * Class Name: PartCluster
 * Author: Levent8421
 * Description:
 * 物料大分类(类型)实体类
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_part_cluster")
public class PartCluster extends AbstractEntity {
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
}
