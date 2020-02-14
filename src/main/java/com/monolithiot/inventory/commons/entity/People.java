package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/2/14 17:14
 * Class Name: People
 * Author: Levent8421
 * Description:
 * 联系人实体类
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_people")
public class People extends AbstractEntity {
    /**
     * 姓名
     */
    @Column(name = "name", length = 32)
    private String name;
    /**
     * 电话1
     */
    @Column(name = "phone1", length = 32)
    private String phone1;
    /**
     * 电话2
     */
    @Column(name = "phone2", length = 32)
    private String phone2;
    /**
     * QQ
     */
    @Column(name = "qq", length = 32)
    private String qq;
    /**
     * Email
     */
    @Column(name = "email", length = 64)
    private String email;
    /**
     * 公司
     */
    @Column(name = "company")
    private String company;
    /**
     * Title
     */
    @Column(name = "title")
    private String title;
    /**
     * 地址
     */
    @Column(name = "address")
    private String address;
    /**
     * 邮编
     */
    @Column(name = "postalCode", length = 32)
    private String postalCode;
    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;
}
