package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/7/16 14:45
 * Class Name: PartSupply
 * Author: Levent8421
 * Description:
 * 物料-供应商关系
 *
 * @author Levent8421
 */
@Table(name = "t_part_supply")
@EqualsAndHashCode(callSuper = true)
@Data
public class PartSupply extends AbstractEntity {
    @Column(name = "part_id", length = 10, nullable = false)
    private Integer partId;

    @Column(name = "supplier_id", length = 10, nullable = false)
    private Integer supplierId;

    @Column(name = "moq", length = 10, nullable = false)
    private Integer moq;

    @Column(name = "deliveryDays", length = 10, nullable = false)
    private Integer deliveryDays;

    @Column(name = "unitPrice", nullable = false)
    private Double unitPrice;

    @Column(name = "unitTaxPrice", nullable = false)
    private Double unitTaxPrice;

    @Column(name = "isDefault", length = 10, nullable = false)
    private Integer isDefault;

    @Column(name = "url")
    private String url;

    @Column(name = "remark")
    private String remark;
}
