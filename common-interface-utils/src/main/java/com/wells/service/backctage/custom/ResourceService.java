package com.wells.service.backctage.custom;

import com.wells.pojo.SysMenu;

import java.util.List;

/**
 * Created by zb on 2019/2/22
 */
public interface ResourceService {
    List<String> listResource();
    List<String> listPermissionCodeByUserId(int userId);
    List<SysMenu>listAuthorizedRouter(int userId);
}
