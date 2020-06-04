package com.zte.clonedata.dao;

import com.zte.clonedata.model.DoubanMovie;
import com.zte.clonedata.model.DoubanMovieExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoubanMovieMapper {
    long countByExample(DoubanMovieExample example);

    int deleteByExample(DoubanMovieExample example);

    int deleteByPrimaryKey(String movieid);

    int insert(DoubanMovie record);

    int insertSelective(DoubanMovie record);

    List<DoubanMovie> selectByExample(DoubanMovieExample example);

    DoubanMovie selectByPrimaryKey(String movieid);

    int updateByExampleSelective(@Param("record") DoubanMovie record, @Param("example") DoubanMovieExample example);

    int updateByExample(@Param("record") DoubanMovie record, @Param("example") DoubanMovieExample example);

    int updateByPrimaryKeySelective(DoubanMovie record);

    int updateByPrimaryKey(DoubanMovie record);
}