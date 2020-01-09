package com.monolithiot.inventory.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Create By Levent8421
 * Create Time: 2020/1/8 15:25
 * Class Name: User
 * Author: Levent8421
 * Description:
 * 用户数据实体类
 *
 * @author Levent*421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_user")
public class User extends AbstractEntity {
    /**
     * 用户名
     */
    @Column(name = "name", length = 128, nullable = false)
    private String name;
    /**
     * 密码
     */
    @Column(name = "password", length = 128, nullable = false)
    private String password;
    /**
     * 权限
     */
    @Column(name = "permission", length = 128, nullable = false)
    private String permission;
}
