package com.wells.service.impl.system.custom;

import com.alibaba.dubbo.config.annotation.Service;
import com.wells.mapper.custom.ResourceMapper;
import com.wells.pojo.SysMenu;
import com.wells.service.backctage.custom.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zb on 2019/2/22
 */
@Service
@Component
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Override
    public List<String> listResource() {return resourceMapper.listResource(); }
    @Override
    public List<String> listPermissionCodeByUserId(int userId){return resourceMapper.listPermissionCodeByUserId(userId);}
    @Override
    public List<SysMenu>listAuthorizedRouter(int userId){return resourceMapper.listAuthorizedRouter(userId);}
}
