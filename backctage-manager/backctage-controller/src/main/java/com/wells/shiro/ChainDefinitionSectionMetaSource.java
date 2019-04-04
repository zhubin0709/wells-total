package com.wells.shiro;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.wells.service.backctage.custom.ResourceService;
import org.apache.shiro.config.Ini;
import org.springframework.beans.factory.FactoryBean;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by zb on 2019/2/21
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

    private final Logger log =  LoggerFactory.getLogger(ChainDefinitionSectionMetaSource.class);
    /**
     * 默认url过滤定义（shiro过滤器的filterChainDefinitions属性）
     */
    private String filterChainDefinitions;
    @Reference
    private ResourceService resourceService;
    /**
     * 设置默认url过滤定义
     *
     * @param filterChainDefinitions
     */
    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }
    @Override
    public Ini.Section getObject() throws Exception {
        /*******************************************
         * rest：例子/admins/user/**=rest[user]，根据请求的方法，相当于/admins/user/**=perms[user:method]
         * ,其中method为post，get，delete等。
         * port：例子/admins/user/**=port[8081]，当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString，
         * 其中schmal是协议http或https等，serverName是你访问的host，8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
         * perms：例子/admins/user/**=perms[user:add:*]，perms参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，
         * 例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
         * roles：例子/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，
         * 例如/admins/user/**=roles["admin,guest"]，每个参数通过才算通过，相当于hasAllRoles()方法。
         * anon：例子/admins/**=anon 没有参数，表示可以匿名使用。
         * authc：例如/admins/user/**=authc表示需要认证才能使用，没有参数。
         * authcBasic：例如/admins/user/**=authcBasic没有参数表示httpBasic认证。
         * ssl：例子/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
         * user：例如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查。
         *******************************************/
        //jwtAuthcFilter控制器用来做身份认证，用restfulPermissionFilter做restful权限验证。
        String restPermissionString = "jwtAuthcFilter,restfulPermissionFilter[{0}]";

        // 加载默认的url过滤定义
        Ini ini = new Ini();
        ini.load(this.filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        List<String> dtoList = this.resourceService.listResource();
        // 将自定义url过滤添加到section中
        for (String url : dtoList) {
            if(url.startsWith("/")) {
                String code=url.substring(url.indexOf("/")+1);
                if(!section.containsKey(code)) {
                    section.put(url+"/*", MessageFormat.format(restPermissionString, code));
                }
            }
        }
        System.out.println("=========================================");
        section.put("/**", "jwtAuthcFilter");
        return section;
    }

    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
