package com.monolithiot.inventory.web.controller.api;

import com.monolithiot.inventory.commons.entity.User;
import com.monolithiot.inventory.service.vo.UserPermission;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By Levent8421
 * Create Time: 2020/1/9 17:35
 * Class Name: ApiUserController
 * Author: Levent8421
 * Description:
 * 用户相关API数据访问控制器
 *
 * @author Levent*421
 */
@RestController
@RequestMapping("/api/user")
public class ApiUserController extends AbstractController {

    /**
     * 获取当前登录的用户
     *
     * @return GR
     */
    @GetMapping("/_me")
    public GeneralResult<User> getCurrentUser() {
        return GeneralResult.ok(requireCurrentUser());
    }

    /**
     * 当前用户的权限星系
     *
     * @return GR with permission Object
     */
    @GetMapping("/_permission")
    public GeneralResult<UserPermission> currentUserPermission() {
        val user = requireCurrentUser();
        val permission = UserPermission.fromUser(user);
        return GeneralResult.ok(permission);
    }
}
