package com.zte.clonedata.job;

import com.zte.clonedata.util.JDBCUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * ProjectName: clonedata-com.zte.clonedata.job
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 12:28 2020/6/1
 * @Description:
 */
@Slf4j
@Component
@Async
public class DeleteJob {

    /**
     * 每日凌晨删除7天以前的记录
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    public void execute() throws SQLException {
        log.info("清除任务开始  =================");
        JDBCUtils.delete();
    }


}
