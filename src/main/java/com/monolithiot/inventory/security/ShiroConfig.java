package com.monolithiot.inventory.security;

import com.monolithiot.inventory.security.encrypt.AccessTokenEncoder;
import com.monolithiot.inventory.service.general.UserService;
import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;

/**
 * Create By Levent8421
 * Create Time: 2020/1/9 17:24
 * Class Name: ShiroConfig
 * Author: Levent8421
 * Description:
 * Shiro 相关配置
 *
 * @author Levent*421
 */
@Configuration
public class ShiroConfig {
    /**
     * Realm
     *
     * @param userService        user service
     * @param accessTokenEncoder AccessTokenEncoder
     * @return ShiroAuthorizingRealm
     */
    @Bean
    public ShiroAuthorizingRealm shiroAuthorizingRealm(UserService userService,
                                                       AccessTokenEncoder accessTokenEncoder) {
        return new ShiroAuthorizingRealm(userService, accessTokenEncoder);
    }

    /**
     * Subject Factory
     *
     * @return StatelessDefaultSubjectFactory
     */
    @Bean
    public StatelessDefaultSubjectFactory subjectFactory() {
        return new StatelessDefaultSubjectFactory();
    }

    /**
     * Session Manager
     *
     * @return DefaultSessionManager
     */
    @Bean
    public DefaultSessionManager sessionManager() {
        val manager = new DefaultSessionManager();
        manager.setSessionValidationSchedulerEnabled(false);
        return manager;
    }

    @Bean
    public org.apache.shiro.mgt.SecurityManager securityManager(ShiroAuthorizingRealm shiroAuthorizingRealm,
                                                                SubjectFactory subjectFactory,
                                                                SessionManager sessionManager) {
        val manager = new DefaultWebSecurityManager();
        manager.setRealm(shiroAuthorizingRealm);
        val subjectDao = manager.getSubjectDAO();
        if (subjectDao instanceof DefaultSubjectDAO) {
            val sessionStorageEvaluator = ((DefaultSubjectDAO) subjectDao).getSessionStorageEvaluator();
            if (sessionStorageEvaluator instanceof DefaultSessionStorageEvaluator) {
                ((DefaultSessionStorageEvaluator) sessionStorageEvaluator).setSessionStorageEnabled(false);
            }
        }
        manager.setSubjectFactory(subjectFactory);
        manager.setSessionManager(sessionManager);
        SecurityUtils.setSecurityManager(manager);
        return manager;
    }

    /**
     * StatelessAuthcFilter
     *
     * @param accessTokenEncoder AccessTokenEncoder
     * @return StatelessAuthcFilter
     */
    @Bean
    public StatelessAuthcFilter statelessAuthcFilter(AccessTokenEncoder accessTokenEncoder) {
        return new StatelessAuthcFilter(accessTokenEncoder);
    }

    /**
     * ShiroFilterFactoryBean
     *
     * @param securityManager      SecurityManager
     * @param statelessAuthcFilter StatelessAuthcFilter
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager,
                                                         StatelessAuthcFilter statelessAuthcFilter) {
        val shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        val filterMap = new HashMap<String, Filter>(16);
        filterMap.put("statelessFilter", statelessAuthcFilter);
        shiroFilterFactoryBean.setFilters(filterMap);

        val filterChainDefinitions = new HashMap<String, String>(16);
        filterChainDefinitions.put("/api/**", "statelessFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitions);
        return shiroFilterFactoryBean;
    }
}
