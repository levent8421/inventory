package com.monolithiot.inventory.security;

import lombok.Data;
import lombok.val;

/**
 * Create By Levent8421
 * Create Time: 2020/1/10 17:20
 * Class Name: TokenJson
 * Author: Levent8421
 * Description:
 * AccessToken JSON
 *
 * @author Levent*421
 */
@Data
public class TokenJson {
    public static TokenJson of(Integer userId) {
        val json = new TokenJson();
        json.setUserId(userId);
        return json;
    }

    private Integer userId;
}
