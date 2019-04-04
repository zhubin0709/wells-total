package com.wells.mapper;

import com.wells.pojo.SysUser;
import com.wells.pojo.SysUserCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysUserMapper {
    long countByExample(SysUserCriteria example);

    int deleteByExample(SysUserCriteria example);

    int deleteByPrimaryKey(Integer userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExampleWithRowbounds(SysUserCriteria example, RowBounds rowBounds);

    List<SysUser> selectByExample(SysUserCriteria example);

    SysUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserCriteria example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserCriteria example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}