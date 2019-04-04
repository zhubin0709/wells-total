package com.wells.mapper;

import com.wells.pojo.SysButton;
import com.wells.pojo.SysButtonCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysButtonMapper {
    long countByExample(SysButtonCriteria example);

    int deleteByExample(SysButtonCriteria example);

    int deleteByPrimaryKey(Integer btnId);

    int insert(SysButton record);

    int insertSelective(SysButton record);

    List<SysButton> selectByExampleWithRowbounds(SysButtonCriteria example, RowBounds rowBounds);

    List<SysButton> selectByExample(SysButtonCriteria example);

    SysButton selectByPrimaryKey(Integer btnId);

    int updateByExampleSelective(@Param("record") SysButton record, @Param("example") SysButtonCriteria example);

    int updateByExample(@Param("record") SysButton record, @Param("example") SysButtonCriteria example);

    int updateByPrimaryKeySelective(SysButton record);

    int updateByPrimaryKey(SysButton record);
}