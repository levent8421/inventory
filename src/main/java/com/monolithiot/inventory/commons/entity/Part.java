package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private Integer userId;
    private Integer partCategoryId;
    private Integer partStatusId;
    private Integer partClusterId;
    private String partNo;
    private String description;
    private String model;
}
