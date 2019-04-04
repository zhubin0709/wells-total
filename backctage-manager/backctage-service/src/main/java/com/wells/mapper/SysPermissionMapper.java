package com.wells.mapper;

import com.wells.pojo.SysPermission;
import com.wells.pojo.SysPermissionCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysPermissionMapper {
    long countByExample(SysPermissionCriteria example);

    int deleteByExample(SysPermissionCriteria example);

    int deleteByPrimaryKey(Integer privilegeId);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    List<SysPermission> selectByExampleWithRowbounds(SysPermissionCriteria example, RowBounds rowBounds);

    List<SysPermission> selectByExample(SysPermissionCriteria example);

    SysPermission selectByPrimaryKey(Integer privilegeId);

    int updateByExampleSelective(@Param("record") SysPermission record, @Param("example") SysPermissionCriteria example);

    int updateByExample(@Param("record") SysPermission record, @Param("example") SysPermissionCriteria example);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
}