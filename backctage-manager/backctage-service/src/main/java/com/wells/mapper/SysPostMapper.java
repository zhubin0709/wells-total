package com.wells.mapper;

import com.wells.pojo.SysPost;
import com.wells.pojo.SysPostCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysPostMapper {
    long countByExample(SysPostCriteria example);

    int deleteByExample(SysPostCriteria example);

    int deleteByPrimaryKey(Integer postId);

    int insert(SysPost record);

    int insertSelective(SysPost record);

    List<SysPost> selectByExampleWithRowbounds(SysPostCriteria example, RowBounds rowBounds);

    List<SysPost> selectByExample(SysPostCriteria example);

    SysPost selectByPrimaryKey(Integer postId);

    int updateByExampleSelective(@Param("record") SysPost record, @Param("example") SysPostCriteria example);

    int updateByExample(@Param("record") SysPost record, @Param("example") SysPostCriteria example);

    int updateByPrimaryKeySelective(SysPost record);

    int updateByPrimaryKey(SysPost record);
}