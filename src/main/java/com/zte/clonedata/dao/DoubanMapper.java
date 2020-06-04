package com.zte.clonedata.dao;

import com.zte.clonedata.model.Douban;
import com.zte.clonedata.model.DoubanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoubanMapper {
    long countByExample(DoubanExample example);

    int deleteByExample(DoubanExample example);

    int deleteByPrimaryKey(String id);

    int insert(Douban record);

    int insertSelective(Douban record);

    List<Douban> selectByExample(DoubanExample example);

    int updateByExampleSelective(@Param("record") Douban record, @Param("example") DoubanExample example);

    int updateByExample(@Param("record") Douban record, @Param("example") DoubanExample example);
}