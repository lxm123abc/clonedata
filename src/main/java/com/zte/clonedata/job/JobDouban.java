package com.zte.clonedata.job;

import com.alibaba.fastjson.JSONObject;
import com.zte.clonedata.Contanst;
import com.zte.clonedata.model.Douban;
import com.zte.clonedata.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ProjectName: clonedata-com.zte.clonedata.job
 *
 * @Author: Liang Xiaomin
 * @Date: Creating in 16:46 2020/5/28
 * @Description:
 */
@Slf4j
@Component
@Async
public class JobDouban{

    /**
     */
    @Scheduled(cron = "0 6 * * * ?")
    public void execute(){
        String nowYYYYMMDD = DateUtils.getNowYYYYMMDD();
        long start = System.currentTimeMillis();
        log.info("{} 豆瓣开始执行任务 =================",nowYYYYMMDD);
        PicDownUtils picDownUtils = new PicDownUtils();
        int i = 0;
        int j = 0;
        while (true){
            String url ="https://movie.douban.com/j/search_subjects?type=movie&tag=热门&page_limit="
                    .concat(String.valueOf(i+1000))
                    .concat("&page_start=")
                    .concat(String.valueOf(i));
            String result = HttpUtils.getJson(url);
            String data = JSONObject.parseObject(result, Map.class).get(Contanst.JSON_KEY_DOUBAN).toString();
            if (StringUtils.isEmpty(data) || data.length() <10){
                break;
            }
            i = i+1000;
            List<Douban> doubans = JSONUtils.parseArray(data, Douban.class);
            for (Douban douban : doubans) {
                douban.setSort(j++);
                douban.setDid(UUID.randomUUID().toString().replaceAll("-",""));
                douban.setPDate(nowYYYYMMDD);
                String imageurl = douban.getCover();
                String name = imageurl.substring(imageurl.lastIndexOf("/")+1);
                String path = Contanst.TYPE_DOUBAN.concat(String.valueOf(i)).concat(File.separator).concat(name);
                douban.setFilepath(path);
                picDownUtils.urls.add(douban.getCover());
                picDownUtils.paths.add(path);
            }
            JDBCUtils.saveDouban(doubans);
            log.info("豆瓣第{}页加载完毕 =============",i);
        }
        log.info("豆瓣执行任务结束,用时:{} =================",System.currentTimeMillis()-start);
        Thread t1 = new Thread(picDownUtils);
        t1.start();
    }

}
