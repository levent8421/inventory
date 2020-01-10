package com.monolithiot.inventory.web.vo;

import com.monolithiot.inventory.commons.entity.User;
import com.monolithiot.inventory.service.vo.UserPermission;
import lombok.Data;
import lombok.val;

/**
 * Create By Levent8421
 * Create Time: 2020/1/10 15:01
 * Class Name: LoginResult
 * Author: Levent8421
 * Description:
 * 用户令牌
 *
 * @author Levent*421
 */
@Data
public class LoginResult {
    public static LoginResult fromUser(User user) {
        val permission = UserPermission.fromUser(user);
        val loginResult = new LoginResult();
        loginResult.setUsername(user.getName());
        loginResult.setUserPermission(permission);
        return loginResult;
    }

    private UserPermission userPermission;
    private String username;
    private String token;
}
