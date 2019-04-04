package com.wells.mapper;

import com.wells.pojo.SysUserOnline;
import com.wells.pojo.SysUserOnlineCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysUserOnlineMapper {
    long countByExample(SysUserOnlineCriteria example);

    int deleteByExample(SysUserOnlineCriteria example);

    int deleteByPrimaryKey(String sessionid);

    int insert(SysUserOnline record);

    int insertSelective(SysUserOnline record);

    List<SysUserOnline> selectByExampleWithRowbounds(SysUserOnlineCriteria example, RowBounds rowBounds);

    List<SysUserOnline> selectByExample(SysUserOnlineCriteria example);

    SysUserOnline selectByPrimaryKey(String sessionid);

    int updateByExampleSelective(@Param("record") SysUserOnline record, @Param("example") SysUserOnlineCriteria example);

    int updateByExample(@Param("record") SysUserOnline record, @Param("example") SysUserOnlineCriteria example);

    int updateByPrimaryKeySelective(SysUserOnline record);

    int updateByPrimaryKey(SysUserOnline record);
}