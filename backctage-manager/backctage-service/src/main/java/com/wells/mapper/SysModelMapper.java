package com.wells.mapper;

import com.wells.pojo.SysModel;
import com.wells.pojo.SysModelCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysModelMapper {
    long countByExample(SysModelCriteria example);

    int deleteByExample(SysModelCriteria example);

    int deleteByPrimaryKey(Integer modelId);

    int insert(SysModel record);

    int insertSelective(SysModel record);

    List<SysModel> selectByExampleWithRowbounds(SysModelCriteria example, RowBounds rowBounds);

    List<SysModel> selectByExample(SysModelCriteria example);

    SysModel selectByPrimaryKey(Integer modelId);

    int updateByExampleSelective(@Param("record") SysModel record, @Param("example") SysModelCriteria example);

    int updateByExample(@Param("record") SysModel record, @Param("example") SysModelCriteria example);

    int updateByPrimaryKeySelective(SysModel record);

    int updateByPrimaryKey(SysModel record);
}