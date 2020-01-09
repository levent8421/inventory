package com.monolithiot.inventory.service.vo;

import com.monolithiot.inventory.commons.util.UserPermissionUtils;
import com.monolithiot.inventory.entity.User;
import lombok.Data;
import lombok.val;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Create By Levent8421
 * Create Time: 2020/1/8 15:36
 * Class Name: UserPermission
 * Author: Levent8421
 * Description:
 * 用户权限
 *
 * @author Levent*421
 */
@Data
public class UserPermission {
    public static UserPermission fromUser(User user) {
        if (user == null) {
            return null;
        }
        val res = fromString(user.getPermission());
        res.setUser(user);
        return res;
    }

    public static UserPermission fromString(String userPermissions) {
        val permissionsMap = UserPermissionUtils.readPermission(userPermissions);
        return fromMap(permissionsMap);
    }

    public static UserPermission fromMap(Map<String, List<String>> permissionsMap) {
        return new UserPermission(permissionsMap);
    }

    private Map<String, List<String>> permissionsMap;
    private User user;

    public UserPermission(Map<String, List<String>> permissionsMap) {
        this.permissionsMap = permissionsMap;
    }

    /**
     * 检查用户是否拥有权限
     *
     * @param name       模块
     * @param permission 权限名称
     * @return boolean value
     */
    public boolean hasPermission(String name, String permission) {
        return hasPermission(name, Collections.singletonList(permission));
    }

    /**
     * 检查用户是否拥有权限
     *
     * @param name        模块
     * @param permissions 权限名称列表
     * @return boolean value
     */
    public boolean hasPermission(String name, Collection<String> permissions) {
        if (permissionsMap == null || !permissionsMap.containsKey(name)) {
            return false;
        }
        val permissionList = permissionsMap.get(name);
        return permissionList.containsAll(permissions);
    }
}
