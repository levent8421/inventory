package com.monolithiot.inventory.web.controller.open;

import com.monolithiot.inventory.commons.entity.User;
import com.monolithiot.inventory.commons.exception.BadRequestException;
import com.monolithiot.inventory.commons.util.ParamChecker;
import com.monolithiot.inventory.security.StatelessToken;
import com.monolithiot.inventory.security.encrypt.AccessTokenEncoder;
import com.monolithiot.inventory.security.util.AccessTokenUtils;
import com.monolithiot.inventory.service.general.UserService;
import com.monolithiot.inventory.service.vo.UserPermission;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import com.monolithiot.inventory.web.vo.LoginResult;
import com.monolithiot.inventory.web.vo.UserLoginParam;
import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

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
    private final AccessTokenEncoder accessTokenEncoder;

    public OpenUserController(UserService userService,
                              AccessTokenEncoder accessTokenEncoder) {
        this.userService = userService;
        this.accessTokenEncoder = accessTokenEncoder;
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

    /**
     * 用户登录
     *
     * @param param param
     * @return GR
     */
    @PostMapping("/_login")
    public GeneralResult<?> login(@RequestBody UserLoginParam param) {
        val ex = BadRequestException.class;
        ParamChecker.notNull(param, ex, "No Params sent!");
        ParamChecker.notEmpty(param.getUsername(), ex, "用户名必填！");
        ParamChecker.notEmpty(param.getPassword(), ex, "密码必填！");
        val user = userService.login(param.getUsername(), param.getPassword());
        if (user == null) {
            return GeneralResult.ok("用户名或密码错误！");
        }
        val digestString = AccessTokenUtils.asTokenString(user, accessTokenEncoder);

        val loginToken = new StatelessToken();
        loginToken.setUsername(user.getName());
        loginToken.setDigest(digestString);
        val subject = SecurityUtils.getSubject();
        subject.login(loginToken);

        val res = new LoginResult();
        res.setToken(loginToken.asTokenString());
        res.setUsername(user.getName());
        res.setUserPermission(UserPermission.fromUser(user));
        return GeneralResult.ok(res);
    }
}
