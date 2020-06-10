package com.zte.clonedata.dao;

import com.zte.clonedata.model.TaskLog;
import com.zte.clonedata.model.TaskLogExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TaskLogMapper {
    long countByExample(TaskLogExample example);

    int deleteByExample(TaskLogExample example);

    int deleteByPrimaryKey(String id);

    int insert(TaskLog record);

    int insertSelective(TaskLog record);

    List<TaskLog> selectByExample(TaskLogExample example);

    TaskLog selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TaskLog record, @Param("example") TaskLogExample example);

    int updateByExample(@Param("record") TaskLog record, @Param("example") TaskLogExample example);

    int updateByPrimaryKeySelective(TaskLog record);

    int updateByPrimaryKey(TaskLog record);

    List<Map<String,Object>> groupbyTypeLog();
}