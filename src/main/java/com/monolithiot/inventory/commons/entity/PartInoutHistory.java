package com.monolithiot.inventory.commons.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * Create By Levent8421
 * Create Time: 2020/7/16 10:06
 * Class Name: PartInoutHistory
 * Author: Levent8421
 * Description:
 * 物料出入库记录
 *
 * @author Levent8421
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "t_part_inout_history")
public class PartInoutHistory extends AbstractEntity {
    @Column(name = "user_id", nullable = false, length = 10)
    private Integer userId;
    /**
     * association user
     */
    private User user;

    @Column(name = "part_id", nullable = false, length = 10)
    private Integer partId;
    /**
     * association part
     */
    private Part part;

    @Column(name = "part_inout_action_id", nullable = false, length = 10)
    private Integer partInoutActionId;

    /**
     * association part inout action
     */
    private PartInoutAction partInoutAction;

    @Column(name = "storage_location_id", nullable = false, length = 10)
    private Integer storageLocationId;

    /**
     * association part storage location
     */
    private StorageLocation storageLocation;
    @Column(name = "binNo", length = 32)
    private String binNo;

    @Column(name = "quantity", nullable = false, length = 10)
    private Integer quantity;

    @Column(name = "quantity_before", length = 10)
    private Integer quantityBefore;

    @Column(name = "quantity_after", length = 10)
    private Integer quantityAfter;

    @Column(name = "time")
    private Date time;

    @Column(name = "remark")
    private String remark;

    @Column(name = "traceNo")
    private String traceNo;

    /* ---- extra ---- */
    /**
     * Part Supply
     * 物料供应关系
     */
    private PartSupply partSupply;
}
