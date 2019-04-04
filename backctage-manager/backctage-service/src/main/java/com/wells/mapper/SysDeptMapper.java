package com.wells.mapper;

import com.wells.pojo.SysDept;
import com.wells.pojo.SysDeptCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysDeptMapper {
    long countByExample(SysDeptCriteria example);

    int deleteByExample(SysDeptCriteria example);

    int deleteByPrimaryKey(Integer deptId);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    List<SysDept> selectByExampleWithRowbounds(SysDeptCriteria example, RowBounds rowBounds);

    List<SysDept> selectByExample(SysDeptCriteria example);

    SysDept selectByPrimaryKey(Integer deptId);

    int updateByExampleSelective(@Param("record") SysDept record, @Param("example") SysDeptCriteria example);

    int updateByExample(@Param("record") SysDept record, @Param("example") SysDeptCriteria example);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);
}