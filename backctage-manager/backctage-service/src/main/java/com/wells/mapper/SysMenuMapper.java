package com.wells.mapper;

import com.wells.pojo.SysMenu;
import com.wells.pojo.SysMenuCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysMenuMapper {
    long countByExample(SysMenuCriteria example);

    int deleteByExample(SysMenuCriteria example);

    int deleteByPrimaryKey(Integer menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    List<SysMenu> selectByExampleWithRowbounds(SysMenuCriteria example, RowBounds rowBounds);

    List<SysMenu> selectByExample(SysMenuCriteria example);

    SysMenu selectByPrimaryKey(Integer menuId);

    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuCriteria example);

    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuCriteria example);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
}