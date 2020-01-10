package com.monolithiot.inventory.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * Create By Levent8421
 * Create Time: 2020/1/10 15:44
 * Class Name: StatelessDefaultSubjectFactory
 * Author: Levent8421
 * Description:
 * 无状态Subject 工厂
 *
 * @author Levent*421
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        //不创建session
        context.setSessionCreationEnabled(false);
        return super.createSubject(context);
    }
}
