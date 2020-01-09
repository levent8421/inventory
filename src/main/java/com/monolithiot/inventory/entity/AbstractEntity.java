package com.monolithiot.inventory.entity;

import com.monolithiot.inventory.context.Constants;
import com.monolithiot.inventory.commons.util.DateTimeUtils;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


/**
 * Create By Levent8421
 * Create Time: 2020/1/8 15:22
 * Class Name: AbstractEntity
 * Author: Levent8421
 * Description:
 * Abstract Entity Class
 *
 * @author Levent*421
 */
@Data
public abstract class AbstractEntity implements Serializable {
    /**
     * Id
     */
    @Id
    @Column(name = "id", length = 10, nullable = false)
    @GeneratedValue(generator = Constants.MyBatis.GENERATOR_JDBC)
    private Integer id;
    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private Date createTime;
    /**
     * 更新时间
     */
    @Column(name = "update_time", nullable = false)
    private Date updateTime;
    /**
     * 删除标记（逻辑删除）
     */
    @Column(name = "deleted", nullable = false)
    private Boolean deleted;

    /**
     * 初始化BaseEntity中的属性
     */
    public void init() {
        updateTime = createTime = DateTimeUtils.now();
        deleted = false;
    }

    /**
     * 刷新更新时间
     */
    public void touch() {
        updateTime = DateTimeUtils.now();
    }
}
