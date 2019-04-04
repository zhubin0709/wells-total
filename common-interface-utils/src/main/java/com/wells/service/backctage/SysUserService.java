package com.wells.service.backctage;

import com.wells.pojo.SysUser;

/**
 * Created by zb on 2019/1/26
 */
public interface SysUserService {
    /**
     * 通过登录名查找用户信息
     *
     * @param loginName
     * @return
     */
    SysUser selectUserByLoginName(String loginName);
    /**
     * 添加系统用户
     *
     * @param user
     *            User类的实例
     * @return
     */
    SysUser saveUser(SysUser user);
    /**
     * 根据Id查询用户
     * @param id 用户id
     * @return
     */
    SysUser selectUserByUserId(int id);
    /**
     * 根据Id修改用户信息
     * @param id 用户id
     * @return
     */
    int updateUserbyUserId(int id,String ip);
}
