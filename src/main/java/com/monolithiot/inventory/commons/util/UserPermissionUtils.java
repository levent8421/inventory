package com.monolithiot.inventory.commons.util;

import com.monolithiot.inventory.commons.vo.Tuple2;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Create By Levent8421
 * Create Time: 2020/1/8 17:32
 * Class Name: UserPermissionUtils
 * Author: Levent8421
 * Description:
 * 用户权限相关工具类
 *
 * @author Levent*421
 */
@Slf4j
public class UserPermissionUtils {
    private static final String PERMISSION_DELIMITER = ";";
    private static final String PERMISSION_START = ":";
    private static final String PERMISSION_ITEM_DELIMITER = ",";

    /**
     * Read Permissions as Java Map
     *
     * @param permissionsString permissions String from database
     * @return permissions map
     */
    public static Map<String, List<String>> readPermission(String permissionsString) {
        if (TextUtils.isBlank(permissionsString)) {
            return Collections.emptyMap();
        }
        return Arrays.stream(permissionsString.split(PERMISSION_DELIMITER))
                .map(item -> {
                    val pair = item.split(PERMISSION_START);
                    if (pair.length != 2) {
                        log.warn("Error On Resolve PermissionString [{}], item=[{}]", permissionsString, item);
                        return null;
                    }
                    val name = pair[0];
                    val permissions = pair[1];
                    return Tuple2.of(name, permissions);
                })
                .filter(Objects::nonNull)
                .map(pair -> {
                    if (TextUtils.isBlank(pair.getO1()) || TextUtils.isBlank(pair.getO2())) {
                        log.warn("Error On Resolve PermissionPair,name=[{}], permissions=[{}]", pair.getO1(), pair.getO2());
                        return null;
                    }
                    val permissions = Arrays.asList(pair.getO2().split(PERMISSION_ITEM_DELIMITER));
                    return Tuple2.of(pair.getO1(), permissions);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(Tuple2::getO1, Tuple2::getO2));
    }
}
