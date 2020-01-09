package com.monolithiot.inventory.web.controller.open;

import com.monolithiot.inventory.entity.User;
import com.monolithiot.inventory.service.general.UserService;
import com.monolithiot.inventory.service.vo.UserPermission;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/1/8 15:50
 * Class Name: OpenUserController
 * Author: Levent8421
 * Description:
 * 开放访问权限的用户控制器
 *
 * @author Levent*421
 */
@RestController
@RequestMapping("/open/user")
public class OpenUserController extends AbstractController {
    private final UserService userService;

    public OpenUserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Find  All Users As A List
     *
     * @return GR
     */
    @GetMapping("/")
    public GeneralResult<List<User>> all() {
        val users = userService.all();
        return GeneralResult.ok(users);
    }

    /**
     * 用户权限信息
     *
     * @param id ID
     * @return GR
     */
    @GetMapping("/{id}/_permissions")
    public GeneralResult<UserPermission> userPermissions(@PathVariable("id") Integer id) {
        val user = userService.require(id);
        val permission = UserPermission.fromUser(user);
        return GeneralResult.ok(permission);
    }
}
