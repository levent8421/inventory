package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/2/11 22:52
 * Class Name: Part
 * Author: Levent8421
 * Description:
 * 物料实体类
 *
 * @author Levent*421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_part")
public class Part extends AbstractEntity {
    /**
     * 创建用户ID
     */
    @Column(name = "user_id", length = 10, nullable = false)
    private Integer userId;
    /**
     * 类别ID
     */
    @Column(name = "part_category_id", length = 10, nullable = false)
    private Integer partCategoryId;
    /**
     * 关联的类别对象
     */
    private PartCategory partCategory;
    /**
     * 状态ID
     */
    @Column(name = "part_status_id", length = 10, nullable = false)
    private Integer partStatusId;
    /**
     * 物料状态对象
     */
    private PartStatus partStatus;
    /**
     * 物料库位
     */
    @Column(name = "part_cluster_id", length = 10, nullable = false)
    private Integer partClusterId;
    /**
     * 物料库位对象
     */
    private PartCluster partCluster;
    /**
     * 物料号
     */
    @Column(name = "partNo", length = 32, nullable = false)
    private String partNo;
    /**
     * 描述
     */
    @Column(name = "description", length = 128)
    private String description;
    /**
     * 型号
     */
    @Column(name = "model", length = 128)
    private String model;
    /**
     * 封装
     */
    @Column(name = "packaging", length = 128)
    private String packaging;
    /**
     * 品牌
     */
    @Column(name = "brand", length = 128)
    private String brand;
    /**
     * 每包数量
     */
    @Column(name = "packingQty", length = 128)
    private String packingQty;
    /**
     * 版本
     */
    @Column(name = "version", length = 4)
    private String version;
}
