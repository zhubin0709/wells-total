package com.wells.mapper;

import com.wells.pojo.SysOperLog;
import com.wells.pojo.SysOperLogCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysOperLogMapper {
    long countByExample(SysOperLogCriteria example);

    int deleteByExample(SysOperLogCriteria example);

    int deleteByPrimaryKey(Integer operId);

    int insert(SysOperLog record);

    int insertSelective(SysOperLog record);

    List<SysOperLog> selectByExampleWithRowbounds(SysOperLogCriteria example, RowBounds rowBounds);

    List<SysOperLog> selectByExample(SysOperLogCriteria example);

    SysOperLog selectByPrimaryKey(Integer operId);

    int updateByExampleSelective(@Param("record") SysOperLog record, @Param("example") SysOperLogCriteria example);

    int updateByExample(@Param("record") SysOperLog record, @Param("example") SysOperLogCriteria example);

    int updateByPrimaryKeySelective(SysOperLog record);

    int updateByPrimaryKey(SysOperLog record);
}