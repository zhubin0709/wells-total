package com.wells.mapper;

import com.wells.pojo.SysLoginLog;
import com.wells.pojo.SysLoginLogCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysLoginLogMapper {
    long countByExample(SysLoginLogCriteria example);

    int deleteByExample(SysLoginLogCriteria example);

    int deleteByPrimaryKey(Integer infoId);

    int insert(SysLoginLog record);

    int insertSelective(SysLoginLog record);

    List<SysLoginLog> selectByExampleWithRowbounds(SysLoginLogCriteria example, RowBounds rowBounds);

    List<SysLoginLog> selectByExample(SysLoginLogCriteria example);

    SysLoginLog selectByPrimaryKey(Integer infoId);

    int updateByExampleSelective(@Param("record") SysLoginLog record, @Param("example") SysLoginLogCriteria example);

    int updateByExample(@Param("record") SysLoginLog record, @Param("example") SysLoginLogCriteria example);

    int updateByPrimaryKeySelective(SysLoginLog record);

    int updateByPrimaryKey(SysLoginLog record);
}