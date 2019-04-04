package com.wells.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * Created by zb on 2019/2/20
 */
public class JwtDefaultSubjectFactory extends DefaultWebSubjectFactory {
    @Override
    public Subject createSubject(SubjectContext context) {
        // 不创建session
        context.setSessionCreationEnabled(false);
        Subject subject = super.createSubject(context);
        return subject;
    }
}
