package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.People;
import com.monolithiot.inventory.repository.mapper.PeopleMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.PeopleService;
import org.springframework.stereotype.Service;

/**
 * Create By Levent8421
 * Create Time: 2020/2/14 18:26
 * Class Name: PeopleServiceImpl
 * Author: Levent8421
 * Description:
 * 联系人相关业务行为实现
 *
 * @author Levent8421
 */
@Service
public class PeopleServiceImpl extends AbstractServiceImpl<People> implements PeopleService {
    private final PeopleMapper peopleMapper;

    public PeopleServiceImpl(PeopleMapper peopleMapper) {
        super(peopleMapper);
        this.peopleMapper = peopleMapper;
    }
}
