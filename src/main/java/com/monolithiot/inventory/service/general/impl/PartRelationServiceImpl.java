package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.PartRelation;
import com.monolithiot.inventory.repository.mapper.PartRelationMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.PartRelationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/12/22 10:54
 * Class Name: PartRelationServiceImpl
 * Author: Levent8421
 * Description:
 * Part Relation Service implementation
 *
 * @author Levent8421
 */
@Service
public class PartRelationServiceImpl extends AbstractServiceImpl<PartRelation> implements PartRelationService {
    private final PartRelationMapper partRelationMapper;

    public PartRelationServiceImpl(PartRelationMapper partRelationMapper) {
        super(partRelationMapper);
        this.partRelationMapper = partRelationMapper;
    }

    @Override
    public List<PartRelation> findByPrimaryPart(Integer partId) {
        return partRelationMapper.selectByPart(partId);
    }
}
