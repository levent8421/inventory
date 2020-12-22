package com.monolithiot.inventory.service.general;

import com.monolithiot.inventory.commons.entity.PartRelation;
import com.monolithiot.inventory.service.commons.AbstractService;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/12/22 10:53
 * Class Name: PartRelationService
 * Author: Levent8421
 * Description:
 * Part relation service
 *
 * @author Levent8421
 */
public interface PartRelationService extends AbstractService<PartRelation> {
    /**
     * Find relation by primary part id
     *
     * @param partId part id
     * @return child part relations
     */
    List<PartRelation> findByPrimaryPart(Integer partId);
}
