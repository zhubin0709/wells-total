package com.wells.service.impl.system;

import com.alibaba.dubbo.config.annotation.Service;
import com.wells.mapper.SysUserMapper;
import com.wells.pojo.SysUser;
import com.wells.pojo.SysUserCriteria;
import com.wells.service.backctage.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zb on 2019/1/26
 */
@Service
@Component
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 通过登录名查找用户信息
     *
     * @param loginName
     * @return
     */
    @Override
    public SysUser selectUserByLoginName(String loginName) {
        SysUserCriteria sysUserCriteria=new SysUserCriteria();
        SysUserCriteria.Criteria criteria=sysUserCriteria.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
       List<SysUser> sysUserList=sysUserMapper.selectByExample(sysUserCriteria);
       if(sysUserList!=null&&sysUserList.size()==1){
           return sysUserList.get(0);
       }
        return null;
    }

    /**
     * 添加系统用户
     *
     * @param user
     *            User类的实例
     * @return
     */
    @Override
    public SysUser saveUser(SysUser user) {
        return null;
    }

    /**
     * 根据Id查询用户
     * @param id 用户id
     * @return
     */
    @Override
    public SysUser selectUserByUserId(int id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
    /**
     * 根据Id修改用户信息
     * @param id 用户id
     * @return
     */
    @Override
   public int updateUserbyUserId(int id,String ip){
        SysUser user=new SysUser();
        user.setUserId(id);
        user.setLoginIp(ip);
        return sysUserMapper.updateByPrimaryKey(user);
    }
}
