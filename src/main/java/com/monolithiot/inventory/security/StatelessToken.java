package com.monolithiot.inventory.security;

import lombok.Data;
import lombok.val;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Create By Levent8421
 * Create Time: 2020/1/10 15:50
 * Class Name: StatelessToken
 * Author: Levent8421
 * Description:
 * 无状态Token
 *
 * @author Levent*421
 */
@Data
public class StatelessToken implements AuthenticationToken {
    private static final String USERNAME_AND_TOKEN_DELIMITER = ":";
    private static final int TOKEN_PAIR_LENGTH = 2;
    private String username;
    private String digest;
    private Map<String, Object> data;

    public static StatelessToken fromDigest(String digest) {
        val pair = digest.split(USERNAME_AND_TOKEN_DELIMITER);
        if (pair.length != TOKEN_PAIR_LENGTH) {
            return null;
        }
        val username = pair[0];
        val tokenString = pair[1];
        val token = new StatelessToken();
        token.setDigest(tokenString);
        token.setUsername(username);
        return token;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return digest;
    }

    private void checkDataMap() {
        if (data == null) {
            data = new HashMap<>(16);
        }
    }

    public void appendData(String name, Object value) {
        checkDataMap();
        data.put(name, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getData(String name, Class<T> clazz, T defaultValue) {
        if (data == null) {
            return null;
        }
        val value = data.getOrDefault(name, defaultValue);
        if (clazz.isInstance(value)) {
            return (T) value;
        }
        return null;
    }

    public void removeData(String name) {
        if (data == null) {
            return;
        }
        data.remove(name);
    }

    public String asTokenString() {
        return username + USERNAME_AND_TOKEN_DELIMITER + digest;
    }
}
