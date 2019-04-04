package com.wells.mapper;

import com.wells.pojo.SysUserRoleCriteria;
import com.wells.pojo.SysUserRoleKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysUserRoleMapper {
    long countByExample(SysUserRoleCriteria example);

    int deleteByExample(SysUserRoleCriteria example);

    int deleteByPrimaryKey(SysUserRoleKey key);

    int insert(SysUserRoleKey record);

    int insertSelective(SysUserRoleKey record);

    List<SysUserRoleKey> selectByExampleWithRowbounds(SysUserRoleCriteria example, RowBounds rowBounds);

    List<SysUserRoleKey> selectByExample(SysUserRoleCriteria example);

    int updateByExampleSelective(@Param("record") SysUserRoleKey record, @Param("example") SysUserRoleCriteria example);

    int updateByExample(@Param("record") SysUserRoleKey record, @Param("example") SysUserRoleCriteria example);
}