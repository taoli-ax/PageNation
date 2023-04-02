package com.coh.dao;

import com.coh.pojo.Cup;
import com.coh.pojo.CupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CupDao {
    long countByExample(CupExample example);

    int deleteByExample(CupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cup record);

    int insertSelective(Cup record);

    List<Cup> selectByExample(CupExample example);

    Cup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cup record, @Param("example") CupExample example);

    int updateByExample(@Param("record") Cup record, @Param("example") CupExample example);

    int updateByPrimaryKeySelective(Cup record);

    int updateByPrimaryKey(Cup record);
}