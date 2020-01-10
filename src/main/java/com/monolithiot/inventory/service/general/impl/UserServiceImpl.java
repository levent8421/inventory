package com.monolithiot.inventory.service.general.impl;

import com.monolithiot.inventory.commons.entity.User;
import com.monolithiot.inventory.repository.mapper.UserMapper;
import com.monolithiot.inventory.security.encrypt.PasswordEncryptor;
import com.monolithiot.inventory.service.commons.impl.AbstractServiceImpl;
import com.monolithiot.inventory.service.general.UserService;
import lombok.val;
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
    private final PasswordEncryptor passwordEncryptor;

    public UserServiceImpl(UserMapper userMapper, PasswordEncryptor passwordEncryptor) {
        super(userMapper);
        this.userMapper = userMapper;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public User findByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public User login(String username, String password) {
        val user = findByName(username);
        if (user == null) {
            return null;
        }
        if (passwordEncryptor.matches(user.getPassword(), password)) {
            return user;
        }
        return null;
    }
}
