package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/2/14 16:30
 * Class Name: Supplier
 * Author: Levent8421
 * Description:
 * 供应商实体类
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_supplier")
public class Supplier extends AbstractEntity {
    /**
     * 名称
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * 地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 网址
     */
    @Column(name = "url")
    private String url;
    /**
     * 联系人1
     */
    @Column(name = "contactor1")
    private String contactor1;
    /**
     * 联系人2
     */
    @Column(name = "contactor2")
    private String contactor2;
    /**
     * 联系人1的电话
     */
    @Column(name = "phone1")
    private String phone1;
    /**
     * 联系人2的电话
     */
    @Column(name = "phone2")
    private String phone2;
    /**
     * 联系人1的其他联系方式
     */
    @Column(name = "other1")
    private String other1;
    /**
     * 联系人2的其他联系方式
     */
    @Column(name = "other2")
    private String other2;
    /**
     * 传真
     */
    @Column(name = "fax")
    private String fax;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
}
