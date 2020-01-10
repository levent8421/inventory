package com.monolithiot.inventory.security.util;

import com.alibaba.fastjson.JSON;
import com.monolithiot.inventory.commons.entity.User;
import com.monolithiot.inventory.security.TokenJson;
import com.monolithiot.inventory.security.encrypt.AccessTokenEncoder;
import lombok.val;

/**
 * Create By Levent8421
 * Create Time: 2020/1/10 17:41
 * Class Name: AccessTokenUtils
 * Author: Levent8421
 * Description:
 * 令牌相关工具类
 *
 * @author Levent*421
 */
public class AccessTokenUtils {
    /**
     * Token String from user
     *
     * @param accessTokenEncoder encoder
     * @param user               user
     * @return token string
     */
    public static String asTokenString(User user, AccessTokenEncoder accessTokenEncoder) {
        val json = TokenJson.of(user.getId());
        return accessTokenEncoder.encode(JSON.toJSONString(json));
    }
}

