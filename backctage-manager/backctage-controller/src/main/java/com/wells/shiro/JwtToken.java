package com.wells.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by zb on 2019/2/20
 */
public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = 9028340303836653244L;
    private String jwt;

    private String host;

    public JwtToken(String jwt, String host) {
        this.jwt = jwt;
        this.host = host;
    }

    @Override
    public Object getPrincipal() {
        return this.jwt;
    }

    @Override
    public Object getCredentials() {
        return Boolean.TRUE;
    }
    public String getHost() {
        return this.host;
    }
}
