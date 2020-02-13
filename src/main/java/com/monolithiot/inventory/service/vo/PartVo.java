package com.monolithiot.inventory.service.vo;

import com.monolithiot.inventory.commons.entity.Part;
import com.monolithiot.inventory.commons.entity.PartQuantity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.val;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 22:27
 * Class Name: PartVo
 * Author: Levent8421
 * Description:
 * 物料 Value Object
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PartVo extends Part {
    public static PartVo fromPart(Part part) {
        val vo = new PartVo();
        BeanUtils.copyProperties(part, vo);
        return vo;
    }

    /**
     * 库存列表
     */
    private List<PartQuantity> quantities;
}
