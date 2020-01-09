package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.User;
import com.monolithiot.inventory.repository.mapper.UserMapper;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.UserService;
import org.springframework.stereotype.Service;


/**
 * Create By Levent8421
 * Create Time: mm 15:59
 * Class Name: UserServiceImpl
 * Author: Levent8421
 * Description:
 * 用户相关业务行为实现
 *
 * @author Levent*421
 */
@Service
public class UserServiceImpl extends AbstractServiceImpl<User> implements UserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        super(userMapper);
        this.userMapper = userMapper;
    }
}
