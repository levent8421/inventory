package com.monolithiot.inventory.security;

import com.monolithiot.inventory.commons.context.Constants;
import com.monolithiot.inventory.security.encrypt.AccessTokenEncoder;
import com.monolithiot.inventory.security.util.AccessTokenUtils;
import com.monolithiot.inventory.service.general.UserService;
import com.monolithiot.inventory.service.vo.UserPermission;
import lombok.val;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create By Levent8421
 * Create Time: 2020/1/9 16:33
 * Class Name: ShiroAuthorizingRealm
 * Author: Levent8421
 * Description:
 * Shairo 授权配置
 *
 * @author Levent*421
 */
public class ShiroAuthorizingRealm extends AuthorizingRealm {
    private static final String ROLE_PERMISSION_TEMPLATE = "%s:%s";
    private final UserService userService;
    private final AccessTokenEncoder accessTokenEncoder;

    public ShiroAuthorizingRealm(UserService userService,
                                 AccessTokenEncoder accessTokenEncoder) {
        this.accessTokenEncoder = accessTokenEncoder;
        this.userService = userService;
        setAuthenticationTokenClass(StatelessToken.class);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        val username = (String) principalCollection.getPrimaryPrincipal();
        val user = userService.findByName(username);
        val permissions = UserPermission.fromUser(user);
        if (permissions == null) {
            throw new UnknownAccountException("Could not find user " + username);
        }
        val authorizationInfo = new SimpleAuthorizationInfo();
        for (Map.Entry<String, List<String>> permissionItem : permissions.getPermissionsMap().entrySet()) {
            val role = permissionItem.getKey();
            authorizationInfo.addRole(role);
            val rolePermissions = permissionItem.getValue().stream()
                    .map(p -> String.format(ROLE_PERMISSION_TEMPLATE, role, p))
                    .collect(Collectors.toList());
            authorizationInfo.addStringPermissions(rolePermissions);
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        val username = (String) authenticationToken.getPrincipal();
        val user = userService.findByName(username);
        if (user == null) {
            throw new UnknownAccountException("Could not find user [" + username + "]！");
        }
        RequestExtendParamHolder.set(Constants.Security.USER_EXTEND_PARAM_NAME, user);
        return new SimpleAuthenticationInfo(user.getName(), AccessTokenUtils.asTokenString(user, accessTokenEncoder), user.getName());
    }
}
