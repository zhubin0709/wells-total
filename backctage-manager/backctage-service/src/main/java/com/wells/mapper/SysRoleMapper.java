package com.wells.mapper;

import com.wells.pojo.SysRole;
import com.wells.pojo.SysRoleCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysRoleMapper {
    long countByExample(SysRoleCriteria example);

    int deleteByExample(SysRoleCriteria example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByExampleWithRowbounds(SysRoleCriteria example, RowBounds rowBounds);

    List<SysRole> selectByExample(SysRoleCriteria example);

    SysRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleCriteria example);

    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleCriteria example);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
}