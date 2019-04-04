package com.wells.mapper;

import com.wells.pojo.SysUserPostCriteria;
import com.wells.pojo.SysUserPostKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysUserPostMapper {
    long countByExample(SysUserPostCriteria example);

    int deleteByExample(SysUserPostCriteria example);

    int deleteByPrimaryKey(SysUserPostKey key);

    int insert(SysUserPostKey record);

    int insertSelective(SysUserPostKey record);

    List<SysUserPostKey> selectByExampleWithRowbounds(SysUserPostCriteria example, RowBounds rowBounds);

    List<SysUserPostKey> selectByExample(SysUserPostCriteria example);

    int updateByExampleSelective(@Param("record") SysUserPostKey record, @Param("example") SysUserPostCriteria example);

    int updateByExample(@Param("record") SysUserPostKey record, @Param("example") SysUserPostCriteria example);
}