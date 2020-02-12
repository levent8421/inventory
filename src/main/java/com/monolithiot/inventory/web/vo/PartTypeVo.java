package com.monolithiot.inventory.web.vo;

import com.monolithiot.inventory.commons.entity.PartCategory;
import com.monolithiot.inventory.commons.entity.PartType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/12 10:54
 * Class Name: PartTypeVo
 * Author: Levent8421
 * Description:
 * PartType Value Object
 *
 * @author Levent8421
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PartTypeVo extends PartType {
    public PartTypeVo(PartType partType) {
        BeanUtils.copyProperties(partType, this);
    }

    /**
     * 类别列表
     */
    private List<PartCategory> partCategories;
}
